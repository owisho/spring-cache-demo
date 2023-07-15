package com.example.spring.http.retrofit;

import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class RetrofitTest {

    @Test
    public void TestRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8891")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        ChatPptService service = retrofit.create(ChatPptService.class);
        Call<JSONObject> list = service.list();
        try {
            Response<JSONObject> resp = list.execute();
            System.out.println(resp.body().toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
