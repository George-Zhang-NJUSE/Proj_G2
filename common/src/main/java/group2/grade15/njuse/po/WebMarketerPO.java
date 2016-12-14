package group2.grade15.njuse.po;

import java.io.Serializable;

;

public class WebMarketerPO implements Serializable {
    private String password;
    private String staffID;

    public WebMarketerPO(String password, String staffID) {
        this.password = password;
        this.staffID = staffID;
    }

    public String getPassword() {
        return password;
    }

    public String getStaffID() {
        return staffID;
    }

}
