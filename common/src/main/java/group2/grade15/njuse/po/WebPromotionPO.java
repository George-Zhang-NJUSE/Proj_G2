package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.WebPromotionType;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by dell on 2016/12/1.
 */
public class WebPromotionPO implements Serializable {
    int promotionID;
    WebPromotionType type;
    Date start;
    Date end;
    String address;//代码
    int level;
    double discount;
    String name;
    PromotionState state;

    public WebPromotionPO(int promotionID, WebPromotionType type, Date start, Date end, String address, int level, double discount, String name, PromotionState state) {
        this.promotionID = promotionID;
        this.type = type;
        this.start = start;
        this.end = end;
        this.address = address;
        this.level = level;
        this.discount = discount;
        this.name = name;
        this.state = state;
    }

    public double count(double price) {
        return discount * price;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public WebPromotionType getType() {
        return type;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getAddress() {
        return address;
    }

    public int getLevel() {
        return level;
    }

    public double getDiscount() {
        return discount;
    }

    public String getName() {
        return name;
    }

    public PromotionState getState() {
        return state;
    }
}
