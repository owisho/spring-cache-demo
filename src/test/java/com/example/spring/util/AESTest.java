package com.example.spring.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class AESTest {

    @Test
    public void Test() {
        try {
            //加密密码
            String key = "opera110crypt110opera110crypt110";
            //偏移量
            String iv = "13579246800987651357924680098765";
            String message = "今天是周二，我好开心";
            //加密
            byte[] encryResult = AES.encryptAes(message.getBytes(), key.getBytes(), iv.getBytes());
            //解密
            byte[] decryResult = AES.decryptAesToByteString(encryResult, key.getBytes(), iv.getBytes());
            System.out.println("解密数据 = " + new String(decryResult));

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DemoData() throws IOException, GeneralSecurityException {

        //加密密码
        String key = "12345678901234567890123456789012";
        //偏移量
        String iv = "1357924680098765";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/wangyang/Downloads/query_result_2023-01-17T09_53_53.278005Z.csv")));
        String line = reader.readLine();
        int i = -1;
        while (line != null) {
            i++;
            if (i == 0) {
                line = reader.readLine();
                continue;
            }

            String[] arr = line.split(",");
            String id = arr[0].trim();
            String email = arr[1].trim();
            String imgUrl = arr[2].trim();
            String name = arr[3].trim();
//            System.out.println(String.format("original:id=%s,email=%s,imgUrl=%s,name=%s", id, email, imgUrl, name));

            String encryptEmail = Base64.getEncoder().encodeToString(AES.encryptAes(email.getBytes("UTF-8"), key.getBytes(), iv.getBytes()));
            String encryptUrl = Base64.getEncoder().encodeToString(AES.encryptAes(imgUrl.getBytes("UTF-8"), key.getBytes(), iv.getBytes()));
            String encryptName = Base64.getEncoder().encodeToString(AES.encryptAes(name.getBytes("UTF-8"), key.getBytes(), iv.getBytes()));
//            System.out.println(String.format("encrypt: id=%s,email=%s,imgUrl=%s,name=%s", id, encryptEmail, encryptUrl, encryptName));

            String decryptEmail = new String(AES.decryptAesToByteString(Base64.getDecoder().decode(encryptEmail), key.getBytes(), iv.getBytes()), "UTF-8");
            String decryptUrl = new String(AES.decryptAesToByteString(Base64.getDecoder().decode(encryptUrl), key.getBytes(), iv.getBytes()), "UTF-8");
            String decryptName = new String(AES.decryptAesToByteString(Base64.getDecoder().decode(encryptName), key.getBytes(), iv.getBytes()), "UTF-8");
//            System.out.println(String.format("decrypt: id=%s,email=%s,imgUrl=%s,name=%s", id, decryptEmail, decryptUrl, decryptName));

            System.out.println(String.format("update facebook_user set email='%s',profile_image_url='%s',name='%s' where id='%s';", encryptEmail, encryptUrl, encryptName, id));

            Assert.assertEquals(email, decryptEmail);
            Assert.assertEquals(imgUrl, decryptUrl);
            Assert.assertEquals(name, decryptName);

            line = reader.readLine();

        }

    }


}
