package com.herokuapp.restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeadersAndCookies extends BaseTest {

    @Test
    public void headersAndCookiesTest() {

//      Headers and cookies can be declared with Header and Cookie class and add them to spec
        Header someHeader = new Header("New header", "Additional data for request");
        spec.header(someHeader);
        Cookie someCookie = new Cookie.Builder("New cookie", "Additional data for request").build();
        spec.cookie(someCookie);
//      To add cookies and headers we need to say log().all(). after given(spec).

        Response response = RestAssured.given(spec).log().all().get("/ping");
        Assert.assertEquals(response.getStatusCode(), 201);

//      Get all Headers
        Headers headers = response.getHeaders();
        System.out.println("Headers: " + headers);

//      Get all Cookies
        Cookies cookies = response.getDetailedCookies();
        System.out.println("Cookies: " + cookies);

//      Get single header / cookie
        Header serverHeader1 = headers.get("Server");
        System.out.println(serverHeader1.getName() + ": " + serverHeader1.getValue());
//      Second way, when we want only value returned:
        String serverHeader2 = response.getHeader("Server");
        System.out.println("Server: " + serverHeader2);



//      Adding cookies and headers:
        Response responseCookieAndHeaders = RestAssured.given(spec).
//                auth().preemptive().basic("admin", "password123").
                cookie("Test cookie name", "Value").
                header("Test header name", "Value").
//      To add cookies and headers we need to say log().all(). after given(spec).
                log().all().
                get("/ping");

        Cookies newCookies = responseCookieAndHeaders.getDetailedCookies();
        System.out.println("New cookies: " + newCookies);
        Headers newHeaders = responseCookieAndHeaders.getHeaders();
        System.out.println("New headers: " + newHeaders);

    }
}
