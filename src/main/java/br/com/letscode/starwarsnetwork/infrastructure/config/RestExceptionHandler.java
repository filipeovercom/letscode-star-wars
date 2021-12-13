package br.com.letscode.starwarsnetwork.infrastructure.config;

import br.com.letscode.starwarsnetwork.infrastructure.models.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorResponse apiErrorHandler =
                ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getLocalizedMessage())
                        .errors(errors)
                        .build();

        return handleExceptionInternal(
                ex, apiErrorHandler, headers, apiErrorHandler.getStatus(), request);
    }

    @ExceptionHandler({TransactionSystemException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolation(Throwable ex) {
        var errors = new ArrayList<String>();
        var validationMessage = "Validation errors found: ";

        if (ex instanceof TransactionSystemException) {
            ex = ((TransactionSystemException) ex).getRootCause();
        }

        if (ex instanceof ConstraintViolationException) {
            var constraintViolation = (ConstraintViolationException) ex;
            var violations = constraintViolation.getConstraintViolations();

            for (ConstraintViolation<?> violation : violations) {
                errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
            }

            return APIResponseHandler.sendBadRequest(validationMessage, errors);
        }

        return APIResponseHandler.sendBadRequest(validationMessage, errors);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        String error =
                String.format(
                        "Argumento %s deve ser do tipo %s",
                        ex.getName(),
                        ofNullable(ex.getRequiredType()).map(Class::getName).orElse(""));

        return APIResponseHandler.sendBadRequest(ex.getLocalizedMessage(), error);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse responseDTO;

        if (body instanceof ErrorResponse) {
            responseDTO = (ErrorResponse) body;
        } else {
            responseDTO =
                    ErrorResponse.builder()
                            .status(status)
                            .message(ex.getLocalizedMessage())
                            .build();
        }

        return APIResponseHandler.sendBadRequest(responseDTO);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<ErrorResponse> handleHttpClientErrorException(
            HttpClientErrorException exception) {
        var status = exception.getStatusCode();

        if (status == HttpStatus.NOT_FOUND) {
            return APIResponseHandler.sendNotFound();
        } else if (status == HttpStatus.BAD_REQUEST) {
            return APIResponseHandler.sendBadRequest(exception.getMessage());
        }

        return APIResponseHandler.sendBadRequest("Invalid client exception");
    }
}
