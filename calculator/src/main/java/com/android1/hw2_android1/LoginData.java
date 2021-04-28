package com.android1.hw2_android1;

import java.io.Serializable;

public class LoginData implements Serializable {
    private String Login;
    private String Password;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
