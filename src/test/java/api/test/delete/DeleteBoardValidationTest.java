package api.test.delete;

import api.arguments.holders.AuthValidationArgumentsHolder;
import api.arguments.holders.BoardIdValidationArgumentsHolder;
import api.arguments.providers.AuthValidationArgumentsProvider;
import api.arguments.providers.BoardIdValidationArgumentsProvider;
import api.consts.BoardEndpoints;
import api.test.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static api.consts.UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS;
import static api.consts.UrlParamValues.EXISTING_BOARD_ID;

public class DeleteBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder) {

        Response response = requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .delete(BoardEndpoints.DELETE_BOARD_URL);
        response.then().statusCode(argumentsHolder.getStatusCode());
        Assertions.assertEquals(argumentsHolder.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {

        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", EXISTING_BOARD_ID)
                .delete(BoardEndpoints.DELETE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());


    }

    @Test
    public void checkDeleteBoardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", EXISTING_BOARD_ID)
                .delete(BoardEndpoints.DELETE_BOARD_URL);
        response
                .then()
                .log().body()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }

}
