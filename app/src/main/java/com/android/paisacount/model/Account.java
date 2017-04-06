package com.android.paisacount.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Shagun on 05/04/2017.
 */

public class Account extends RealmObject {

    @PrimaryKey
    private int id;

    private Date date;

    private String hackathon_name;

    private String university_name;

    private String hackathon_place;

    private int amount;

    private String payment_by;

    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public String getHackathon_name() {
        return hackathon_name;
    }

    public void setHackathon_name(String hackathon_name) {
        this.hackathon_name = hackathon_name;
    }

    public String getHackathon_place() {
        return hackathon_place;
    }

    public void setHackathon_place(String hackathon_place) {
        this.hackathon_place = hackathon_place;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayment_by() {
        return payment_by;
    }

    public void setPayment_by(String payment_by) {
        this.payment_by = payment_by;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
