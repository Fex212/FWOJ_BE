package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
public class userServiceImpl implements userService{

    @Autowired
    private userMapper userMapperObject;

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
    public boolean getAvailableByUsername(String username) {
        if(userMapperObject.getAvailableByUsername(username) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setAvailableByUsername(String username, boolean available) {
        if(userMapperObject.setAvailableByUsername(username,available) == 1)
            return true;
        else
            return false;
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
        if(token == null)
            return null;
        HashMap s = new HashMap();
        s.put("userId",userMapperObject.getUserIdByToken(token));
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
    public String getUserEmail(String token) {
        return userMapperObject.getUserEmailByToken(token);
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
    public String updateUserPersonInfo(String token, String sign, String site, String github) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.updateUserSiteByToken(token,site) == 1 &&
            userMapperObject.updateUserGithubByToken(token,github) == 1 &&
            userMapperObject.updateUserSignByToken(token,sign) == 1)
            s.put("error","0");
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getUserCardInfo(String username) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        //type,sign,site,github
        String type = userMapperObject.getUserTypeByUsername(username);
        String sign = userMapperObject.getUserSignByUsername(username);
        String site = userMapperObject.getUserSiteByUsername(username);
        String github = userMapperObject.getUserGithubByUsername(username);
        if(type != null)
        {
            s.put("error","0");
            s.put("type",type);
            s.put("site",site);
            s.put("sign",sign);
            s.put("github",github);
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public boolean emailExist(String email) {
        if(userMapperObject.emailExist(email) == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean usernameIsExist(String username) {
        if(userMapperObject.usernameExist(username) == 0)
            return false;
        else
            return true;
    }

    @Override
    public int getEmailNumExpect(String email, int id) {
        return userMapperObject.getEmailNumExpect(email,id);
    }

    @Override
    public int getUsernameNumExpect(String username, int id) {
        return userMapperObject.getUsernameNumExpect(username,id);
    }


    @Override
    public List<user> getUserList(Integer page, Integer pre,String key) {

        int start = pre * (page - 1);
        int num = pre;
        return userMapperObject.getUserList(start,num,key);
    }

    @Override
    public int getUserNum() {
        return userMapperObject.getUserNum();
    }

    @Override
    public boolean tokenIsAdmin(String token) {
        if(userMapperObject.getTypeByToken(token) != null && userMapperObject.getTypeByToken(token).equals("admin"))
            return true;
        else
            return false;
    }

    @Override
    public user getUserDetailById(int id) {
        return userMapperObject.getUserDetailById(id);
    }

    @Override
    public boolean editUserDetail(String email, String username, String type, String des, String passwd,int id) {
        if(userMapperObject.editUserDetail(email,username,type,des,passwd,id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean editUserDetailWithoutPasswd(String email, String username, String type, String des,int id) {
        if(userMapperObject.editUserDetailWithoutPasswd(email,username,type,des,id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteUser(int id) {
        if(userMapperObject.deleteUser(id) == 1)
            return true;
        else
            return false;
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
        String username = userMapperObject.getUserNameByToken(token);
        if (username != null) {
            String kzm = ".jpg";
            if (Objects.isNull(file)) {
                s.put("error", "-1");
                return mapper.writeValueAsString(s);
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER + username + kzm);
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
    public byte[] getAvatarUrl(String username) throws IOException {
        byte[] bytes;
        FileInputStream inputStream;
        if (username != null)
        {
            boolean flag = true;
            File avatarFile = new File("./uploadFolder/avatar/" + username + ".jpg");
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
                inputStream = new FileInputStream("./uploadFolder/defaultAvatar.jpg");
                bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
            }
            return bytes;
        }
        else
        {
            inputStream = new FileInputStream("./uploadFolder/defaultAvatar.jpg");
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }
    }
}
