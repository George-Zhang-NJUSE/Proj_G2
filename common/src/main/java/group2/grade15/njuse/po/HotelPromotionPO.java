package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by dell on 2016/12/1.
 */
public class HotelPromotionPO implements Serializable{
    int promotionID;
    HotelPromotionType type;
    Date start;
    Date end;
    ArrayList<Integer> vipList;
    double discount;
    String name;
    PromotionState state;

    public HotelPromotionPO(int promotionID, HotelPromotionType type, Date start, Date end, ArrayList<Integer> vipList, double discount, String name, PromotionState state) {

        this.promotionID = promotionID;
        this.type = type;
        this.start = start;
        this.end = end;
        this.vipList = vipList;
        this.discount = discount;
        this.name = name;
        this.state = state;
    }

    public double count(double price){
        return discount*price;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public HotelPromotionType getType() {
        return type;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public ArrayList<Integer> getVipList() {
        return vipList;
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
