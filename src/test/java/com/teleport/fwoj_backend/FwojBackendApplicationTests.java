package com.teleport.fwoj_backend;

//import com.teleport.fwoj_backend.mapper.contestMapper;
import com.teleport.fwoj_backend.mapper.contestMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FwojBackendApplicationTests {

    @Autowired
    userMapper userMapperObject;
    @Autowired
    userService userServiceObject;
    @Test
    void contextLoads() {
        System.out.println(userServiceObject.createToken("11"));
    }

}
