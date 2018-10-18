package com.demo.callbackhandlers;

import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DummyCallBackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        NameCallback nameCallback=null;
        PasswordCallback passwordCallback=null;

        for(int counter=0;counter<callbacks.length;counter++){
            if(callbacks[counter] instanceof  NameCallback){
                nameCallback= (NameCallback) callbacks[counter];
            }
            if(callbacks[counter] instanceof  PasswordCallback){
                passwordCallback= (PasswordCallback) callbacks[counter];
            }
        }

        if (nameCallback!=null){
            System.out.println(nameCallback.getPrompt());
            nameCallback.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());

            if(passwordCallback!=null){
                System.out.println(passwordCallback.getPrompt());
                passwordCallback.setPassword((new BufferedReader(new InputStreamReader(System.in))).readLine().toCharArray());
            }
        }
    }
}
