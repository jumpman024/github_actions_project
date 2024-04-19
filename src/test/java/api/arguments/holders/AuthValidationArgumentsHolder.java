package api.arguments.holders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AuthValidationArgumentsHolder {

    private final Map<String, String> authParams;
    private final String errorMessage;

}
