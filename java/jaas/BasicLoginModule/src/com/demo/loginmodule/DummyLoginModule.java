package com.demo.loginmodule;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class DummyLoginModule implements LoginModule {
    private static final String userName="root";
    private static final String password="password";

    private boolean isAuthenticated;

    private CallbackHandler callbackHandler;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler=callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        NameCallback nameCallback=new NameCallback("Enter User name");
        PasswordCallback passwordCallback=new PasswordCallback("Enter Password",false);

        Callback[] callbacks={nameCallback,passwordCallback};

        try {
            this.callbackHandler.handle(callbacks);
            isAuthenticated=nameCallback.getName().equals(userName)&&(new String(passwordCallback.getPassword())).equals(password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }

    @Override
    public boolean commit() throws LoginException {
        return isAuthenticated;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
