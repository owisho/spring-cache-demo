package com.example.spring.http.retrofit;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.Date;
import java.util.List;

public interface ChatPptService {

    @GET("/chat-ppt/ppt/list")
    Call<JSONObject> list();

    @Data
    class PptVO {
        private String pptId;
        private String name;
        private String theme;
        private String outline;
        private String content;
        private Date createTime;
        private Date updateTime;
    }

}

