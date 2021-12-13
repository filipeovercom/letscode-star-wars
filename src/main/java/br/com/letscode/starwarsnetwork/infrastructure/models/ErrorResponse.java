package br.com.letscode.starwarsnetwork.infrastructure.models;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class ErrorResponse {

    HttpStatus status;

    @Builder.Default LocalDateTime timestamp = LocalDateTime.now();

    String message;

    @Singular("addError")
    List<String> errors;
}
