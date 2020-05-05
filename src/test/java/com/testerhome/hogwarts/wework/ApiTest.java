package com.testerhome.hogwarts.wework;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class ApiTest {

    @Test
    void templateFromYaml() {
        Api api=new Api();
        api.templateFromYaml("/api/list.yaml",null).then().statusCode(200);
    }


    @Test
        void request(){
        RequestSpecification req=given().log().all();
        req.queryParam("id",1);
        req.queryParam("d","xxxxxxx");
        req.get("http://www.baidu.com");

    }

    @Test
    void resource(){
        URL url=getClass().getResource("/api/app.har.json");
        System.out.println(url.getFile());
        System.out.println(url.getPath());
    }
}