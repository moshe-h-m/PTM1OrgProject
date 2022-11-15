package com.example.ptm1orgproject;

public abstract class organizationMember {
    String name;
    String id_member;

    public organizationMember(String name, String id_member) {
        this.name = name;
        this.id_member = id_member;
    }

    public String getName() {
        return name;
    }

    public String getId_member() {
        return id_member;
    }

    @Override
    public String toString() {
        return "organizationMember{" +
                "name='" + name + '\'' +
                ", id_member='" + id_member + '\'' +
                '}';
    }

}
