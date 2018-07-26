package com.example.demo.model;

public class DemoModel {

    private String from;
    private String to;
    private Double rate;
    private Double amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{\"from\":" + from + ",\"to\":" + to + ",\"rate\":" + rate + ",\"amount\":" + amount + "}";
    }
}
