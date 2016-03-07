package com.vlad.interfaces;

import com.vlad.models.UserProfile;

import javax.servlet.http.HttpSession;

/**
 * Created by Владислав on 07.03.2016.
 */
public interface AccountService {
    boolean userExists(String login, char[] password, HttpSession session);
    boolean register(String login, char[] password, HttpSession session);
    boolean inCurrentSession(HttpSession session);
    UserProfile getUserBySession(HttpSession session);
    void deleteUserSession(HttpSession session);
}
