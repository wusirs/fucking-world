package com.world.fucking.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class RestTemplateTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

// 文件资源
        FileSystemResource fileResource =
                new FileSystemResource(new File("E:\\Users\\heisenberg\\Pictures\\Screenshots\\AA.png"));

// 表单参数
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);
        body.add("userId", "1001");
        body.add("type", "avatar");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(body, headers);


        String url = "http://localhost:8089/fucking-world/document/upload";
        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println(response);
    }
}
