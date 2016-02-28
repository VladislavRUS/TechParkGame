package com.vlad.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Владислав on 26.02.2016.
 */
public class PageGenerator {
    private static final Configuration CFG = new Configuration();
    private static final String PAGE_FOLDER = "myresources/html/";

    public static <T> String getPage(Map<T, T> map, String fileName) {
        Template template;
        Writer stream = null;
        try {
            template = CFG.getTemplate(PAGE_FOLDER + fileName);
            stream = new StringWriter();
            template.process(map, stream);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }
}
