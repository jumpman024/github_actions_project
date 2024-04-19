package api.test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static api.consts.UrlParamValues.AUTH_QUERY_PARAMS;


public class BaseTest {

    @BeforeAll
    public static void setBaseUrl() {

        RestAssured.baseURI = "https://api.trello.com";

    }


    protected RequestSpecification requestWithAuth() {
        return RestAssured.given()
                .log().all()
                .queryParams(AUTH_QUERY_PARAMS);

    }

    protected RequestSpecification requestWithoutAuth() {
        return RestAssured.given()
                .log().all();
    }


}
