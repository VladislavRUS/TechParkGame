package com.vlad.servlets;

import com.vlad.services.AccountService;
import com.vlad.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 28.02.2016.
 */
public class LogoutServlet extends HttpServlet{
    public static final String LOGOUT_SERVLET_URL = "/logout";

    private AccountService accountService;

    public LogoutServlet(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        accountService.deleteUserSession(req.getSession());
        resp.getWriter().println(PageGenerator.getPage(null, "index.html"));
    }
}
