package com.finaxemoney.model;

public class TransactionPojo {
    private String id,user_id,date,remark,type,withdraw_type,payment_status,wallet;
    private double amount;

    public TransactionPojo() {
    }

    public TransactionPojo(String id, String user_id, String date, String remark, String type, String withdraw_type, String payment_status, String wallet, double amount) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.remark = remark;
        this.type = type;
        this.withdraw_type = withdraw_type;
        this.payment_status = payment_status;
        this.wallet = wallet;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWithdraw_type() {
        return withdraw_type;
    }

    public void setWithdraw_type(String withdraw_type) {
        this.withdraw_type = withdraw_type;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
}
