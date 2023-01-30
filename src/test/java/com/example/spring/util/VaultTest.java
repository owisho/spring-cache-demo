package com.example.spring.util;

import org.junit.Test;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.support.Versioned;
import org.springframework.vault.core.VaultTemplate;

import java.util.HashMap;
import java.util.Map;

public class VaultTest {

    @Test
    public void test() {
        VaultEndpoint endpoint = new VaultEndpoint();
        endpoint.setHost("xxxxxx");
        endpoint.setPort(8200);
        endpoint.setScheme("http");

        VaultTemplate template = new VaultTemplate(endpoint,
                new TokenAuthentication("xxxxxxxx"));

        Map<String, String> data = new HashMap<>();
        data.put("java_password", "java_hashi123");

        Versioned.Metadata createResponse = template
                .opsForVersionedKeyValue("kv")
                .put("my-secret-password", data);

        System.out.println("Secret written successfully");

        Versioned<Map<String, Object>> readResponse = template
                .opsForVersionedKeyValue("kv")
                .get("my-secret-password");

        String password = "";
        if (readResponse != null && readResponse.hasData()){
            password = (String)readResponse.getData().get("java_password");
        }

        if(!password.equals("java_hashi123")){
            throw new RuntimeException("Unexpected password");
        }

        System.out.println("Access granted!");



    }

}
