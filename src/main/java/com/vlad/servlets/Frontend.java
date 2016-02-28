package com.vlad.servlets;

import com.vlad.util.PageGenerator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Владислав on 20.02.2016.
 */
public class Frontend extends HttpServlet {
    public static final String FRONTED_URL = "/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        Map<String, String> map = new HashMap<>();
        map.put("log", "ADMIN");
        writer.println(PageGenerator.getPage(map, "index.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("PRIVET!");
    }
}
