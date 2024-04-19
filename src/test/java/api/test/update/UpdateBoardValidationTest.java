package api.test.update;

import api.arguments.holders.AuthValidationArgumentsHolder;
import api.arguments.holders.BoardIdValidationArgumentsHolder;
import api.arguments.providers.AuthValidationArgumentsProvider;
import api.arguments.providers.BoardIdValidationArgumentsProvider;
import api.consts.BoardEndpoints;
import api.test.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Map;

import static api.consts.UrlParamValues.*;

public class UpdateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidId(BoardIdValidationArgumentsHolder validationArguments) {

        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .put(BoardEndpoints.UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(),
                response.body().asString());

    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {

        Response response = requestWithoutAuth()
                .pathParam("id", EXISTING_BOARD_ID)
                .queryParams(validationArguments.getAuthParams())
                .body(Map.of("name", "Updated Name"))
                .contentType(ContentType.JSON)
                .put(BoardEndpoints.UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());

    }

    @Test
    public void checkUpdateBoardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", "60e03f8328428d54e3f62252")
                .body(Map.of("name", "Updated Name"))
                .contentType(ContentType.JSON)
                .put(BoardEndpoints.UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }


}
