package api.test.update;

import api.consts.BoardEndpoints;
import api.consts.UrlParamValues;
import api.test.BaseTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

public class UpdateBoardTest extends BaseTest {

    @Test
    public void checkUpdateBoard() {

        String updatedName = "Updated Name" + LocalDateTime.now();

        requestWithAuth()
                .pathParam("id", UrlParamValues.BOARD_ID_TO_UPDATE)
                .body(Map.of("name", updatedName))
                .contentType(ContentType.JSON)
                .put(BoardEndpoints.UPDATE_BOARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(updatedName));

        requestWithAuth()
                .pathParam("id", UrlParamValues.BOARD_ID_TO_UPDATE)
                .get(BoardEndpoints.GET_BOARD_URL)
                .then()
                .log().body()
                .body("name", Matchers.equalTo(updatedName));

    }

}
