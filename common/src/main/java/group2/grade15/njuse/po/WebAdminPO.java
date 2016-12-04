package group2.grade15.njuse.po;

import java.io.Serializable;

;

public class WebAdminPO implements Serializable {
    private String password;
    private String staffID;

    public WebAdminPO(String password, String staffID) {
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
