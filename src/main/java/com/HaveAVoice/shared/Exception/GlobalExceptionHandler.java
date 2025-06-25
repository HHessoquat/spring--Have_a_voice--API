package com.HaveAVoice.shared.Exception;

import com.HaveAVoice.shared.BusinessCodes;
import com.HaveAVoice.shared.Response.ResponseService;
import com.HaveAVoice.shared.i18n.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final MessageHelper message;


    public GlobalExceptionHandler(MessageHelper message) {
        this.message = message;
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ResponseService<Void>> handleInvalidCredentials(InvalidCredentials e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ResponseService.build(e.getCode(), message.i18n("AUTHENTICATION.ERROR.INVALID_CREDENTIALS"))
        );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseService<Void>> resourceNotFound(ResourceNotFoundException e) {
        log.warn("unexistant {} {} seeked", e.getResourceType(), e.getIdentifier());
        String messageKey = e.getResourceType().toUpperCase() + ".NOT_FOUND";
        String msg = this.message.i18n(messageKey, new Object[] { e.getIdentifier() });

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ResponseService.build(BusinessCodes.NOT_FOUND, msg)
                );
    }

    @ExceptionHandler(ResourceConflict.class)
    public ResponseEntity<ResponseService<Void>> resourceNameConflict(ResourceConflict e) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ResponseService.build(
                        e.getBusinessCode(),
                        e.getResourceType().toUpperCase() + ".CONFLICT"
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseService<Void>> fallback(Exception e) {
        log.error("Fallback handler: {}", e.getClass().getName(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ResponseService.build(BusinessCodes.NOT_FOUND, e.getMessage())
                );
    }
}
