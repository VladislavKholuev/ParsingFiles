package com.company;

import java.util.ArrayList;

public class Student implements Comparable<Student>{

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private ArrayList<Rate> listRates;

    public Student(int id, String Surname, String Name, String Patronymic){
        this.id = id;
        this.surname = Surname;
        this.name = Name;
        this.patronymic = Patronymic;
        this.listRates = new ArrayList<Rate>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFIO(){
        return this.surname + " " + this.name + " " + this.patronymic;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<Rate> getListRates() {
        return this.listRates;
    }

    public void addRate(Rate rate){
        this.listRates.add(rate);
    }

    public double getAVR(){
        double avr = 0.0;
        double sum = 0.0;
        for (Rate rate : this.listRates){
            sum = sum + rate.getValue();
        }
        if(this.listRates.size() != 0){
            avr = sum / this.listRates.size();
        }
        return avr;
    }

    @Override
    public int compareTo(Student student)
    {
        if (this.getAVR() == student.getAVR()) {
            return 0;
        } else if (this.getAVR()< student.getAVR() ) {
            return 1;
        } else {
            return -1;
        }
    }
}
