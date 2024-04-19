package api.test.get;

import api.consts.BoardEndpoints;
import api.consts.UrlParamValues;
import api.test.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@Log4j
public class GetBoardsTest extends BaseTest {



    @Test
    public void checkGetBoards() {

        requestWithAuth()
                .queryParam("fields","id,name")
                .pathParam("member", UrlParamValues.USER_NAME)
                .get(BoardEndpoints.GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }



    @Test
    public void checkGetBoard() {
        requestWithAuth()
                .queryParam("fields","id,name")
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .get("/1/boards/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Test"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"))
                .extract().response().prettyPrint();

    }

    @Test
    public void checkGetCards() {

        requestWithAuth()
                .pathParam("list_id","66053febe789a7abdefd30ea")
                .log().everything()
                .get("/1/lists/{list_id}/cards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"))
                .extract().response().prettyPeek();

    }

    @Test
    public void checkGetCard() {

        requestWithAuth()
                .pathParam("id","66053feca1bb9e49a3703cd4")
                .get("/1/cards/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Kickoff meeting"));
    }


}
