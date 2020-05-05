package com.testerhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

class DepartmentTest {
    Department department;
    String random=String.valueOf(System.currentTimeMillis());

    @BeforeEach
    void setUp() {
        if(department==null){
            department=new Department();
            department.deleteAll();
        }

    }

    @Test
    void list() {

        department.list("2").then().statusCode(200)
                .body("department.name[0]", equalTo("nuaa"));

    }

    @Test
    void create() {
        department.create("hogwarts"+random,1).then().body("errcode",equalTo(0));
        department.create("南京大学nj"+random,1).then().body("errcode",equalTo(0));
    }

    @Test
    void createByMap(){
        HashMap<String, Object> map=new HashMap<String, Object>(){{
            put("name",String.format("sisul"+random));
            put("parentid",1);

        }
        };
        department.create(map).then().body("errcode", equalTo(0));
    }


    @Test
    void delete() {
        String nameOld="iiii";
        department.create(nameOld,1);
        Integer id=department.list("").path("department.find{it.name=='"+nameOld+"'}.id");
        department.delete(id).then().body("errcode",equalTo(0));
    }

    @Test
    void update() {
        String nameOld="imooc5"+random;
        department.create(nameOld,1);
        Integer id=department.list("").path("department.find{ it.name=='"+nameOld+"'}.id");
        System.out.println("---------------------");
        System.out.println(id);
        department.update("nnnn"+random, id).then().body("errcode",equalTo(0));
    }

    @Test
    void deleteAll() {
        department.deleteAll();
    }
}