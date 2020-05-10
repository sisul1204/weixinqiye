package com.testerhome.hogwarts.wework.contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.testerhome.hogwarts.wework.Wework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Department extends Contact{
    public Response list(String id){

        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("id",id);

        return templateFromYaml("/api/list.yaml",map);

    }

    public Response create(String name, Integer parentid){
        //让用例更清晰
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("parentid",parentid);
        map.put("_file","/data/create.json");

        return templateFromYaml("/api/create.yaml",map);


    }

    public Response create(HashMap<String, Object> map){
        map.put("_file","/data/create.json");
        return templateFromYaml("/api/create.yaml",map);
    }


    public Response createMap(HashMap<String, Object> map){

        DocumentContext documentContext=JsonPath.parse(this.getClass()
                            .getResourceAsStream("/data/create.json"));

        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(),entry.getValue());
        });
        return getDefaultRequestSpecification()
                .body(documentContext.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().extract().response();

    }




    public Response delete(Integer id){

        HashMap<String,Object> map=new HashMap<>();
        map.put("id",id);
        return templateFromYaml("/api/delete.yaml",map);
    }

    public Response update(String name, Integer id){

        HashMap<String,Object> map=new HashMap<>();
        map.put("_file","/data/update.json");
        map.put("name",name);
        map.put("id",id);
        return templateFromYaml("/api/update.yaml",map);

    }


    public Response deleteAll(){

        List<Integer> idList=list("").then().log().all().extract().path("department.id");
        System.out.println(idList);
        idList.forEach(id->delete(id));
        return null;

    }

    public Response update(HashMap<String, Object> map)throws Exception{
        return templateFromHar(
                "demo.har.json",
                "https://work.weixin.qq.com/wework_admin/party?action=addparty",
                map);

    }

    public Response updateAll(HashMap<String,Object>map){
        return api("api.json", map);
    }








}
