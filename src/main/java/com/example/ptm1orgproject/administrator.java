package com.example.ptm1orgproject;

import java.util.ArrayList;

public class administrator extends organizationMember implements manageable{

    private String management_area;
    private int salary;

    public ArrayList<inspector> inspectors = new ArrayList<>();


    public administrator(String name, String id_member, String management_area) {
        super(name, id_member);
        this.management_area = management_area;
        this.setSalary(0);
        this.inspectors = new ArrayList<>();

    }

    public ArrayList<inspector> getinspectors() {
        return inspectors;
    }

    public void setinspectors(ArrayList<inspector> inspectors) {
        this.inspectors = inspectors;
    }

    public String getManagement_area() {
        return management_area;
    }

    public void setManagement_area(String management_area) {
        this.management_area = management_area;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int monthly_salary(int monthly_hours)throws volunteerExeption{
        if(this.reporting()==0)
            throw new volunteerExeption("no inspector reporting!");
        int monthly_salary = 56*monthly_hours;
        double bonus = monthly_salary * 0.05;
        monthly_salary += bonus * inspectors.size();
        return monthly_salary;
    }

    @Override
    public int reporting() throws volunteerExeption {
        int sum_of_hours=0;
        for( inspector o: inspectors){

            sum_of_hours += o.reporting();

        }
        return sum_of_hours;
    }

    @Override
    public String toString() {
        return "administrator{" +", name='" + name + '\'' +
                ", id_member='" + id_member + '\'' +
                "management_area='" + management_area + '\'' +
                ", salary=" + salary +
                '}';
    }
}

