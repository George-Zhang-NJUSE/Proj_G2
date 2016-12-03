package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.HotelPromotionPO;
import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/11/29.
 */
public class HotelPromotionVO {
    int promotionID;
    HotelPromotionType type;
    Date start;
    Date end;
    ArrayList<Integer> vipList;
    double discount;
    String name;
    PromotionState state;

    public HotelPromotionVO(HotelPromotionPO po){
        promotionID = po.getPromotionID();
        type = po.getType();
        start = po.getStart();
        end = po.getEnd();
        vipList = po.getVipList();
        discount = po.getDiscount();
        name = po.getName();
        state = po.getState();
    }

    public HotelPromotionVO(int promotionID, HotelPromotionType type, Date start, Date end, ArrayList<Integer> vipList, double discount, String name, PromotionState state) {
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

    public HotelPromotionPO toPO(){
        return new HotelPromotionPO(promotionID, type, start, end, vipList, discount, name, state);
    }
}
