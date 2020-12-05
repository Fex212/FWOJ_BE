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
    boolean available;
    String token;
    String github;
    String sign;
    String site;
}
