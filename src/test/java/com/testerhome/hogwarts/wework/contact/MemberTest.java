package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;


class MemberTest {

    static Member member;
    @BeforeAll
    static void setUp() {
        member=new Member();
    }

    @ParameterizedTest
//    @ValueSource(strings={"sisul_","hogwarts_","testerhome_"})
    @CsvFileSource(resources = "/data/members.csv")
    void create(String name,String alias) {
        String nameNew=name+member.random;
        String random=String.valueOf(System.currentTimeMillis()).substring(5+0,5+8);
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("userid",nameNew);
        map.put("name",name);
        map.put("alias",alias);
        map.put("mobile","182"+random);
        map.put("department", Arrays.asList(1,2));
        map.put("email",random+"@qq.com");
        member.create(map).then().statusCode(200).body("errcode",equalTo(0));
    }
}