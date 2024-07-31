package com.example.uom_june24;

import android.content.res.AssetManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class StudentList {
    ArrayList<Student> stdList;

    public StudentList(AssetManager asset) {
        // Read from XML
        ArrayList<Student> stdTemp = new ArrayList<>();

        try {
            InputStream is = asset.open("students.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element = doc.getDocumentElement();
            element.normalize();
            // for every tag student
            NodeList nList = doc.getElementsByTagName("student");

            for (int i =0 ; i<nList.getLength(); i++){
                Node n = nList.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE){
                    String name = ((Element)n).getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
                    String[] grades = ((Element)n).getElementsByTagName("grades").item(0).getChildNodes().item(0).getNodeValue().split(",");

                    Student st = new Student(name);
                    // add every grade to student instance
                    for (String grade : grades) {
                        st.addGrade(Integer.parseInt(grade.trim()));
                    }
                    // Add to temp array
                    stdTemp.add(st);
                }
            }
            // Replace main array
            stdList = stdTemp;

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String findAverage(String name) {
        // implement find
        double av = 0;
        for (Student d : stdList) {
            if (d.name.equals(name))
            {
                // call method for calculating grade
                av = calcAverageGrade(d);
                // Student found, stop the loop : flag = false
                break;
            }
        }

        return String.valueOf(av);
    }

    private double calcAverageGrade(Student st){
        // sum : total grade sum
        // num : total of successfully passed courses
        int sum=0, num=0;
        for (int i : st.grades) {
            if (i >= 5){
                sum += i;
                num ++;
            }
        }
        // Calculate average
        // In case there is no grade return 0
        return num==0 ? 0 : (double) sum/num;
    }

    public ArrayList<String> getListNames(){
        // Get al the names as an list for the adapter
        ArrayList<String> names = new ArrayList<>();

        for (Student n : stdList) {
            names.add(n.name);
        }

        return names;
    }

}

