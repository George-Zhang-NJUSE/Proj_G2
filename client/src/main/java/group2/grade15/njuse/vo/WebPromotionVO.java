package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.WebPromotionType;

import java.sql.Date;

/**
 * Created by Guo on 2016/11/29.
 */
public class WebPromotionVO {
    int promotionID;
    WebPromotionType type;
    Date start;
    Date end;
    String address;//代码
    int level;
    double discount;
    String name;
    PromotionState state;

    public WebPromotionVO(WebPromotionPO po) {
        promotionID = po.getPromotionID();
        type = po.getType();
        start = po.getStart();
        end = po.getEnd();
        address = po.getAddress();
        level = po.getLevel();
        discount = po.getDiscount();
        name = po.getName();
        state = po.getState();
    }

    public WebPromotionVO(int promotionID, WebPromotionType type, Date start, Date end, String address, int level,
                          double discount, String name, PromotionState state) {

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

    public WebPromotionPO toPO() {
        return new WebPromotionPO(promotionID, type, start, end, address, level, discount, name, state);
    }
}
