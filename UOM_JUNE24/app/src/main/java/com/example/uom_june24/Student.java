package com.example.uom_june24;

import java.util.ArrayList;

public class Student {
    String name;
    ArrayList<Integer> grades;

    public Student(String n) {
        name = n;
        grades = new ArrayList<Integer>();
    }

    public void addGrade(Integer g) {
        grades.add(g);
    }

}
