package com.teleport.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.pojo.ann;
import com.teleport.service.annService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {


    @Autowired
    @Qualifier("annServiceImpl")
    private annService annServiceObject;

    @RequestMapping(value = "/test",method = {RequestMethod.POST,RequestMethod.GET})
    public String getAnnouncementList() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<ann> list = annServiceObject.getAnnList(1,1);

        return  mapper.writeValueAsString(list);

    }


}
