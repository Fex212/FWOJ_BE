package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface rankService{
    String  getRank(int page, int pre) throws JsonProcessingException;
    String getRankChartData() throws JsonProcessingException;
}
