package org.abondar.experimental.springaop.securebeforeadvice;

/**
 * Created by abondar on 16.07.16.
 */
public class SecurityManager {
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public void login(String userName,String password){
        threadLocal.set(new UserInfo(userName,password));
    }

    public void logout(){
        threadLocal.set(null);
    }

    public  UserInfo getLoggedOnUser(){
        return threadLocal.get();
    }
}
