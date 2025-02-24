package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.config.Config;
import com.restfulbooker.api.payloads.Auth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static String token;

    @BeforeAll
    public static void setUp() {
        RestAssured.useRelaxedHTTPSValidation();
        token = getToken();
    }
    private static String getToken() {
        String username = Config.getProperty("username");
        String password = Config.getProperty("password");

        Auth auth = new Auth.Builder()
                .setUsername(username)
                .setPassword(password)
                .build();
        Response response = AuthApi.postAuth(auth);
        return response.getBody().jsonPath().get("token").toString();
    }
}
