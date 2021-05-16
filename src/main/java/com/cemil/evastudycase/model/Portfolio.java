package com.cemil.evastudycase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_id")
    private long userId;

    @Column(name="register_ind")
    private boolean registerIndex = false;

    @Column(name = "total_wallet")
    private double totalWallet;

    @Column(name = "current_wallet")
    private double currentWallet;

    public Portfolio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isRegisterIndex() {
        return registerIndex;
    }

    public void setRegisterIndex(boolean registerIndex) {
        this.registerIndex = registerIndex;
    }

    public double getTotalWallet() {
        return totalWallet;
    }

    public void setTotalWallet(double totalWallet) {
        this.totalWallet = totalWallet;
    }

    public double getCurrentWallet() {
        return currentWallet;
    }

    public void setCurrentWallet(double currentWallet) {
        this.currentWallet = currentWallet;
    }
}
