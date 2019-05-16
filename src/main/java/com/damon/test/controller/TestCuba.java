package com.damon.test.controller;

import com.damon.test.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestCuba {

    private static final Logger log = LoggerFactory.getLogger(TestCuba.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String APP_SECRET = "63182ed1a555c3f37386cc6c175b38b2927af74c3244b380";

    private static final String APP_HOST = "http://10.138.111.13/cuba/service";

    private static final String GET_TENEMENTS = "/plat/api/user/tenements";

    @GetMapping("getTenements")
    public Object testGetTenements() {
        String uri = APP_HOST + GET_TENEMENTS;
        Map<String, String> param = new HashMap<>();
        param.put("authorityType", "all");
        param.put("ucUserId", "198");
        StringBuilder paramBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            paramBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        String cipher = paramBuilder.toString() + "key=" + APP_SECRET;
        String md5cipher = Md5Util.string2MD5(cipher);
        String uriParam = paramBuilder.append("cipherText=")
                .append(md5cipher)
                .toString();

        uri += "?" + uriParam;

        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
        return result;
    }

    @PostMapping("postTenements")
    public Object testPostTenements(@RequestParam("authorityType") String authorityType,
                                    @RequestParam("ucUserId") String ucUserId) {
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("authorityType", authorityType);
        param.add("ucUserId", ucUserId);

        StringBuilder cipher = new StringBuilder();
        for (Map.Entry<String, String> entry : param.toSingleValueMap().entrySet()) {
            cipher.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        String cipherText = cipher.append("key=")
                .append(APP_SECRET)
                .toString();
        String md5CipherText = Md5Util.string2MD5(cipherText);
        param.add("cipherText", md5CipherText);

        URI uri = new DefaultUriBuilderFactory().uriString(APP_HOST)
                .path(GET_TENEMENTS)
                .queryParams(param)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(param, headers);

        Object result = restTemplate.postForEntity(uri, requestEntity, Object.class).getBody();
        System.out.println(result);
        return result;
    }
}
