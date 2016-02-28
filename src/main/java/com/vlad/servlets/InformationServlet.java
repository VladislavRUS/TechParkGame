package com.vlad.servlets;

import com.vlad.models.UserProfile;
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
 * Created by Владислав on 28.02.2016.
 */
public class InformationServlet extends HttpServlet{
    public static final String INFORMATION_SERVLET_URL = "/information";

    private AccountService accountService;

    public InformationServlet(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();
        if(accountService.inCurrentSession(req.getSession())){
            UserProfile profile = accountService.getUserBySession(req.getSession());
            map.put("login", profile.getLogin());
            map.put("password", profile.getPassword().toString());
            resp.getWriter().println(PageGenerator.getPage(map, "information.html"));
        }
        else{
            map.put("msg", "You need to log in!");
            resp.getWriter().println(PageGenerator.getPage(map, "error.html"));
        }
    }
}
