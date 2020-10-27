package com.teleport.fwoj_backend.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    int id;
    String username;
    String passwd;
    String des;
    String avatar;
    String type;
    String email;
    String token;
    public user(String username,String token)
    {
        this.username = username;
        this.token = token;
    }
    public user(String email,String username,String passwd)
    {
        this.email = email;
        this.username = username;
        this.passwd = passwd;
    }
}
