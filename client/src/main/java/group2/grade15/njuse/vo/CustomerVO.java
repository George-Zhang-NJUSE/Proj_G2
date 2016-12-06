package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.MemberType;

import java.io.Serializable;
import java.sql.Date;

public class CustomerVO implements Serializable {
    private int id;
    private String name;
    private String password;
    private String contact;
    private Date birthday;
    private final double credit;
    private MemberType type;
    private String companyName;

    public CustomerVO(CustomerPO po) {
        this.id = po.getId();
        this.name = po.getName();
        this.password = po.getPassword();
        this.contact = po.getContact();
        this.birthday = po.getBirthday();
        this.credit = po.getCredit();
        this.type = po.getType();
        this.companyName = po.getCompanyName();
    }

    public CustomerVO(int id, String name, String password, String contact, Date birthday, double credit, MemberType type, String companyName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.birthday = birthday;
        this.credit = credit;
        this.type = type;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public Date getBirthday() {
        return birthday;
    }

    public double getCredit() {
        return credit;
    }

    public MemberType getType() {
        return type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CustomerPO toPO() {
        return new CustomerPO(id, name, password, contact, birthday, credit, type, companyName);
    }

}
