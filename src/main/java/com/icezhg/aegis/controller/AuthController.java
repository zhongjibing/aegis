package com.icezhg.aegis.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.icezhg.aegis.util.HttpClientFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/auth")
    public void token(@RequestParam Map<String, String> parameters) {
        LOGGER.info("param: {}", parameters);

        CloseableHttpClient httpClient = HttpClientFactory.simpleHttpClient();

        try {
            String code = parameters.get("code");
            String url = "http://localhost:8080/oauth/token?grant_type=authorization_code&code=" + code + "&client_id=my-trusted-client&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fauth";
            HttpGet get = new HttpGet(url);

            String auth = "my-trusted-client:secret";
            String base64String = Base64.encodeBase64String(auth.getBytes("UTF-8"));
            get.addHeader("Authorization", "Basic " + base64String);
            CloseableHttpResponse response = httpClient.execute(get);

            int statusCode = response.getStatusLine().getStatusCode();
            LOGGER.info("response status: {}", statusCode);

            InputStream stream = response.getEntity().getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            LOGGER.info("result: {}", result);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
