package api.test.update;

import api.consts.BoardEndpoints;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import api.test.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static api.consts.UrlParamValues.CARD_ID_TO_UPDATE;

public class UpdateCardIdTest extends BaseTest {

    private String nameForUpdateCard = "New Card Name" + LocalDateTime.now();

    @Test
    public void updateExistingCard() {

        requestWithAuth()
                .pathParam("id", CARD_ID_TO_UPDATE)
                .body(Map.of("name", nameForUpdateCard))
                .contentType(ContentType.JSON)
                .put(BoardEndpoints.UPDATE_CARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(nameForUpdateCard));

        requestWithAuth()
                .pathParam("id", CARD_ID_TO_UPDATE)
                .get(BoardEndpoints.GET_CARDS_URL)
                .then()
                .log().body()
                .body("name", Matchers.equalTo(nameForUpdateCard));
    }



}
