package com.example.satyam.stayinlimits.models;

public class HomeFragData {
    private double savings, goal, limit, balance;

    public HomeFragData(double savings, double goal, double limit, double balance) {
        this.savings = savings;
        this.goal = goal;
        this.limit = limit;
        this.balance = balance;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
