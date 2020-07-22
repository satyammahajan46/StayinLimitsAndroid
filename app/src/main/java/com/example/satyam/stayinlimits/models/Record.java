package com.example.satyam.stayinlimits.models;

public class Record {
    private static int transID = 1;
    private String transName;
    private double credit;
    private double debit;
    private String date;

    public Record(String transName) {
        this(transName, 0.0, 0.0);
    }

    public Record(String transName, double credit, double debit) {
        this.transName = transName;
        this.credit = credit;
        this.debit = debit;
    }

    public static int getTransID() {
        return transID++;
    }


    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
