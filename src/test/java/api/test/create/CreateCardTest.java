package api.test.create;

import api.consts.BoardEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import api.test.BaseTest;

import java.util.Map;
import java.util.Random;

import static api.consts.UrlParamValues.ID_LIST;

public class CreateCardTest extends BaseTest {

    private String cardName;
    private String createdCardId;

    @Test
    public void createNewCard() {

//        cardId = RandomStringUtils.random(24, "0123456789abcdefABCDEF");
//        cardId = generateCardId();

        Response response =
                requestWithAuth()
                        .queryParams(Map.of("idList", ID_LIST))
                        .contentType(ContentType.JSON)
                        .post(BoardEndpoints.CREATE_CARD_URL);
        response
                        .then()
                        .statusCode(200)
                        .body("id", Matchers.equalTo(response.body().jsonPath().get("id")));
        createdCardId = response.getBody().jsonPath().getString("id");
        requestWithAuth()
                .pathParam("id",createdCardId)
                .get(BoardEndpoints.GET_CARDS_URL)
                .then()
                .statusCode(200)
                .body("id",Matchers.equalTo(createdCardId));

    }

    private static String generateCardId() {
        final String HEX_CHARS = "0123456789abcdefABCDEF";
        final int LENGTH = 24;

        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(HEX_CHARS.length());
            char randomChar = HEX_CHARS.charAt(randomIndex);
            sb.append(randomChar);

        }

        return sb.toString().toLowerCase();
    }

    @AfterEach
    public void deleteCard() {

        requestWithAuth()
                .pathParam("id", createdCardId)
                .delete(BoardEndpoints.DELETE_CARDS_URL)
                .then()
                .statusCode(200);

    }
}
