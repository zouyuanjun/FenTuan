package com.lejiaokeji.fentuan.databean;

public class Add_Alipay_Bean {
    String code,payee,paymentAccount,phone;

    public Add_Alipay_Bean() {
    }

    public Add_Alipay_Bean(String code, String payee, String paymentAccount, String phone) {
        this.code = code;
        this.payee = payee;
        this.paymentAccount = paymentAccount;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
