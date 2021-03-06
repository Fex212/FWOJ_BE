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
        //token?????????
        if (userMapperObject.getUserNameByToken(token) == null
            || userMapperObject.getUserNameByToken(token).equals(""))
        {
            s.put("error", "1");
        }
        else {
            //?????????state???
            Date date = new Date();
            int authorId = userMapperObject.getUserIdByToken(token);
            int isVisible = problemMapperObject.getProblemVisibleById(problemId);
            if (isVisible == 1
                    && stateMapperObject.addState(problemId, authorId, date, language, code.trim()) == 1) {
                //id??????pending ???
                int lastId = stateMapperObject.getLastStateId();
                stateMapperObject.addPendingId(lastId);
                s.put("error", "0");
            }
            else
                s.put("error", "2");
        }
        return mapper.writeValueAsString(s);
    }

    //QDU_Judger
    @Override
    public void judgeServer() throws JSONException, IOException, ParseException {

        List<Integer> idList = stateMapperObject.getPendingIdList();
        List<state> list = new LinkedList<>();
        int len = idList.size();

        for(int i = 0 ; i < len;i ++) {
            list.add(stateMapperObject.getStateAll(idList.get(i)));
        }

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

            //??????json??????????????????
            JSONObject reJo = JSONObject.parseObject(re);

            //????????????????????????ac
            int acFlag = 0;

            if(reJo.getString("err") != null)
            {
                //????????????????????????
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
                stateMapperObject.deletePendingId(list.get(i).getId());
            }
            else
            {
                //????????????
                JSONArray data = reJo.getJSONArray("data");
                int arrLen = data.size();
                //????????????????????????????????????????????????????????????
                //AC 0 TLE 1 RE 4 WA -1
                //??????RE???RE ??????TLE???TLE ??????WA???WA ??????AC???AC
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
                //???????????????ac?????????
                if(flag == 1)
                {
                    finalRe = "ac";
                    acFlag = 1;
                }
                //??????state timeCost memoryCost
                stateMapperObject.updateState(stateId,finalRe,"");
                stateMapperObject.setTimeCost(stateId,maxTimeCost);
                stateMapperObject.setMemoryCost(stateId,maxMemoryCost);
                //??????pending?????????id
                stateMapperObject.deletePendingId(list.get(i).getId());
            }
            //????????????????????????????????????solvedList,attemptList,solvedNum
            String solvedList = "";
            String attemptList = "";
            if(userMapperObject.getUserSolvedListById(authorId) != null)
                solvedList = userMapperObject.getUserSolvedListById(authorId);
            if(userMapperObject.getUserAttemptListById(authorId) != null)
                attemptList = userMapperObject.getUserAttemptListById(authorId);

            //????????????ac????????????solved?????????????????????????????????num++
            if(acFlag == 1)
            {
                String acList[] = solvedList.split(",");
                int acLen = acList.length;
                //??????solved?????????????????????
                int existFlag = 0;
                if(!solvedList.equals("")) {
                    for(int j = 0 ; j < acLen ; j ++) {
                        if(Integer.parseInt(acList[j]) == problemId) {
                            existFlag = 1;
                            break;
                        }
                    }
                }
                //solved??????????????????,?????????num++
                if(existFlag == 0)
                {
                    if(solvedList.equals(""))
                        userMapperObject.setUserSolvedListById(authorId,String.valueOf(problemId));
                    else
                        userMapperObject.setUserSolvedListById(authorId,solvedList+","+ problemId);
                    userMapperObject.setSolvedNumPlus(authorId);
                    //?????????totalSubmit++ acSubmit++
                    problemMapperObject.totalSubmitPlus(problemId);
                    problemMapperObject.acSubmitPlus(problemId);
                }
            }
            //????????????ac?????????attempt????????????????????????
            else {
                String attList[] = attemptList.split(",");
                int attLen = attList.length;
                //??????attempt?????????????????????
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
                //attempt??????????????????,??????
                if(existFlag == 0) {
                    if(attemptList.equals(""))
                        userMapperObject.setUserAttemptListById(authorId,String.valueOf(problemId));
                    else
                        userMapperObject.setUserAttemptListById(authorId,attemptList+","+ problemId);
                }
                //?????????totalSubmit++
                problemMapperObject.totalSubmitPlus(problemId);
            }
        }
    }

    /*
    //FWOJ_Judger
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

            String re = "";
            String url = "http://localhost:8081";
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            String body = "1teleports" + code + "1teleporte2teleports" + problemId + "2teleporte";
            StringEntity s = new StringEntity(body, "utf-8");
            httpPost.setEntity(s);

            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                re = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
            response.close();
            //????????????????????????ac
            int acFlag = 0;

            if(re.equals("") || re.length() < 7)
            {
                //testcase not found or system error
                stateMapperObject.updateState(stateId,"se","systemError or testcase not found");
                stateMapperObject.setTimeCost(stateId,0);
                stateMapperObject.setMemoryCost(stateId,0);

            }
            else if(!(re.substring(0,6).equals("result")))
            {
                //ce??????????????????
                stateMapperObject.updateState(stateId,"ce",re);
                stateMapperObject.setTimeCost(stateId,0);
                stateMapperObject.setMemoryCost(stateId,0);
            }
            else
            {
                LinkedList<LinkedList<String>> reList = new LinkedList<>();
                String row[] = re.split("\n");
                int rowLen = row.length;

                for(int j = 0 ; j < rowLen ; j++)
                {
                    String mapp[] = row[j].split(",");
                    int mappLen = mapp.length;
                    LinkedList<String> tmpList = new LinkedList<>();
                    for(int k = 0 ; k < mappLen ; k ++) {
                        String tmp[] = mapp[k].split(":");
                        tmpList.add(tmp[1]+" ");
                    }
                    reList.add(tmpList);
                }
//                int reListLen = reList.size();

//                for(int j = 0 ; j < reListLen ; j ++) {
//                    LinkedList<String> tmpList = reList.get(j);
//                    int tmpListLen = tmpList.size();
//                    for(int k = 0 ;k < tmpListLen ; k ++){
//                        System.out.print(tmpList.get(k) + " ");
//                    }
//                    System.out.println();
//                }
                //????????????
                int arrLen = reList.size();
                //????????????????????????????????????????????????????????????
                //AC 0 TLE 1 RE 4 WA -1
                //??????RE???RE ??????TLE???TLE ??????WA???WA ??????AC???AC
                String finalRe = "";
                int flag = 1;
                int maxTimeCost = -1;
                int maxMemoryCost = -1;

                for(int j = 0 ; j < arrLen ; j++)
                {
                    LinkedList<String> tmpList = reList.get(j);
                    String result = tmpList.get(0).trim();
                    int cpu_time = Integer.parseInt(tmpList.get(1).trim());
                    int memory = Integer.parseInt(tmpList.get(2).trim());

                    if(maxTimeCost < cpu_time)
                        maxTimeCost = cpu_time;
                    if(maxMemoryCost < memory)
                        maxMemoryCost = memory;

                    if(result.equals("RE"))
                    {
                        finalRe = "re";
                        flag = 0;
                        break;
                    }
                    else if(result.equals("TLE"))
                    {
                        finalRe = "tle";
                        flag = 0;
                        break;
                    }
                    else if(result.equals("WA"))
                    {
                        finalRe = "wa";
                        flag = 0;
                        break;
                    }
                    else if(result.equals("MLE"))
                    {
                        finalRe = "mle";
                        flag = 0;
                        break;
                    }
                    else if(result.equals("OLE"))
                    {
                        finalRe = "ole";
                        flag = 0;
                        break;
                    }
                    else if(result.equals("SE"))
                    {
                        finalRe = "se";
                        flag = 0;
                        break;
                    }
                }
                //???????????????ac?????????
                if(flag == 1)
                {
                    finalRe = "ac";
                    acFlag = 1;
                }
                //??????state timeCost memoryCost
                stateMapperObject.updateState(stateId,finalRe,"");
                stateMapperObject.setTimeCost(stateId,maxTimeCost);
                stateMapperObject.setMemoryCost(stateId,maxMemoryCost);
            }
            //????????????????????????????????????solvedList,attemptList,solvedNum
            String solvedList = "";
            String attemptList = "";
            if(userMapperObject.getUserSolvedListById(authorId) != null)
                solvedList = userMapperObject.getUserSolvedListById(authorId);
            if(userMapperObject.getUserAttemptListById(authorId) != null)
                attemptList = userMapperObject.getUserAttemptListById(authorId);

            //????????????ac????????????solved?????????????????????????????????num++
            if(acFlag == 1)
            {
                String acList[] = solvedList.split(",");
                int acLen = acList.length;
                //??????solved?????????????????????
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
                //solved??????????????????,?????????num++
                if(existFlag == 0)
                {
                    if(solvedList.equals(""))
                        userMapperObject.setUserSolvedListById(authorId,String.valueOf(problemId));
                    else
                        userMapperObject.setUserSolvedListById(authorId,solvedList+","+ problemId);
                    userMapperObject.setSolvedNumPlus(authorId);
                    //?????????totalSubmit++ acSubmit++
                    problemMapperObject.totalSubmitPlus(problemId);
                    problemMapperObject.acSubmitPlus(problemId);
                }
            }
            //????????????ac?????????attempt????????????????????????
            else
            {
                String attList[] = attemptList.split(",");
                int attLen = attList.length;
                //??????attempt?????????????????????
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
                //attempt??????????????????,??????
                if(existFlag == 0)
                {
                    if(attemptList.equals(""))
                        userMapperObject.setUserAttemptListById(authorId,String.valueOf(problemId));
                    else
                        userMapperObject.setUserAttemptListById(authorId,attemptList+","+ problemId);
                }
                //?????????totalSubmit++
                problemMapperObject.totalSubmitPlus(problemId);
            }
        }
    }
    */

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
