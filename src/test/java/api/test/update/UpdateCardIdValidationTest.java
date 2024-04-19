package api.test.update;

import api.arguments.holders.AuthValidationArgumentsHolder;
import api.arguments.holders.CardIdValidationArgumentsHolder;
import api.arguments.providers.AuthValidationArgumentsProviderForCards;
import api.arguments.providers.CardIdValidationArgumentsProvider;
import api.consts.BoardEndpoints;
import api.test.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Map;

import static api.consts.UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS;
import static api.consts.UrlParamValues.CARD_ID_TO_UPDATE;

public class UpdateCardIdValidationTest extends BaseTest {


    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkUpdateCardWithInvalidId(CardIdValidationArgumentsHolder argumentsHolder) {

        Response response =
                requestWithAuth()
                        .pathParams(argumentsHolder.getPathParams())
                        .put(BoardEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(argumentsHolder.getStatusCode());
        Assertions.assertEquals(argumentsHolder.getErrorMessage(), response.body().asString());

    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProviderForCards.class)
    public void checkUpdateCardWithInvalidCredentials(AuthValidationArgumentsHolder argumentsHolder) {


        Response response = requestWithoutAuth()
                .pathParam("id", CARD_ID_TO_UPDATE)
                .queryParams(argumentsHolder.getAuthParams())
                .body(Map.of("name", "Updated Name"))
                .contentType(ContentType.JSON)
                .log().all()
                .put(BoardEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(argumentsHolder.getErrorMessage(), response.body().asString());

    }

    @Test
    public void checkUpdateCardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", CARD_ID_TO_UPDATE)
                .body(Map.of("name", "Updated Name"))
                .contentType(ContentType.JSON)
                .put(BoardEndpoints.UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }

}
