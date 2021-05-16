package com.cemil.evastudycase.model;

import javax.persistence.*;

@Entity
@Table(name="share")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="register_ind")
    private boolean registerIndex = false;

    @Column(name="reference")
    private String reference;

    @Column(name="amount")
    private double amount;

    @Column(name="rate")
    private double rate;

    public Share() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRegisterIndex() {
        return registerIndex;
    }

    public void setRegisterIndex(boolean registerIndex) {
        this.registerIndex = registerIndex;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
