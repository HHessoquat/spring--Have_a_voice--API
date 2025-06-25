package com.HaveAVoice.shared.Exception;

import lombok.Getter;

@Getter
public class ResourceConflict extends RuntimeException{
    private final String businessCode;
    private final String resourceType;

    public ResourceConflict(String businessCode, String resourceType) {
        super(resourceType + " already exists");
        this.businessCode = businessCode;
        this.resourceType = resourceType;

    }
}
