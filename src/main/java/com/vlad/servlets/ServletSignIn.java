package com.vlad.servlets;

import com.vlad.services.AccountService;
import com.vlad.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Владислав on 26.02.2016.
 */
public class ServletSignIn extends HttpServlet{
    public static final String SERVLET_SIGN_IN_URL = "/login";
    private AccountService accountService;

    public ServletSignIn(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        char[] password = req.getParameter("password").toCharArray();
        HttpSession userSession = req.getSession();
        Map<String, String> map = new HashMap<>();

        if(accountService.userExists(login, password, userSession)){
            map.put("login", login);
            resp.getWriter().println(PageGenerator.getPage(map, "personal.html"));
        }
        else{
            map.put("msg", "User was not found!");
            resp.getWriter().println(PageGenerator.getPage(map, "error.html"));
        }
    }
}
