package com.teleport.fwoj_backend.service.Impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.problemMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.problem;
import com.teleport.fwoj_backend.service.problemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class problemServiceImpl implements problemService {

    @Autowired
    private problemMapper problemMapperObject;
    @Autowired
    private userMapper userMapperObject;

    @Override
    public String getProblemList(int page,int pre,String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int start = pre * (page - 1);
        int num = pre;
        List<problem> list = problemMapperObject.getProblemList(start,num);
        int len =list.size();
        //list存储了问题列表。
        //需要判断是否登陆，若已登陆，则对list中每一个problem对象进行isSolved isAttempt的设置
        // 1 is
        if(userMapperObject.getUserTypeByToken(token) != null)
        {
            String attemptListString = userMapperObject.getUserAttemptListByToken(token);
            String attemptList[] = attemptListString.split(",");
            int attemptSize = attemptList.length;

            String solvedListString = userMapperObject.getUserSolvedListByToken(token);
            String solvedList[] = solvedListString.split(",");
            int solvedSize = solvedList.length;

            //若user的attemptList中有则SetAttempt(1)
            for(int i = 0 ; i < len ; i ++)
            {
                int flag = 0;
                for(int j = 0 ; j < attemptSize ; j ++)
                {
                    if(String.valueOf(list.get(i).getId()).equals(attemptList[j]))
                    {
                        list.get(i).setIsAttempt(1);
                        flag = 1 ;
                    }
                }
                if(flag == 0)
                    list.get(i).setIsAttempt(0);
            }

            //若user的solvedList中有则setAccept(1)
            for(int i = 0 ; i < len ; i ++)
            {
                int flag = 0;
                for(int j = 0 ; j < solvedSize ; j ++)
                {
                    if(String.valueOf(list.get(i).getId()).equals(solvedList[j]))
                    {
                        list.get(i).setIsAccept(1);
                        flag = 1 ;
                    }
                }
                if(flag == 0)
                    list.get(i).setIsAccept(0);
            }

        }
        int total = problemMapperObject.getProblemSum();
        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("status", 200);
        return mapper.writeValueAsString(s);
    }
    //按id查找详情
    @Override
    public String getProblemDetailById(int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        problem problemObject = problemMapperObject.getProblemDetailById(id);
        HashMap s = new HashMap();
        if (problemObject != null) {
            s.put("data", problemObject);
            s.put("error", "0");
        }
        //对象为null
        else
            s.put("error", "1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getProblemDetailAdmin(String token,int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            problem problemObject = problemMapperObject.getProblemDetailAdmin(id);
            if(problemObject != null)
            {
                s.put("error","0");
                s.put("data",problemObject);
            }
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return  mapper.writeValueAsString(s);
    }

    @Override
    public String changeProblemVisible(String token, int id) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            //可见
            if(problemMapperObject.getProblemVisibleById(id) == 1)
                if(problemMapperObject.setProblemVisibleById(id,false) == 1)
                    s.put("error","0");
                else
                    s.put("error","2");
                //不可见
            else
                if(problemMapperObject.setProblemVisibleById(id,true) == 1)
                    s.put("error","0");
                else
                    s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getProblemListAdmin(int page, int pre, String key,String token) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            int start = pre * (page - 1);
            int num = pre;
            s.put("data",problemMapperObject.getProblemListAdmin(start,num,key));
            s.put("num",problemMapperObject.getProblemSumAdmin());
            s.put("status",1);
        }
        else
            s.put("status",0);
        return  mapper.writeValueAsString(s);
    }


    @Override
    public String deleteProblemById(String token,int id) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 删除失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(problemMapperObject.deleteProblemById(id) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String createProblem(String token,String title, String des, String input, String output, String inputExample, String outputExample, String hint) throws JsonProcessingException {

        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = simpleDateFormat.format(new Date());
            if(problemMapperObject.createProblem(title,des,input,output,inputExample,outputExample,hint,
                    0,0,dateTime,userMapperObject.getUserIdByToken(token),false) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String editProblem
            (String token,String title, String des, String input, String output, String inputExample, String outputExample, String hint, int id) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(problemMapperObject.editProblem(title,des,input,output,inputExample,outputExample,hint,id) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String uploadTestCaseById(@RequestParam("file") MultipartFile file, String token, int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if (Objects.isNull(file)) {
            s.put("error", "-1");
            return mapper.writeValueAsString(s);
        }

        //若不存在题目文件夹则创建
        String UPLOAD_FOLDER = "./uploadFolder/test_case/" + id + "/";
        File folder = new File(UPLOAD_FOLDER);
        if (!folder.exists() && !folder.isDirectory())
            folder.mkdirs();

        //若存在test_case_id文件夹则删除
        String TEST_CASE_FOLDER = "./uploadFolder/test_case/" + id + "/" + "test_case_" +id;
        File folder2 = new File(TEST_CASE_FOLDER);
        deleteDir(folder2);
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER  + "test_case_"+ id +".zip");
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            Files.write(path, bytes);
            s.put("error", "0");
        } catch (IOException e) {
            s.put("error", "-2");
        }

        return mapper.writeValueAsString(s);
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
