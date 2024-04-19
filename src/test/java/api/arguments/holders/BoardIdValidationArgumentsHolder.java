package api.arguments.holders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BoardIdValidationArgumentsHolder {

    private final Map<String,String> pathParams;

    private final String errorMessage;

    private final int statusCode;

//    public BoardIdValidationArgumentsHolder(Map<String, String> pathParams, String errorMessage, int statusCode) {
//        this.pathParams = pathParams;
//        this.errorMessage = errorMessage;
//        this.statusCode = statusCode;
//    }

//    public Map<String, String> getPathParams() {
//        return pathParams;
//    }
//
//    public String getErrorMessage() {
//        return errorMessage;
//    }
//
//    public int getStatusCode() {
//        return statusCode;
//    }
}
