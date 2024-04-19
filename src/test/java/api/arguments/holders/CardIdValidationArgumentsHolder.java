package api.arguments.holders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class CardIdValidationArgumentsHolder {

    private final Map<String,String> pathParams;

    private final String errorMessage;

    private final int statusCode;


}
