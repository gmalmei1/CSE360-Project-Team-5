package com.restaurant;

public class Customer extends User{

    private final String paymentInfo;
    private final String phoneNumber;
    public Cart cart;

    public Customer(String fullNameParam,
                    String emailParam,
                    String passwordParam,
                    String securityQuestionParam,
                    String securityAnswerParam,
                    String paymentParam,
                    String phoneParam)
    {
        super(fullNameParam, emailParam, passwordParam, securityQuestionParam, securityAnswerParam);
        this.paymentInfo = paymentParam;
        this.phoneNumber = phoneParam;
        this.cart = new Cart();
    }

    public String getPaymentInfo(){ return paymentInfo; }

    public String getPhoneNumber(){
        return phoneNumber;
    }


}
