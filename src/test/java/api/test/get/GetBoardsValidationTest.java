package api.test.get;

import api.arguments.holders.AuthValidationArgumentsHolder;
import api.arguments.holders.BoardIdValidationArgumentsHolder;
import api.arguments.providers.AuthValidationArgumentsProvider;
import api.arguments.providers.BoardIdValidationArgumentsProvider;
import api.consts.BoardEndpoints;
import api.consts.UrlParamValues;
import api.test.BaseTest;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

@Log4j
public class GetBoardsValidationTest extends BaseTest {


    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidId(BoardIdValidationArgumentsHolder validationArguments) {

        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {

        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .log().all()
                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());

    }


    @Test
    public void checkGetCardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", "60e03f8328428d54e3f62252")
                .get(BoardEndpoints.GET_CARDS_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }


}
