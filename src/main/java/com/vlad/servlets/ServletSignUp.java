package com.vlad.servlets;

import com.vlad.services.AccountService;
import com.vlad.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Владислав on 26.02.2016.
 */
public class ServletSignUp extends HttpServlet{
    public static final String SERVLET_SIGN_UP_URL = "/register";
    private AccountService accountService;

    public ServletSignUp(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        char[] pass = req.getParameter("password").toCharArray();
        Map<String, String> map = new HashMap<>();
        if(accountService.register(login, pass, req.getSession())){
            map.put("login", login);
            resp.getWriter().println(PageGenerator.getPage(map, "personal.html"));
        }
        else{
            map.put("msg", "This user already exists!");
            resp.getWriter().println(PageGenerator.getPage(map, "error.html"));
        }
    }
}
