package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.PromotionState;

import java.io.Serializable;

/**
 * Created by dell on 2016/12/1.
 */
public class RankPO implements Serializable {
    int level;
    int standard;//达到该等级的最低信用
    double discount;
    PromotionState state;

    public RankPO(int level, int standard, double discount, PromotionState state) {
        this.level = level;
        this.standard = standard;
        this.discount = discount;
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public int getStandard() {
        return standard;
    }

    public double getDiscount() {
        return discount;
    }

    public PromotionState getState() {
        return state;
    }
}
