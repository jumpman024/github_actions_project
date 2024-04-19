package api.test.get;

import api.arguments.holders.AuthValidationArgumentsHolder;
import api.arguments.providers.AuthValidationArgumentsProvider;
import api.consts.BoardEndpoints;
import api.arguments.holders.CardIdValidationArgumentsHolder;
import api.arguments.providers.CardIdValidationArgumentsProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import api.test.BaseTest;

import static api.consts.UrlParamValues.EXISTING_BOARD_ID;

public class GetCardIdValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void getCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments) {

        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get("/1/cards/{id}");
        response.then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());

    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {

        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id",EXISTING_BOARD_ID)
                .log().all()
                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());

    }



}
