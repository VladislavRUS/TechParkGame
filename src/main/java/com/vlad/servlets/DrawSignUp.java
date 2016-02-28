package com.vlad.servlets;

import com.vlad.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 26.02.2016.
 */
public class DrawSignUp extends HttpServlet{
    public static final String DRAW_SIGN_UP_URL = "/signup";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getPage(null, "signup.html"));
    }
}
