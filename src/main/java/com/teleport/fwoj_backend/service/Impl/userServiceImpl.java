package com.teleport.fwoj_backend.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.problemMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.user;
import com.teleport.fwoj_backend.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
public class userServiceImpl implements userService {

    @Autowired
    private userMapper userMapperObject;
    @Autowired
    private problemMapper problemMapperObject;

    @Override
    public String login(String username, String passwd) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        //error 0 成功 -1 用户名或密码不正确 -2 账号封禁
        if(userMapperObject.loginCheck(username,passwd) == 1)
            if(userMapperObject.getAvailableByUsername(username) == 1)
            {
                s.put("error","0");
                byte[] lock = new byte[0];
                long w = 100000000;
                long r = 0;
                synchronized (lock) {
                    r = (long) ((Math.random() + 1) * w);
                }
                String token = System.currentTimeMillis() + String.valueOf(r).substring(1);
                userMapperObject.createToken(username,token);
                s.put("token",token);
            }
            else
                s.put("error","-2");
        else
            s.put("error","-1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String register(String email, String username, String passwd) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();

        int emailE = userMapperObject.emailExist(email);
        int usernameE = userMapperObject.usernameExist(username);

        //error:1 email exist 2 username exist 3 format error 4sql error
        if(emailE == 1)
            s.put("error","1");
        else if(usernameE == 1)
            s.put("error","2");
        else if(username.length() > 10 || username.length() < 2 || passwd.length()>35 || email.length() > 30)
            s.put("error","3");
        else
        {

            int r = userMapperObject.register(email,username,passwd);
            if(r == 1)
                s.put("error","0");
            else
                s.put("error","4");
        }
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getUserNameByToken(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if(token == null)
            return null;
        HashMap s = new HashMap();
        s.put("username",userMapperObject.getUserNameByToken(token));
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getUserIdByToken(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(token != null)
        {
            if(userMapperObject.getUserIdByToken(token) != null)
            {
                s.put("error","0");
                s.put("userId",userMapperObject.getUserIdByToken(token));
            }
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getUserTypeByToken(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if(token == null)
            return null;
        HashMap s = new HashMap();
        s.put("userType",userMapperObject.getUserTypeByToken(token));
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getUserPersonInfo(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        int id = userMapperObject.getUserIdByToken(token);
        String username = userMapperObject.getUserNameByToken(token);
        String email = userMapperObject.getUserEmailByToken(token);
        String type = userMapperObject.getUserTypeByToken(token);
        String site = userMapperObject.getUserSiteByUsername(username);
        String github = userMapperObject.getUserGithubByUsername(username);
        String sign = userMapperObject.getUserSignByUsername(username);
        if(id != 0 && username != null && email != null && type != null)
        {
            s.put("error","0");
            s.put("id",id);
            s.put("username",username);
            s.put("email",email);
            s.put("type",type);
            s.put("github",github);
            s.put("sign",sign);
            s.put("site",site);
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String updateUserPersonInfo(String token,String username, String sign, String site, String github) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();

        //error 1 用户名已被注册 2 更新失败
        int id = userMapperObject.getUserIdByToken(token);
        int usernameNumExcept = userMapperObject.getUsernameNumExpect(username,id);

        if(usernameNumExcept != 0)
            s.put("error","1");
        else
        {
            if(userMapperObject.updateUserSiteByToken(token,site) == 1 &&
                    userMapperObject.updateUserGithubByToken(token,github) == 1 &&
                    userMapperObject.updateUserSignByToken(token,sign) == 1 &&
                    userMapperObject.updateUserNameByToken(token,username) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getUserCardInfo(int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        //type,sign,site,github
        String type = userMapperObject.getUserTypeById(id);
        String sign = userMapperObject.getUserSignById(id);
        String site = userMapperObject.getUserSiteById(id);
        String github = userMapperObject.getUserGithubById(id);
        String username = userMapperObject.getUserNameById(id);
        String solvedListString = userMapperObject.getUserSolvedListById(id);

        String tmp[] = solvedListString.split(",");
        int len  = tmp.length;
        if(type != null)
        {
            s.put("error","0");
            s.put("type",type);
            s.put("site",site);
            s.put("sign",sign);
            s.put("github",github);
            s.put("username",username);
            s.put("solvedNumber",len);
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String changeUserAvailable(String token,int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            //可用
            if(userMapperObject.getUserAvailableById(id) == 1)
            {
                if(userMapperObject.setAvailableById(id,false) == 1)
                    s.put("error","0");
                else
                    s.put("error","1");
            }
            //不可用
            else
            {
                if(userMapperObject.setAvailableById(id,true) == 1)
                    s.put("error","0");
                else
                    s.put("error","1");
            }
        }
        else
            s.put("error","2");

        return mapper.writeValueAsString(s);
    }

    @Override
    public String getUserList(Integer page, Integer pre, String key, String token) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if (userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin")) {
            int start = pre * (page - 1);
            int num = pre;
            List<user> list = userMapperObject.getUserList(start, num, key);
            int userNum = userMapperObject.getUserNum();
            s.put("data", list);
            s.put("num", userNum);
            s.put("error", "0");
        } else
            s.put("error", "1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String tokenIsAdmin(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();

        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            s.put("result","1");
        }
        else
            s.put("result","0");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String  getUserDetailById(String token,int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            s.put("error","0");
            s.put("userDetail",userMapperObject.getUserDetailById(id));
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String editUserDetail
            (String token,String email, String username, String type, String passwd,int id) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        int emailNumExcept = userMapperObject.getEmailNumExpect(email,id);
        int usernameNumExcept = userMapperObject.getUsernameNumExpect(username,id);
        //error:1 email exist 2 username exist
        //3 format error 4 sql error
        //5 permission error
        //判断是否是admin权限
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(emailNumExcept != 0)
                s.put("error","1");
            else if(usernameNumExcept  != 0)
                s.put("error","2");
            else if(username.length() > 10 || username.length() < 2 || passwd.length()>35 || email.length() > 30 || (!type.equals("admin") && !type.equals("user")))
                s.put("error","3");
            else
            {
                //如果更新了密码，创建新的token
                int f = 0;
                if(userMapperObject.editUserDetail(email,username,type,passwd,id) == 1)
                {
                    s.put("error","0");
                    if(userMapperObject.getUserPasswdByUsername(username) != null && !userMapperObject.getUserPasswdByUsername(username).equals(passwd))
                        f = 1;
                    if(f == 1)
                    {
                        byte[] lock = new byte[0];
                        long w = 100000000;
                        long r = 0;
                        synchronized (lock) {
                            r = (long) ((Math.random() + 1) * w);
                        }
                        String newToken = System.currentTimeMillis() + String.valueOf(r).substring(1);
                        userMapperObject.createToken(username,newToken);
                    }
                }
                else
                    s.put("error","4");
            }
        }
        else
            s.put("error","5");
        return mapper.writeValueAsString(s);

    }

    @Override
    public String editUserDetailWithoutPasswd
            (String token,String email, String username, String type,int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        int emailNumExcept = userMapperObject.getEmailNumExpect(email,id);
        int usernameNumExcept = userMapperObject.getUsernameNumExpect(username,id);
        //error:1 email exist 2 username exist
        //3 format error 4 sql error
        //5 permission error
        //判断是否是admin权限
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(emailNumExcept != 0)
                s.put("error","1");
            else if(usernameNumExcept  != 0)
                s.put("error","2");
            else if(username.length() > 10 || username.length() < 2  || email.length() > 30 || (!type.equals("admin") && !type.equals("user")))
                s.put("error","3");
            else
            {
                if(userMapperObject.editUserDetailWithoutPasswd(email,username,type,id) == 1)
                    s.put("error","0");
                else
                    s.put("error","4");
            }
        }
        else
            s.put("error","5");
        return mapper.writeValueAsString(s);

    }

    @Override
    public String deleteUser(String token,int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(userMapperObject.deleteUser(id) == 1)
                s.put("error","0");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String updatePasswordByPrePassword(String token, String oldpasswd, String passwd) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        String username = userMapperObject.getUserNameByToken(token);
        //原密码不对 error 1
        //原密码对
        if(userMapperObject.loginCheck(username,oldpasswd) == 1)
        {
            //更改密码
            if(userMapperObject.updatePassword(username,passwd) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String uploadAvatar(MultipartFile file, String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        String UPLOAD_FOLDER = "./uploadFolder/avatar/";
        int id = userMapperObject.getUserIdByToken(token);
        if (id != 0) {
            String kzm = ".jpg";
            if (Objects.isNull(file)) {
                s.put("error", "-1");
                return mapper.writeValueAsString(s);
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER + id + kzm);
                if (!Files.isWritable(path)) {
                    Files.createDirectories(Paths.get(UPLOAD_FOLDER));
                }
                Files.write(path, bytes);
                s.put("error", "0");
            } catch (IOException e) {
                s.put("error", "-2");
            }
        }
        else
            s.put("error", "-3");
        return mapper.writeValueAsString(s);
    }

    @Override
    public byte[] getAvatarUrl(int id) throws IOException {
        byte[] bytes;
        FileInputStream inputStream;
        if (userMapperObject.getUserNameById(id) != null)
        {
            boolean flag = true;
            File avatarFile = new File("./uploadFolder/avatar/" + id + ".jpg");
            if (!avatarFile.exists()) {
                flag = false;
            }
            //存在,返回对应头像
            if (flag) {
                inputStream = new FileInputStream(avatarFile);
                bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
            }
            //不存在,返回默认头像
            else {
                inputStream = new FileInputStream("./uploadFolder/avatar/defaultAvatar.jpg");
                bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
            }
            return bytes;
        }
        else
        {
            inputStream = new FileInputStream("./uploadFolder/avatar/defaultAvatar.jpg");
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }
    }

    @Override
    public String getSystemInfo(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            s.put("userNum",userMapperObject.getUserNum());
            s.put("problemNum",problemMapperObject.getProblemSumAdmin());
            s.put("error",0);
        }
        else
            s.put("error",1);
        return  mapper.writeValueAsString(s);
    }
}
