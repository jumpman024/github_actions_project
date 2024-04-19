package api.test.create;

import api.arguments.holders.AuthValidationArgumentsHolder;
import api.arguments.providers.BoardNameValidationArgumentsProvider;
import api.consts.BoardEndpoints;
import api.test.BaseTest;
import api.arguments.providers.AuthValidationArgumentsProviderForCreateBoard;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Map;

public class CreateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardNameValidationArgumentsProvider.class)
    public void checkCreateBoardWithInvalidName(Map bodyParams) {

        Response response = requestWithAuth()
                .body(bodyParams)
                .contentType(ContentType.JSON)
                .post(BoardEndpoints.CREATE_BOARD_URL);
        response.then().statusCode(400);
        Assertions.assertEquals("invalid value for name", response.body().jsonPath().get("message"));
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProviderForCreateBoard.class)
    public void checkCreateBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {

        Response response = requestWithoutAuth()
                .body(Map.of("name", "new board"))
                .contentType(ContentType.JSON)
                .log().all()
                .post(BoardEndpoints.CREATE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());

    }



}
