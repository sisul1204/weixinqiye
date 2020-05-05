package com.testerhome.hogwarts.wework;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.equalTo;

public class Wework {

    private static String token;

    public static String getWeworkToken(String secret){
        return RestAssured.given().log().all()
                .queryParam("corpid", WeworkConfig.getInstance().corpid)
                .queryParam("corpsecret", secret)
                .when()
                    .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().statusCode(200)
                    .body("errcode", equalTo(0))
                    .extract().path("access_token");
    }





    public static String getToken(){
        if(token==null){
            token=getWeworkToken(WeworkConfig.getInstance().contactsecret);
        }
        return token;

    }





}
