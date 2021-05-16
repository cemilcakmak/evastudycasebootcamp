package com.cemil.evastudycase.model;

import javax.persistence.*;

@Entity
@Table(name = "portfolio_shares")
public class PortfolioShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="portfolio_id")
    private long portfolioId;

    @Column(name="share_reference")
    private String shareReference;

    @Column(name="amount")
    private double amount;

    @Column(name="total_price")
    private double totalPrice;

    public PortfolioShare(String shareReference, double amount, double totalPrice) {
        this.shareReference = shareReference;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public PortfolioShare() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getShareReference() {
        return shareReference;
    }

    public void setShareReference(String shareReference) {
        this.shareReference = shareReference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
