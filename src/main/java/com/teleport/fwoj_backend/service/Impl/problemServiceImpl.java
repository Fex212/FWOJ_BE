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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

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

    //QDU Judger
    @Override
    public String uploadTestCaseById(@RequestParam("file") MultipartFile file, String token, int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();

        //0 正常 -1 越权 -2 失败 -3 文件格式错误
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin")) {
            if (Objects.isNull(file)) {
                s.put("error", "-1");
                return mapper.writeValueAsString(s);
            }

            //若存在题目文件夹删除
            String UPLOAD_FOLDER = "./uploadFolder/test_case/" + id + "/";
            File folder = new File(UPLOAD_FOLDER);
            if(folder.exists())
                deleteDir(folder);

            //写入压缩包文件
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER  + "test_case_"+ id +".zip");
                if (!Files.isWritable(path)) {
                    Files.createDirectories(Paths.get(UPLOAD_FOLDER));
                }
                Files.write(path, bytes);
                //解压到同名文件夹
                File f1 = new File(UPLOAD_FOLDER  + "test_case_"+ id +".zip");
                unZip(f1,UPLOAD_FOLDER);
                //删除压缩包
                if (f1.exists()) {
                    f1.delete();
                }
                //删除__MACOSX文件夹
//                System.out.println(UPLOAD_FOLDER+"__MACOSX");
                File f2 = new File(UPLOAD_FOLDER +"/__MACOSX");
                if(f2.exists())
                    f2.delete();

                //检查是否是1.in 1.out 2.in 2.out这样排下去的
                int cnt = 0;
                for(int i = 1; i <= 1000 ; i ++)
                {
                    File f3 = new File(UPLOAD_FOLDER  +"/" + i + ".in");
                    File f4 = new File(UPLOAD_FOLDER  +"/" + i + ".out");
                    //如果都存在，cnt++
                    if(f3.exists() && f4.exists())
                        cnt ++;
                    else
                        break;
                }
                //一切正常，添加info
                String info = "";
                String infoData = "";
                info += "{\n";
                info += "    \"spj\": false,\n";
                info += "    \"test_cases\": {\n";

                for(int i = 1 ; i <= cnt ; i ++)
                {
                    info += "        \"" + i +"\": {\n";
                    //读入i.in和i.out
                    String in = readFile(UPLOAD_FOLDER  + i + ".in");
                    String out = readFile(UPLOAD_FOLDER + i + ".out");

                    int inSize = in.length();
                    int outSize = out.length();
                    if(out.charAt(outSize-1) == '\n')
                    {
                        out = out.substring(0,outSize-1);
                    }
                    String md5 = md5(out);

                    info += "            \"stripped_output_md5\": \"" + md5 + "\",\n";

                    info += "            \"input_size\": " + inSize + ",\n";
                    info += "            \"output_size\": "+ outSize + ",\n";
                    info += "            \"input_name\": \""+ i +".in\",\n";
                    info += "            \"output_name\": \""+ i +".out\"\n";
                    if(i == cnt)
                        info += "        }\n";
                    else
                        info += "        },\n";
                }

                info += "    }\n}";
                infoData += cnt;

                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(UPLOAD_FOLDER +"/info"));
                    out.write(info);
                    out.close();
                    BufferedWriter out2 = new BufferedWriter(new FileWriter(UPLOAD_FOLDER +"/info.data"));
                    out2.write(infoData);
                    out2.close();
                } catch (IOException e) {
                }



                s.put("error", "0");
            } catch (IOException e) {
                s.put("error", "-2");
            }
        }
        else
            s.put("error", "-1");

        return mapper.writeValueAsString(s);
    }

    //FWOJ_Judger
    /*
    @Override
    public String uploadTestCaseById(@RequestParam("file") MultipartFile file, String token, int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();

        //0 正常 -1 越权 -2 失败 -3 文件格式错误
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin")) {
            if (Objects.isNull(file)) {
                s.put("error", "-1");
                return mapper.writeValueAsString(s);
            }

            //若存在题目文件夹删除
            String UPLOAD_FOLDER = "./uploadFolder/test_case/" + id + "/";
            File folder = new File(UPLOAD_FOLDER);
            if(folder.exists())
                deleteDir(folder);

            //写入压缩包文件
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER  + "test_case_"+ id +".zip");
                if (!Files.isWritable(path)) {
                    Files.createDirectories(Paths.get(UPLOAD_FOLDER));
                }
                Files.write(path, bytes);
                //解压到同名文件夹
                File f1 = new File(UPLOAD_FOLDER  + "test_case_"+ id +".zip");
                unZip(f1,UPLOAD_FOLDER);
                //删除压缩包
                if (f1.exists()) {
                    f1.delete();
                }
                //删除__MACOSX文件夹
//                System.out.println(UPLOAD_FOLDER+"__MACOSX");
                File f2 = new File(UPLOAD_FOLDER +"/__MACOSX");
                if(f2.exists())
                    f2.delete();

                //检查是否是1.in 1.out 2.in 2.out这样排下去的
                int cnt = 0;
                for(int i = 1; i <= 1000 ; i ++)
                {
                    File f3 = new File(UPLOAD_FOLDER  +"/" + i + ".in");
                    File f4 = new File(UPLOAD_FOLDER  +"/" + i + ".out");
                    //如果都存在，cnt++
                    if(f3.exists() && f4.exists())
                        cnt ++;
                    else
                        break;
                }
                //一切正常，添加info
                String info = "";
                info += cnt;
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(UPLOAD_FOLDER +"/info.data"));
                    out.write(info);
                    out.close();
//                    System.out.println("success");
                } catch (IOException e) {
                }
                s.put("error", "0");
            } catch (IOException e) {
                s.put("error", "-2");
            }
        }
        else
            s.put("error", "-1");

        return mapper.writeValueAsString(s);
    }
    */

    //0 正常 1 越权 2 无数据
    @Override
    public void downloadTestCaseById(String token, int id, HttpServletResponse res) throws IOException {
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            byte[] bytes;
            FileInputStream inputStream;

            File dir = new File("./uploadFolder/test_case/" + id + "/" );
            String[] list = dir.list();
            if(!dir.isDirectory())
            {
                return;
            }

            //需要压缩的文件列表
            List<File> fileList = new ArrayList<>();

            for(String str : list)
            {
                File f = new File("./uploadFolder/test_case/" + id + "/"+str);
                if(str.charAt(0) != '.' && !f.isDirectory())
                {
                    fileList.add(f);
                }
            }
            String target = "./uploadFolder/test_case/" + id + "/" + "test_case_" + id + ".zip";
            toZip(fileList,id,target);
            OutputStream outputStream = res.getOutputStream();
            res.addHeader( "Content-Disposition", "attachment;filename=" + "test_case_" + id + ".zip");
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            bis = new BufferedInputStream(new FileInputStream(new File(target)));
            int i = bis.read(buff);
            while (i != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                i = bis.read(buff);
            }
            outputStream.close();
            bis.close();
        }
        return ;
    }


    // 0 有样例 -1 越权 -2 无样例
    @Override
    public String isTestCaseExistById(String token, int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            File dir = new File("./uploadFolder/test_case/" + id + "/");
            if(!dir.isDirectory())
            {
                s.put("error","-2");
                return mapper.writeValueAsString(s);
            }
            s.put("error","0");
        }
        else
            s.put("error","-1");
        return mapper.writeValueAsString(s);
    }

    //递归删除文件夹
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
    //解压缩
    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();

                    File dir = new File(dirPath);

                    dir.mkdirs();

                } else {
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    if (!targetFile.getParentFile().exists())
                        targetFile.getParentFile().mkdirs();
                    targetFile.createNewFile();
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[10240];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
//            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        }
        finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private static String md5(String str) {
        try {
//            String utf8Str = URLEncoder.encode(str,"UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
        }
        return "";
    }

    //压缩
    private static void toZip(List<File> srcFiles,int id,String target)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(new FileOutputStream(target));
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[1024];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //读取文件内容
    public static String readFile(String strFile){
        try{
            InputStream is = new FileInputStream(strFile);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            String str = new String(bytes);
            is.close();
            return str;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}

/*
{
    "spj": false,
    "test_cases": {
        "1": {
            "stripped_output_md5": "e4da3b7fbbce2345d7772b0674a318d5",
            "input_size": 4,
            "output_size": 2,
            "input_name": "1.in",
            "output_name": "1.out"
        },
        "2": {
            "stripped_output_md5": "d07e2b090a63d7496b086d61ec508131",
            "input_size": 9,
            "output_size": 5,
            "input_name": "2.in",
            "output_name": "2.out"
        },
        "3": {
            "stripped_output_md5": "38913e1d6a7b94cb0f55994f679f5956",
            "input_size": 7,
            "output_size": 3,
            "input_name": "3.in",
            "output_name": "3.out"
        }
    }
}
 */

