package com.finaxemoney.model;

import android.os.Parcel;
import android.os.Parcelable;

public class USER implements Parcelable {

    String  name ;
    String email;
    String phone ;
    String adharNumber;
    String panNumber;
    String amount;
    String state ;
    String city;

    public USER() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static Creator<USER> getCREATOR() {
        return CREATOR;
    }

    protected USER(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        adharNumber = in.readString();
        panNumber = in.readString();
        amount = in.readString();
        state = in.readString();
        city = in.readString();


    }

    public static final Creator<USER> CREATOR = new Creator<USER>() {
        @Override
        public USER createFromParcel(Parcel in) {
            return new USER(in);
        }

        @Override
        public USER[] newArray(int size) {
            return new USER[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdharNumber() {
        return adharNumber;
    }

    public void setAdharNumber(String adharNumber) {
        this.adharNumber = adharNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(adharNumber);
        dest.writeString(panNumber);
        dest.writeString(amount);
        dest.writeString(state);
        dest.writeString(city);
    }
}
