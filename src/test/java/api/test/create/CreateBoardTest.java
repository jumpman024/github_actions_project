package api.test.create;

import api.consts.BoardEndpoints;
import api.test.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static api.consts.UrlParamValues.USER_NAME;

public class CreateBoardTest extends BaseTest {

    private String createdBoardId;

    @Test
    public void checkCreateBoard() {

        String boardName = "New Board" + LocalDateTime.now();

        Response response = requestWithAuth()
                .body(Map.of("name", boardName))
                .contentType(ContentType.JSON)
                .post(BoardEndpoints.CREATE_BOARD_URL);
        createdBoardId = response.body().jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(boardName));
        requestWithAuth()
                .queryParam("fields","id,name")
                .pathParam("member", USER_NAME)
                .get(BoardEndpoints.GET_ALL_BOARDS_URL)
                .then()
                .body("name", Matchers.hasItem(boardName));

    }

    @AfterEach
    public void deleteCreateBoard() {

        requestWithAuth()
                .pathParam("id", createdBoardId)
                .delete(BoardEndpoints.DELETE_BOARD_URL)
                .then()
                .statusCode(200);

    }


}
