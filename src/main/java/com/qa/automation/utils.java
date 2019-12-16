package com.qa.automation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class utils {

    public static Response response = null;
    String path = System.getProperty("user.dir");
    int statusCode = 0;

    public Response getResponse(String url)
    {
        return response = given().when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public int getStatusCode() {
        return statusCode = response.getStatusCode();
    }

    public  String getHeader(String HeaderType) {
        String actualHeader = response.header(HeaderType);
        String[] str = actualHeader.split(";");
        actualHeader = str[0];
        return actualHeader;
    }

    public Response getResponseWithParameter(String url, String parameter, String value)
    {
        return response= given().param(parameter, value).get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public String getResponseIdValue() {
        String res = response.getBody().asString();
        HashMap<String, Object> map = getJsonNodeValue(res);
        return map.get("id").toString();
    }

    private HashMap<String, Object> getJsonNodeValue(String res)
    {
        HashMap<String, Object> map = null;
        List<HashMap<String, Object>> list = response.jsonPath().getList("");
        int size = list.size();
        for(int i = 0; i<size; i++)
        {
            map = list.get(i);
        }
        return map;
    }

    public Response postResponse(String url, String jsonPath) throws IOException {
        String file = path +jsonPath;
        String jsonBody = generateStringFromResources(file);
        return response = given().contentType("application/json").body(jsonBody).when().
                post(url).then().extract().response();
    }

    private String generateStringFromResources(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
