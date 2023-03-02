package com.stl.EmployeeBackend;


import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;


@SuppressWarnings("unused")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeBackendApplicationTests {

    @Test
    @Order(1)
    public void testSignup() {
        String jsonbody="{\"firstname\":\"Pavan\",\"lastname\":\"Kusampudi\",\"email\":\"pavan@gmail.com\",\"phonenumber\":\"6303552597\",\"password\":\"pavan0209\"}";
        String res=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonbody)
                .when()
                .post("http://localhost:8082/Employees/add")
                .then()
                .assertThat().statusCode(201)
                .extract().response().asString();
    }
    
    @Test
    @Order(2)
    public void testLogin() {
        String jsonbody= "{\"email\" : \"pavan@gmail.com\",\"password\" : \"pavan0209\"}";
        String token=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonbody)
                .when()
                .post("http://localhost:8082/Employees/authenticate")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
    
    @Test
    @Order(3)
    public void testgetallEmployees() {
        String result=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get("http://localhost:8082/Employees/get")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
    
    @Test
    @Order(4)
    public void testgetEmployees() {
        String result=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get("http://localhost:8082/Employees/get/pavan@gmail.com")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
    
    @Test
    @Order(5)
    public void testdeleteEmployee() {
        String jsonbody= "{\"email\" : \"pavan@gmail.com\",\"password\" : \"pavan0209\"}";
        String token=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonbody)
                .when()
                .post("http://localhost:8082/Employees/authenticate")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
        
        String result=given()
                .header("Authorization","Bearer "+token).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .delete("http://localhost:8082/Employees/delete/pavan@gmail.com")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
}
