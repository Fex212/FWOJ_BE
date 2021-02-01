package com.teleport.fwoj_backend.service.Impl;
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
            JSONObject jsonObject = JSONObject.parseObject(re);
            if(jsonObject.getString("err") != null)
            {
                String err = jsonObject.getString("err");
                if(err.equals("CompileError"))
                {
                    //编译错误写入数据库
                    stateMapperObject.updateState(stateId,"Compile Error");
                }
            }
            else
            {
                //编译成功
                //AC 0 TLE 1 RE 4 WA -1 CE -2
                System.out.println("buildSuccessful");
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
