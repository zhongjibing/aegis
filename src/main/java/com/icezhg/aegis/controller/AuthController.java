package com.icezhg.aegis.controller;

import java.io.IOException;
import java.util.Map;

import com.icezhg.aegis.domain.AuthTokenInfo;
import com.icezhg.aegis.util.JsonUtil;
import com.icezhg.aegis.util.RestUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/auth")
    public AuthTokenInfo token(@RequestParam Map<String, String> parameters) {
        LOGGER.info("param: {}", parameters);

        try {
            String code = parameters.get("code");
            String url = "http://localhost:8080/oauth/token?grant_type=authorization_code&code=" + code + "&client_id=my-trusted-client&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fauth";
            HttpGet get = new HttpGet(url);

            String auth = "my-trusted-client:secret";
            String base64String = Base64.encodeBase64String(auth.getBytes("UTF-8"));
            get.addHeader("Authorization", "Basic " + base64String);
            String result = RestUtil.execute(get);

            LOGGER.info("result: {}", result);

            AuthTokenInfo tokenInfo = JsonUtil.toBean(result, AuthTokenInfo.class);
            LOGGER.info("tokenInfo:{}", tokenInfo);
            return tokenInfo;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }
}
