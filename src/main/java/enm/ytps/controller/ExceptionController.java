package enm.ytps.controller;

import enm.ytps.exception.AbstractAdManagerException;
import enm.ytps.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Controller
public class ExceptionController {
    @ExceptionHandler({AbstractAdManagerException.class})
    public ResponseEntity<ErrorResponse> handleAdManagerException(AbstractAdManagerException e, WebRequest request) {
        final ErrorResponse errorResponse = ErrorResponse.builder().code(e.getCode()).message(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getCode()));
    }
}
