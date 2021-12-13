package br.com.letscode.starwarsnetwork.infrastructure.config;

import br.com.letscode.starwarsnetwork.infrastructure.models.ErrorResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class APIResponseHandler {

    public static ResponseEntity<ErrorResponse> sendNotFound() {
        return ResponseEntity.notFound().build();
    }

    public static ResponseEntity<ErrorResponse> sendBadRequest(String errorMessage) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorResponse.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(errorMessage)
                                .addError(errorMessage)
                                .build());
    }

    public static ResponseEntity<ErrorResponse> sendBadRequest(String message, String... errors) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorResponse.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(message)
                                .errors(List.of(errors))
                                .build());
    }

    public static ResponseEntity<ErrorResponse> sendBadRequest(
            String message, List<String> errors) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorResponse.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(message)
                                .errors(errors)
                                .build());
    }

    public static ResponseEntity<Object> sendBadRequest(Object object) {
        return ResponseEntity.badRequest().body(object);
    }
}
