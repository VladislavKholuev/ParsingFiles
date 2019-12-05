package com.company;

import java.util.Date;

public class Rate implements Comparable<Rate>{
    private int value;
    private int id_stud;
    private int id_sub;
    private Date data;
    private String sub;

    public Rate(int id_sub, int id_stud, int value, Date data){
            this.id_sub = id_sub;
            this.id_stud = id_stud;
            this.value = value;
            this.data = data;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Date getData() {
        return data;
    }

    public int getId_stud() {
        return id_stud;
    }

    public int getId_sub() {
        return id_sub;
    }

    public int getValue() {
        return value;
    }

    public String getSub() {
        return sub;
    }


    @Override
    public int compareTo(Rate o) {
        return this.getSub().compareTo(o.sub);
    }
}
