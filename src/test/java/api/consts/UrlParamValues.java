package api.consts;

import java.util.Map;

public class UrlParamValues {

    public static final String EXISTING_BOARD_ID = "3Wtafv36";
    public static final String BOARD_ID_TO_UPDATE = "660ea47a7dffcb98e1f53251";
    public static final String CARD_ID_TO_UPDATE = "660eaab914d8b559bd43af9a";
    public static final String ID_LIST = "66053febe789a7abdefd30ea";
    public static final String USER_NAME = "learnpostman2222";
    public static final String VALID_KEY = "6b609d45461d0dc82548499b2cafe6b6";
    public static final String VALID_TOKEN = "ATTAbafab8cefde62c45464b6ed5e5507a9c781f03452a60454db296fc949725433c007B11F5";
    public static final Map<String,String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
    );

    public static final Map<String,String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", "8b32218e6887516d17c84253faf967b6",
            "token", "492343b8106e7df3ebb7f01e219cbf32827c852a5f9e2b8f9ca296b1cc604955"
    );



}
