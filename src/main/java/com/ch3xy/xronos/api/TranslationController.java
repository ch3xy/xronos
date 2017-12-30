package com.ch3xy.xronos.api;

import com.ch3xy.xronos.translation.TranslationService;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/translations")
public class TranslationController {

    private TranslationService translationService;

    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @RequestMapping(value = "/{lang}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getTranslations(@PathVariable String lang) {
        Locale locale = LocaleUtils.toLocale(lang);
        return new ResponseEntity<>(translationService.getTranslation(locale), HttpStatus.OK);
    }
}
