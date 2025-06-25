package com.HaveAVoice.shared.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component
public class MessageHelper {
    @Autowired
    MessageSource messageSource;

    @Autowired
    LocaleResolver localeResolver;

    public Locale getLocale() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return localeResolver.resolveLocale(attributes.getRequest());
        }
        return Locale.getDefault();
    }

    public String i18n(String message) {
        return messageSource.getMessage(message, null, getLocale());
    }
    public String i18n(String message, Object[] args) {
        return messageSource.getMessage(message, args, getLocale());
    }
}
