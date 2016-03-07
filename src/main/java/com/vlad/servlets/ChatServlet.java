package com.vlad.servlets;

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
 * Created by Владислав on 07.03.2016.
 */
public class ChatServlet extends HttpServlet{
    private AccountService accountService;

    public ChatServlet(AccountService accountService){
        this.accountService = accountService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();

        if(accountService.inCurrentSession(req.getSession())) {
            map.put("login", accountService.getUserBySession(req.getSession()).getLogin());
            resp.getWriter().println(PageGenerator.getPage(map, "chat.html"));
        }
        else {
            map.put("msg", "User was not found!");
            resp.getWriter().println(PageGenerator.getPage(map, "error.html"));
        }
    }
}
