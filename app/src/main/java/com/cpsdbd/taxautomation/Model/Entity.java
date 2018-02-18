package com.cpsdbd.taxautomation.Model;

import java.io.Serializable;

/**
 * Created by Sohel on 2/17/2018.
 */

public class Entity implements Serializable{
    private String id;
    private String name;
    private String fName;
    private String nID;
    private String tIN;
    private double lat;
    private double lng;
    private String address;
    private double assesmentTax;
    private int status;


    public Entity() {
    }


    public Entity(String id, String name, String fName, String nID, String tIN, double lat, double lng, String address, double assesmentTax, int status) {
        this.id = id;
        this.name = name;
        this.fName = fName;
        this.nID = nID;
        this.tIN = tIN;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.assesmentTax = assesmentTax;
        this.status = status;
    }

    public Entity(String name, String fName, String nID, String tIN, double lat, double lng, String address, double assesmentTax, int status) {
        this("",name,fName,nID,tIN,lat,lng,address,assesmentTax,status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getnID() {
        return nID;
    }

    public void setnID(String nID) {
        this.nID = nID;
    }

    public String gettIN() {
        return tIN;
    }

    public void settIN(String tIN) {
        this.tIN = tIN;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAssesmentTax() {
        return assesmentTax;
    }

    public void setAssesmentTax(double assesmentTax) {
        this.assesmentTax = assesmentTax;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
