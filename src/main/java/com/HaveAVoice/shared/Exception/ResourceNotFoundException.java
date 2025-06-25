package com.HaveAVoice.shared.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceType;
    private final Long identifier;

    public ResourceNotFoundException(String resourceType, Long identifier) {
        super(resourceType + " with ID " + identifier + " not found");
        this.resourceType = resourceType;
        this.identifier = identifier;
    }
}
