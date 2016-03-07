package com.vlad.services;

import com.vlad.interfaces.AccountService;
import com.vlad.models.UserProfile;
import com.vlad.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Владислав on 26.02.2016.
 */
public class AccountServiceImpl implements AccountService {
    private static Map<HttpSession, UserProfile> currentSessions = Collections.synchronizedMap(new HashMap<HttpSession, UserProfile>());

    @Override
    public boolean userExists(String login, char[] password, HttpSession session){
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("from UserProfile where login = :login");
        query.setParameter("login", login);
        UserProfile profile = (UserProfile) query.uniqueResult();
        ses.close();
        if(profile != null){
            if(profile.getLogin().equals(login) && String.valueOf(profile.getPassword()).equals(String.valueOf(password))){
                if(!currentSessions.containsKey(session))
                    currentSessions.put(session, profile);
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public boolean register(String login, char[] password, HttpSession session){
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("from UserProfile where login = :login");
        query.setParameter("login", login);
        UserProfile profile = (UserProfile)query.uniqueResult();
        ses.close();

        if(profile != null){
            return false;
        }
        else{
            ses = HibernateUtil.getSessionFactory().openSession();
            ses.beginTransaction();
            profile = new UserProfile();
            profile.setLogin(login);
            profile.setPassword(password);
            ses.save(profile);
            ses.getTransaction().commit();
            ses.close();

            currentSessions.put(session, profile);
            return true;
        }
    }

    @Override
    public boolean inCurrentSession(HttpSession session){
        if(currentSessions.containsKey(session)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public UserProfile getUserBySession(HttpSession session){
        return currentSessions.get(session);
    }

    @Override
    public void deleteUserSession(HttpSession session){
        currentSessions.remove(session);
    }
}
