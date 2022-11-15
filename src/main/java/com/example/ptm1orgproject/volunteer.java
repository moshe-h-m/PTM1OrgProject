package com.example.ptm1orgproject;

public class volunteer extends organizationMember implements volunteerable,Runnable{

    String area_of_volunteering;
    private int hours_of_volunteering;



    public volunteer(String name, String id_member, String area_of_voluntering, int hours_of_voluntering) {
        super(name, id_member);
        this.area_of_volunteering = area_of_voluntering;
        this.hours_of_volunteering = hours_of_voluntering;
    }

    public void setArea_of_volunteering(String area_of_volunteering) {
        this.area_of_volunteering = area_of_volunteering;
    }

    public void setHours_of_volunteering(int hours_of_volunteering) {
        this.hours_of_volunteering = hours_of_volunteering;
    }

    public String getArea_of_volunteering() {
        return area_of_volunteering;
    }

    public int getHours_of_volunteering() {
        return hours_of_volunteering;
    }

    @Override
    public void hoursReporting(int num_of_hours) throws volunteerExeption {
        if(num_of_hours<=hours_for_comunity_per_week)
            throw new volunteerExeption("Dir volunteer!\n"+ "full your hours!");
        this.hours_of_volunteering += num_of_hours;

    }

    public void volunteering(){
        int hurs = this.getHours_of_volunteering();
        this.setHours_of_volunteering(hurs++);
    }

    public void run() {
        boolean flag = false;

        int num = hours_of_volunteering;
        try {
            this.hoursReporting(num);
        } catch (volunteerExeption e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+this.getName());
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName()+" End.");

    }

    @Override
    public String toString() {
        return "volunteer{" +
                ", name='" + name + '\'' +
                ", id_member='" + id_member + '\'' +
                "area_of_volunteering='" + area_of_volunteering + '\'' +
                ", hours_of_volunteering=" + hours_of_volunteering +

                '}';
    }
}
