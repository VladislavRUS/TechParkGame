package com.vlad.server;

import com.vlad.services.AccountService;
import com.vlad.servlets.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
/**
 * Created by Владислав on 20.02.2016.
 */
public class Start {
    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        handler.addServlet(new ServletHolder(new Frontend()), Frontend.FRONTED_URL);
        handler.addServlet(new ServletHolder(new DrawSignIn(accountService)), DrawSignIn.DRAW_SIGN_IN_URL);
        handler.addServlet(new ServletHolder(new DrawSignUp()), DrawSignUp.DRAW_SIGN_UP_URL);
        handler.addServlet(new ServletHolder(new ServletSignIn(accountService)), ServletSignIn.SERVLET_SIGN_IN_URL);
        handler.addServlet(new ServletHolder(new ServletSignUp(accountService)), ServletSignUp.SERVLET_SIGN_UP_URL);
        handler.addServlet(new ServletHolder(new InformationServlet(accountService)), InformationServlet.INFORMATION_SERVLET_URL);
        handler.addServlet(new ServletHolder(new LogoutServlet(accountService)), LogoutServlet.LOGOUT_SERVLET_URL);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("myresources");
        resourceHandler.setDirectoriesListed(true);

        ContextHandler context = new ContextHandler("/");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{ handler, resourceHandler});

        Server server = new Server(8080);

        context.setHandler(handlerList);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
