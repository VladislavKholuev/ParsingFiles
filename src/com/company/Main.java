package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final Integer LIMIT = 5;

    public static void main(String[] args) {
        // write your code here
        List<String> listStuds = new ArrayList<>();
        List<String> listGroup = new ArrayList<>();
        List<String> listSub = new ArrayList<>();
        List<String> listRating = new ArrayList<>();

        try {
            Path pathStud = Paths.get("Students.txt");
            Path pathGroup = Paths.get("Groups.txt");
            Path pathSubject = Paths.get("Subjects.txt");
            Path pathRating = Paths.get("Rates.txt");

            listStuds = Files.readAllLines(pathStud);
            listGroup = Files.readAllLines(pathGroup);
            listSub = Files.readAllLines(pathSubject);
            listRating = Files.readAllLines(pathRating);
        } catch (IOException e) {
//            System.out.println("11");
            System.out.println(e.getMessage());
        }
        ArrayList<Student> listOfAllStudent = new ArrayList<>();
        ArrayList<Group> listOfAllGroup = new ArrayList<>();
        ArrayList<subject> listOfAllSub = new ArrayList<>();
        ArrayList<Rate> listOfAllRating = new ArrayList<>();
        ArrayList<ArrayList<String>> listByOneStudentInGroup = new ArrayList<>();
        try {
            //эта проверка не нужна, тк если файл пустой, то ничего не сломается
//            if (!listGroup.isEmpty() && !listRating.isEmpty() && !listStuds.isEmpty() && !listSub.isEmpty()) {
                for (String str : listStuds) {
                    List<String> parceList = Arrays.asList(str.split(" "));
                    try {
                        if (parceList.size() == 4 && Integer.parseInt(parceList.get(0)) != 0) {
                            Student stud = new Student(Integer.parseInt(parceList.get(0)), parceList.get(1), parceList.get(2), parceList.get(3));
                            boolean ok = true;
                            for (Student student : listOfAllStudent) {
                                if (Integer.parseInt(parceList.get(0)) == student.getId()) {
                                    ok = false;
                                }
                            }
                            if (ok) {
                                listOfAllStudent.add(stud);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ошибка в строке");
                        System.out.println(e.getMessage());
                    }
                }

                for (String str : listSub) {
                    List<String> parceList;
                    parceList = Arrays.asList(str.split(" "));
                    try {
                        if (parceList.size() == 2 && Integer.parseInt(parceList.get(0)) > -1) {
                            subject sub = new subject(Integer.parseInt(parceList.get(0)), parceList.get(1));
                            boolean ok = true;
                            for (subject subject : listOfAllSub) {
                                if (Integer.parseInt(parceList.get(0)) == subject.getId()) {
                                    ok = false;
                                }
                            }
                            if (ok) {
                                listOfAllSub.add(sub);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ошибка в строке");
                        System.out.println(e.getMessage());
                    }
                }

                for (String str : listRating) {
                    List<String> parceList;
                    parceList = Arrays.asList(str.split(" "));
                    DateFormat date = new SimpleDateFormat("y-M-d-H:m:s");
                    try {
                        if ((parceList.size() == 4 && Integer.parseInt(parceList.get(0)) > 0 && Integer.parseInt(parceList.get(1)) > 0 && (Integer.parseInt(parceList.get(2)) >= 2 && Integer.parseInt(parceList.get(2)) <= 5))) {
                            date.parse(parceList.get(3));
                            Rate rate = new Rate(Integer.parseInt(parceList.get(0)), Integer.parseInt(parceList.get(1)), Integer.parseInt(parceList.get(2)), date.parse(parceList.get(3)));
                            listOfAllRating.add(rate);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ошибка в строке");
                        System.out.println(e.getMessage());
                    } catch (ParseException e) {
                        System.out.println("ошибка в дате");
                        System.out.println(e.getMessage());
                    }
                }

                for (String str : listGroup) {
                    List<String> parceList;
                    ArrayList<String> parceIdStud = new ArrayList<>();
                    parceList = Arrays.asList(str.split(" "));
                    try {
                        if (parceList.size() == 3 && Integer.parseInt(parceList.get(0)) > 0 && Integer.parseInt(parceList.get(2)) > 0) {

                            boolean ok = true;
                            for (Group group : listOfAllGroup) {
                                if (parceList.get(1).equals(group.getNameGroup()) || Integer.parseInt(parceList.get(0)) == group.getId()) {
                                    ok = false;
                                }
                            }
                            Group group = new Group(Integer.parseInt(parceList.get(0)), parceList.get(1));
                            parceIdStud.add(parceList.get(1));
                            parceIdStud.add(parceList.get(2));
                            boolean ok1 = true;
                            for (ArrayList<String> stud : listByOneStudentInGroup) {
                                if (stud.get(1).equals(parceList.get(2)))
                                    ok1 = false;
                            }
                            if (ok1)
                                listByOneStudentInGroup.add(parceIdStud);
                            if (ok) {
                                listOfAllGroup.add(group);
                            }

                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ошибка в строке");
                        System.out.println(e.getMessage());
                    }
                }

//                System.out.println(listOfAllStudent);
//                System.out.println(listOfAllGroup);
//                System.out.println(listOfAllRating);
//                System.out.println(listOfAllSub);
//                System.out.println(listByOneStudentInGroup);

                //для каждой оценки в класс добавить название предмета
                for (subject sub : listOfAllSub) {
                    for (Rate rate : listOfAllRating) {
                        if (rate.getId_sub() == sub.getId()) {
                            rate.setSub(sub.getNameSubject());
                        }
                    }
                }

                for (Student stud : listOfAllStudent) {
                    for (Rate rate : listOfAllRating) {
                        if (stud.getId() == rate.getId_stud()) {
                            stud.addRate(rate);
                        }
                    }
                }

                for (Group group : listOfAllGroup) {
                    group.setListSub(listOfAllSub);
                    for (Student student : listOfAllStudent) {
                        for (ArrayList<String> sur : listByOneStudentInGroup) {
                            if (group.getNameGroup().equals(sur.get(0)) && student.getId() == Integer.parseInt(sur.get(1))) {
                                group.addListStudets(student);
                            }
                        }
                    }
                }

                //часть а
                System.out.println("часть а");
                for (Group group : listOfAllGroup) {
                    System.out.println("группа: " + group.getNameGroup() + " средний балл по группе: " + group.getGroupAvgValue());
                    group.getListStudets().sort(Student::compareTo);
                    if (group.getListStudets().size() > LIMIT-1) {
                        for (int i = 0; i < LIMIT; ++i) {
                            System.out.println(group.getListStudets().get(i).getFIO() + " " + group.getListStudets().get(i).getAVR());
                        }
                    } else {
                        for (Student student : group.getListStudets()) {
                            System.out.println(student.getFIO() + " " + student.getAVR());
                        }
                    }
                }

                //b
                System.out.println("часть б");
                listOfAllGroup.sort(Group::compareTo);
                if (listOfAllGroup.size() > LIMIT-1) {
                    for (int i = 0; i < 5; ++i) {
                        System.out.println(listOfAllGroup.get(i).getNameGroup() + " количество плохих учеников: " + listOfAllGroup.get(i).getBadStudents() + " процентное соотношение:  " + listOfAllGroup.get(i).getBadProcent());
                    }
                } else {
                    for (Group group : listOfAllGroup) {
                        System.out.println(group.getNameGroup() + " количество плохих учеников: " + group.getBadStudents() + " процентное соотношение:  " + group.getBadProcent());
                    }
                }
                //c
                System.out.println("часть c");
                for (Group group : listOfAllGroup) {
                    ArrayList<subject> listSubs = group.getHardSubjects();
                    System.out.println(group.getNameGroup());
                    listSubs.sort(subject::compareTo);
                    if (listSubs.size() < LIMIT)
                        for (subject subject : listSubs) {
                            System.out.println(subject.getNameSubject() + ",     проценты не сдавших этот предмет " + subject.getRating());
                            subject.setIndex(0);
                        }
                    else {
                        for (int i = 0; i < LIMIT; ++i) {
                            System.out.println(listSubs.get(i).getNameSubject() + ",     проценты не сдавших этот предмет " + listSubs.get(i).getRating());
                            listSubs.get(i).setIndex(0);
                        }
                    }
                }
//                for(Group group :listOfAllGroup){
//                    System.out.println(group.getNameGroup());
//                    for (Student student: group.getListStudets()){
//                        System.out.println(student.getSurname());
//                    }
//                }
//            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
