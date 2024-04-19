package api.test.delete;

import api.consts.BoardEndpoints;
import api.test.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static api.consts.UrlParamValues.ID_LIST;

public class DeleteCardTests extends BaseTest {



    private String cardId;

    @BeforeEach
    public void beforeTest() {

        Response response = requestWithAuth()
                .queryParams(Map.of("idList", ID_LIST,
                        "name", "superName"))
                .contentType(ContentType.JSON)
                .post(BoardEndpoints.CREATE_CARD_URL);
        response
                .then()
                .statusCode(200);
        cardId = response.getBody().jsonPath().get("id");

    }

    @Test
    public void deleteCardTest() {

        requestWithAuth()
                .pathParam("id", cardId)
                .delete(BoardEndpoints.DELETE_CARDS_URL)
                .then()
                .statusCode(200)
                .body("limits", Matchers.equalTo(Collections.emptyMap()));


        Response response = requestWithAuth()
                .pathParam("id", ID_LIST)
                .get(BoardEndpoints.GET_LIST_CARDS_URL);
        response
                .then()
                .log().body()
                .body(Matchers.not(Matchers.hasItem(cardId)));


    }

}
