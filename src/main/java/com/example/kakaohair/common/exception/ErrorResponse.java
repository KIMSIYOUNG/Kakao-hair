package com.example.kakaohair.common.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int status;
    private final String code;
    private final String message;
    private List<RequestFieldError> errors;

    public static ErrorResponse of(final HttpStatus status, final String code,
        final String message, final BindingResult result) {

        return new ErrorResponse(status.value(), code, message, RequestFieldError.listOf(result));
    }

    public static ErrorResponse of(final HttpStatus status, final String code, final String message) {
        return new ErrorResponse(status.value(), code, message, null);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RequestFieldError {
        private String field;
        private String value;
        private String reason;

        private static List<RequestFieldError> listOf(BindingResult result) {
            return result.getFieldErrors().stream()
                .map(field -> new RequestFieldError(
                    field.getField(),
                    field.getRejectedValue() == null ? "" : field.getRejectedValue().toString(),
                    field.getDefaultMessage()

                )).collect(Collectors.toList());
        }
    }
}
