package com.restaurant;

public class User {

    private final String fullName;
    private final String email;
    private final String password;
    private final String securityQ;
    private final String securityA;

    public User(String fullNameParam,
                String emailParam,
                String passwordParam,
                String securityQuestionParam,
                String securityAnswerParam){

        fullName = fullNameParam;
        email = emailParam;
        password = passwordParam;
        securityQ = securityQuestionParam;
        securityA = securityAnswerParam;

    }

    public String getFullName(){
        return fullName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getSecurityQ(){
        return securityQ;
    }

    public String getSecurityA(){
        return securityA;
    }

}
