package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Group implements Comparable<Group>{
    private int id;
    private String nameGroup;
    private ArrayList<Student> listStudets;
    private double groupAvgValue;
    private double badProcent;
    private int badStudents;
    private ArrayList<subject> listSub;

    public Group(int id, String nameGroup){
        this.id = id;
        this.nameGroup = nameGroup;
        this.listStudets = new ArrayList<>();
        this.groupAvgValue = 0;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Student> getListStudets() {
        return listStudets;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void addListStudets(Student student) throws ParseException {

        if(student.getListRates().size() < listSub.size()){
            for(subject sub :listSub){
                boolean ok = false;
                if(student.getListRates().size() == 0)
                    ok =false;
                for(Rate rate : student.getListRates()) {
                    if (rate.getSub().equals(sub.getNameSubject())) {
                        ok = true;
                    }
                }
                if(!ok){
                    DateFormat date = new SimpleDateFormat("y-M-d-H:m:s");
                    Rate rate = new Rate(sub.getId(), student.getId(), 2, date.parse("2017-9-11-12:1:00"));
                    rate.setSub(sub.getNameSubject());
                    student.addRate(rate);
                }
            }
        }

        this.listStudets.add(student);
        this.listStudets.sort(Student::compareTo);
        int sumBadStud = 0;
        if(this.listStudets.size()>0) {
            double procentWorstStud  = listStudets.get(listStudets.size()-1).getAVR();
            for(Student stud : this.listStudets){
                if(stud.getAVR() == procentWorstStud){
                    sumBadStud++;
                }
            }
        }
        this.badStudents = sumBadStud;
        this.badProcent = (float) sumBadStud/this.listStudets.size()*100;
    }

    public int getBadStudents() {

        System.out.println("на всякий случай");
        System.out.println("плохие студенты:");
        if(this.listStudets.size()>0) {
            double procentWorstStud = listStudets.get(listStudets.size() - 1).getAVR();
            for(Student stud : this.listStudets){
                if(stud.getAVR() == procentWorstStud){
                    System.out.println(stud.getFIO());
                }
            }
        }
        System.out.println("информация о группе: ");
        return badStudents;
    }

    public double getGroupAvgValue() {

        double sum = 0.0;
        for(Student student : this.listStudets){
            sum = sum + student.getAVR();
        }
        this.groupAvgValue = sum/this.listStudets.size();

        return this.groupAvgValue;
    }

    public void setListSub(ArrayList<subject> listSub) {
        this.listSub = listSub;
    }

    public double getBadProcent(){
        return this.badProcent;
    }

    public ArrayList<subject> getHardSubjects(){
        for(subject subject : this.listSub) {
            for (Student student : this.listStudets) {
                for (int i = 0; i < student.getListRates().size(); ++i) {
                    if (student.getListRates().get(i).getValue() == 2 && subject.getNameSubject().equals(student.getListRates().get(i).getSub())) {
                        subject.setIndex(subject.getIndex()+1);
                    }
                }
            }
            subject.setRating(100.0*subject.getIndex()/this.getListStudets().size());
        }
        if(this.listSub.size()>5)
            for(int i = 0;i < listSub.size(); ++i){

            }
        return this.listSub;
    }

    @Override
    public int compareTo(Group group) {
        if (this.badProcent == group.badProcent) {
            return 0;
        } else if (this.badProcent< group.badProcent ) {
            return 1;
        } else {
            return -1;
        }
    }
}
