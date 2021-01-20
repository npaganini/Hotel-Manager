package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.MessageSourceExternalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceExternalizerImpl implements MessageSourceExternalizer {
    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[0], LocaleContextHolder.getLocale());
    }
}
