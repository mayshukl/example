package com.demo.app;

import com.demo.callbackhandlers.DummyCallBackHandler;
import com.demo.loginmodule.DummyLoginModule;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Stating Login App");

	    System.setProperty("java.security.auth.login.config","jaas.config");
        LoginContext loginContext = null;
        try {
             loginContext=new LoginContext("dummylogin",new DummyCallBackHandler());
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(0);
        }

        try {
            loginContext.login();
            System.out.println("Login Success");
            System.exit(0);
        } catch (LoginException e) {
           System.out.println(e.getMessage());
        }
    }

}
