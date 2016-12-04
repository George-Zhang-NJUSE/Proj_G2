package group2.grade15.njuse.po;

import java.io.Serializable;

public class HotelManagerPO implements Serializable {
    private int id;
    private String password;
    private String name;
    private String contact;
    private int hotelID;

    public HotelManagerPO(int id, String password, String name, String contact, int hotelID) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.hotelID = hotelID;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public int getHotelID() {
        return hotelID;
    }
}
