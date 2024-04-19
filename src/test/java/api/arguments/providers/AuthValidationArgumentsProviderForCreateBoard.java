package api.arguments.providers;

import api.arguments.holders.AuthValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static api.consts.UrlParamValues.VALID_KEY;
import static api.consts.UrlParamValues.VALID_TOKEN;

public class AuthValidationArgumentsProviderForCreateBoard implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
               new AuthValidationArgumentsHolder(
                       Collections.emptyMap(),
                       "unauthorized permission requested"
               ),
                new AuthValidationArgumentsHolder(
                        Map.of("key", VALID_KEY),
                        "unauthorized permission requested"
                ),
                new AuthValidationArgumentsHolder(
                        Map.of("token", VALID_TOKEN),
                        "unauthorized permission requested"
                )
        ).map(Arguments::of);
    }
}
