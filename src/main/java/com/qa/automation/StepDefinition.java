package com.qa.automation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.IOException;

public class StepDefinition {

    public StepDefinition()
    {
       RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    int statusCode = 0;
    public static Response response = null;
    utils utils = new utils();

/**
java doc -> adding exception
**/
    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws IOException  {
        response =  utils.getResponse(url);
    }

    @Then("^I should get the StatusCode \"([^\"]*)\"$")
    public void iShouldGetTheStatusCode(String code)  {
        statusCode = utils.getStatusCode();
        Assert.assertEquals(statusCode, Integer.parseInt(code));
    }

    @Then("^I should get the \"([^\"]*)\" as \"([^\"]*)\"$")
    public void iShouldGetTheAs(String HeaderType, String expectedHeader)  {
        String actualHeader = utils.getHeader(HeaderType);
        Assert.assertEquals(actualHeader,expectedHeader);
    }

    @Given("^I perform GET operation for \"([^\"]*)\" with \"([^\"]*)\" = \"([^\"]*)\"$")
    public void iPerformGETOperationForWithQueryParameter(String url, String parameter, String value) {
        response= utils.getResponseWithParameter(url, parameter, value);
    }

    @Then("^I should get the ID values as \"([^\"]*)\" in the response body$")
    public void iShouldGetTheIDValuesAsInTheResponseBody(String expectedValue) throws IOException {
        String actualValue = utils.getResponseIdValue();
        Assert.assertEquals(actualValue,expectedValue);
    }

    @Given("^I perform POST operation for \"([^\"]*)\" with payload at \"([^\"]*)\"$")
    public void iPerformPOSTOperationFor(String url, String jsonPath) throws IOException {
        response = utils.postResponse(url, jsonPath);
    }

    @Then("^I should get the status code as \"([^\"]*)\"$")
    public void iShouldGetTheStatusCodeAs(String expectedStatusCode)  {
        statusCode = utils.getStatusCode();
        Assert.assertEquals(statusCode,Integer.parseInt(expectedStatusCode));
    }

    @Then("^I should get the response \"([^\"]*)\" as \"([^\"]*)\"$")
    public void iShouldGetTheResponseAs(String data, String expectedValue) {
        int actualValue = 0;
        String actualValueType = null;
        String res = response.getBody().asString();
        JSONObject jObject = new JSONObject(res);
        if(data.equals("id")){ actualValue = jObject.getInt(data);
            Assert.assertEquals(String.valueOf(actualValue), expectedValue);}
        else {
            actualValueType = jObject.getString(data);
            Assert.assertEquals(actualValueType, expectedValue);
        }

    }
}
