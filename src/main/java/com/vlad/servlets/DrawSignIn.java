package com.vlad.servlets;

import com.vlad.models.UserProfile;
import com.vlad.interfaces.AccountService;
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
public class DrawSignIn extends HttpServlet{
    public static final String DRAW_SIGN_IN_URL = "/signin";
    private AccountService accountService;

    public DrawSignIn(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(accountService.inCurrentSession(req.getSession())){
            Map<String, String> map = new HashMap<>();
            UserProfile profile = accountService.getUserBySession(req.getSession());
            map.put("login", profile.getLogin());
            map.put("password", profile.getPassword().toString());
            resp.getWriter().println(PageGenerator.getPage(map, "personal.html"));
        }
        else{
            resp.getWriter().println(PageGenerator.getPage(null, "signin.html"));
        }
    }
}
