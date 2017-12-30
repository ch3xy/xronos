package com.ch3xy.xronos.translation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

@Service
public class TranslationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TranslationService.class);

    private static final String GUI_MESSAGES_LOCATION = "classpath:ui-messages_${lang}.properties";

    private ApplicationContext applicationContext;

    @Autowired
    public TranslationService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Map<String, Object> getTranslation(Locale locale) {

        Resource resource = applicationContext.getResource(getGuiMessagesLocation(locale));
        EncodedResource utf8Resource = new EncodedResource(resource, Charset.forName("UTF-8"));
        try {
            return propertyFileToJsonMap(utf8Resource);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        // return empty HashMap on Failure
        return new HashMap<>();
    }

    private String getGuiMessagesLocation(Locale locale) {
        return GUI_MESSAGES_LOCATION.replace("${lang}", locale.getLanguage());
    }

    private Map<String, Object> propertyFileToJsonMap(EncodedResource propertyResource) throws IOException {
        Properties properties = PropertiesLoaderUtils.loadProperties(propertyResource);
        return propertiesToJsonMap(properties);
    }

    private Map<String, Object> propertiesToJsonMap(Properties p) {
        Map tree = new LinkedHashMap();

        for (String name : p.stringPropertyNames()) {
            String[] parts = name.split("\\.");
            buildTree(tree, parts, p.getProperty(name));
        }
        return tree;
    }

    private void buildTree(Map tree, String[] parts, String property) {
        Map nextTree = tree;
        for (int i = 0, partsLength = parts.length; i < partsLength; i++) {
            String part = parts[i];
            Object v = nextTree.get(part);
            if (v == null) {
                if (i < partsLength - 1) {
                    Map newNextTree = new LinkedHashMap();
                    nextTree.put(part, newNextTree);
                    nextTree = newNextTree;
                } else {
                    nextTree.put(part, property);
                }
            } else {
                if (i < partsLength - 1) {
                    nextTree = (Map) v;
                }
            }
        }
    }
}
