package com.company;

public class subject implements Comparable<subject>{
    private int id;
    private String nameSubject;
    private int index;
    private double rating;

    public subject(int id, String nameSubject){
        this.id = id;
        this.nameSubject = nameSubject;
    }

    public int getId() {
        return id;
    }

    public String getNameSubject() {
         return nameSubject;
    }

    public int getIndex() {
        return index;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(subject o) {
        if (this.rating == o.rating) {
            return 0;
        } else if (this.rating< o.rating) {
            return 1;
        } else {
            return -1;
        }
    }

}
