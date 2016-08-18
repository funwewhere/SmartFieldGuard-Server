package com.szty.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author fwwer 2016-1-26
 *
 */
public class MySessionContext {
    private static HashMap<String, HttpSession> sessionMap = new HashMap<>();

    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public static synchronized void removeSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null){
        	return null;
        }
        return (HttpSession) sessionMap.get(session_id);
    }
}
