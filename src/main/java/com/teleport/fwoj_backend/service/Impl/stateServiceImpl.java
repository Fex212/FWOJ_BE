package com.teleport.fwoj_backend.service.Impl;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.problemMapper;
import com.teleport.fwoj_backend.mapper.stateMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.state;
import com.teleport.fwoj_backend.service.stateService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.LinkedList;



@Service
public class stateServiceImpl implements stateService {

    @Autowired
    private stateMapper stateMapperObject;
    @Autowired
    private userMapper userMapperObject;
    @Autowired
    private problemMapper problemMapperObject;

    @Override
    public String getStateList(int page, int pre) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int start = pre * (page - 1);
        int num = pre;
        List<state> list = stateMapperObject.getStateList(start,num);
        int total = stateMapperObject.getStateSum();
        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("error", "0");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String  getStateDetail(int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        s.put("data", stateMapperObject.getStateDetail(id));
        return mapper.writeValueAsString(s);
    }

    @Override
    public String addState(String code, int problemId, String token, String language) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        //token不合法
        if (userMapperObject.getUserNameByToken(token) == null
            || userMapperObject.getUserNameByToken(token).equals(""))
        {
            s.put("error", "1");
        }
        else {
            //提交到state表
            Date date = new Date();
            int authorId = userMapperObject.getUserIdByToken(token);
            int isVisible = problemMapperObject.getProblemVisibleById(problemId);
            if (isVisible == 1
                    &&
                    stateMapperObject.addState(problemId, authorId, date, language, code) == 1)
            {
                s.put("error", "0");
            }
            else
                s.put("error", "2");
        }
        return mapper.writeValueAsString(s);
    }

    @Override
    public void judgeServer() throws JSONException, IOException, ParseException {

        List<state> list = stateMapperObject.getPendingList();

        int len = list.size();
        for(int i = 0 ; i < len ; i ++)
        {
            int stateId = list.get(i).getId();
            int problemId = list.get(i).getProblemId();
            int authorId = list.get(i).getAuthorId();
            String code = list.get(i).getCode();

            JSONObject jo = new JSONObject();
            jo.put("src",code);
            jo.put("max_cpu_time",3000);
            jo.put("max_memory",134217728);

            JSONObject compile = new JSONObject();
            compile.put("src_name", "main.c");
            compile.put("exe_name", "main");
            compile.put("max_cpu_time",3000);
            compile.put("max_real_time", 5000);
            compile.put("max_memory",134217728);
            compile.put("compile_command", "/usr/bin/g++ -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c++11 {src_path} -lm -o {exe_path}");

            JSONObject run = new JSONObject();
            run.put("command", "{exe_path}");
            run.put("seccomp_rule", "c_cpp");
            LinkedList<String> l = new LinkedList<>();
            l.add("LANG=en_US.UTF-8");
            l.add("LANGUAGE=en_US:en");
            l.add("LC_ALL=en_US.UTF-8");
            run.put("env",l);

            JSONObject language_config = new JSONObject();
            language_config.put("compile",compile);
            language_config.put("run",run);

            jo.put("language_config",language_config);
            jo.put("test_case_id",String.valueOf(problemId));

            String re = judge("http://localhost:8081/judge",jo);

            //解析json进行相关处理
            JSONObject reJo = JSONObject.parseObject(re);

            //标志本次提交是否ac
            int acFlag = 0;

            if(reJo.getString("err") != null)
            {
                //出错。写入数据库
                String err = reJo.getString("err");
                if(err.equals("CompileError"))
                {
                    stateMapperObject.updateState(stateId,"ce",reJo.getString("data"));
                }
                else if(err.equals("JudgeClientError"))
                {
                    stateMapperObject.updateState(stateId,"se",reJo.getString("data"));
                }
                stateMapperObject.setTimeCost(stateId,0);
                stateMapperObject.setMemoryCost(stateId,0);
            }
            else
            {
                //编译成功
                JSONArray data = reJo.getJSONArray("data");
                int arrLen = data.size();
                //综合所有的测试样例，判断出本题的判题结果
                //AC 0 TLE 1 RE 4 WA -1
                //一个RE则RE 一个TLE则TLE 一个WA则WA 全部AC则AC
                String finalRe = "";
                int flag = 1;
                int maxTimeCost = -1;
                int maxMemoryCost = -1;

                for(int j = 0 ; j < arrLen ; j++)
                {
                    String result = data.getJSONObject(j).getString("result");
                    int cpu_time = Integer.parseInt(data.getJSONObject(j).getString("cpu_time"));
                    int memory = Integer.parseInt(data.getJSONObject(j).getString("memory"));

                    if(maxTimeCost < cpu_time)
                        maxTimeCost = cpu_time;
                    if(maxMemoryCost < memory)
                        maxMemoryCost = memory;

                    if(result.equals("4"))
                    {
                        finalRe = "re";
                        flag = 0;
                        break;
                    }
                    else if(result.equals("1"))
                    {
                        finalRe = "tle";
                        flag = 0;
                        break;
                    }
                    else if(result.equals("-1"))
                    {
                        finalRe = "wa";
                        flag = 0;
                        break;
                    }
                }
                //没有遇到不ac的情况
                if(flag == 1)
                {
                    finalRe = "ac";
                    acFlag = 1;
                }
                //写入state timeCost memoryCost
                stateMapperObject.updateState(stateId,finalRe,"");
                stateMapperObject.setTimeCost(stateId,maxTimeCost);
                stateMapperObject.setMemoryCost(stateId,maxMemoryCost);
            }
            //判题完成。开始处理用户的solvedList,attemptList,solvedNum
            String solvedList = "";
            String attemptList = "";
            if(userMapperObject.getUserSolvedListById(authorId) != null)
                solvedList = userMapperObject.getUserSolvedListById(authorId);
            if(userMapperObject.getUserAttemptListById(authorId) != null)
                attemptList = userMapperObject.getUserAttemptListById(authorId);

            //如果本题ac了，如果solved中没有则需要加入，而且num++
            if(acFlag == 1)
            {
                String acList[] = solvedList.split(",");
                int acLen = acList.length;
                //标志solved中是否有该题目
                int existFlag = 0;
                if(!solvedList.equals(""))
                {
                    for(int j = 0 ; j < acLen ; j ++)
                    {
                        if(Integer.parseInt(acList[j]) == problemId)
                        {
                            existFlag = 1;
                            break;
                        }
                    }
                }
                //solved中没有该题目,加入，num++
                if(existFlag == 0)
                {
                    if(solvedList.equals(""))
                        userMapperObject.setUserSolvedListById(authorId,String.valueOf(problemId));
                    else
                        userMapperObject.setUserSolvedListById(authorId,solvedList+","+ problemId);
                    userMapperObject.setSolvedNumPlus(authorId);
                    //题目的totalSubmit++ acSubmit++
                    problemMapperObject.totalSubmitPlus(problemId);
                    problemMapperObject.acSubmitPlus(problemId);
                }
            }
            //若本题没ac，如果attempt中没有则需要加入
            else
            {
                String attList[] = attemptList.split(",");
                int attLen = attList.length;
                //标志attempt中是否有该题目
                int existFlag = 0;
                if(!attemptList.equals(""))
                {
                    for(int j = 0 ; j < attLen ; j ++)
                    {
                        if(Integer.parseInt(attList[j]) == problemId)
                        {
                            existFlag =1;
                            break;
                        }
                    }
                }
                //attempt中没有该题目,加入
                if(existFlag == 0)
                {
                    if(attemptList.equals(""))
                        userMapperObject.setUserAttemptListById(authorId,String.valueOf(problemId));
                    else
                        userMapperObject.setUserAttemptListById(authorId,attemptList+","+ problemId);
                }
                //题目的totalSubmit++
                problemMapperObject.totalSubmitPlus(problemId);
            }
        }
    }

    public static String judge(String url, JSONObject jsonObject) throws org.apache.http.ParseException, IOException {
        String body = "";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "application/json"));
        httpPost.setEntity(s);

        httpPost.setHeader("X-Judge-Server-Token", "e99f7ff1ebb40a91320378dcc36e550a66f22ed468fe7fcacc0604938460488f");
        httpPost.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            body = EntityUtils.toString(entity, "UTF-8");
        }
        EntityUtils.consume(entity);
        response.close();
        return body;
    }

}
