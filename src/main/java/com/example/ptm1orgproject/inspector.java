package com.example.ptm1orgproject;

import java.util.ArrayList;

public class inspector extends volunteer implements manageable,Runnable{

    private static volunteer park[] = new volunteer[50];
    private volunteer myvolunteer;
    private static int i = 0;
    private ArrayList<organizationMember> volunteers = new ArrayList<>();


    public inspector(String name, String id_member, String area_of_volunteering, int hours_of_volunteering) {
        super(name, id_member, area_of_volunteering, hours_of_volunteering);
        this.volunteers = new ArrayList<>();

    }

    public inspector(volunteer v1) {
        super(v1.name,v1.id_member,v1.area_of_volunteering,v1.getHours_of_volunteering());
    }
    public ArrayList<organizationMember> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(volunteer v) {
        this.volunteers.add(v);
    }
    @Override
    public int reporting() throws volunteerExeption{
        int sum_of_hours=0;
        for( organizationMember o: this.getVolunteers()){
            if(o instanceof volunteer){
                volunteer v = (volunteer)o;
                sum_of_hours += v.getHours_of_volunteering();
            }
        }
        if(sum_of_hours <= 0 )
            throw new volunteerExeption("no volunteer reporting!");
        return sum_of_hours;
    }

    @Override
    public String toString() {
        return "inspector{" +super.toString()+
//                "area_of_volunteering='" + area_of_volunteering + '\'' +
//                ", name='" + name + '\'' +
//                ", id_member='" + id_member + '\'' +
                '}';
    }

    @Override
    public void run() {
        boolean flag = false;

        System.out.println(Thread.currentThread().getName()+" Start. Command = "+this.getName());
        for (organizationMember v : volunteers){

        }
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName()+" End.");

    }
}

