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
        Call<JSONObject> list = service.list("Bearer 17Arn27EcLY12yJjtjPPl2bDz_hTN2Ku0pyRWqSmNMEMf803LGtbWohlHiETi4NIqMBg76iMFwHoVDN4tkONxF6PU1Vaw_1v7IEYEHJH3EeQ9PsOGSLeC_o2rTApgVA_8H237v-ao8RXVfy5pTv_TE6FpjnIlp5lfb5Lt_DTwh_WX6AUldpBW4CrQ3MeEj026mAJjymzy1Yvp-H_2btSlTF2A995nrVpPxcckRJ3xOhZh0fW0Kqvq_BIQJ_h9tb__ZM-0YQOS75CxMuf20FAfdYr9kY9vw7DlbK79Nf6UBK8aKWKYlH6RvoJkayYR4IC6VN4BUBhkmpjV7UvD3cLTw.cv0");
        try {
            Response<JSONObject> resp = list.execute();
            System.out.println(resp.body().toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
