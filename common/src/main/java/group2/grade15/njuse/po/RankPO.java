package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.PromotionState;

import java.io.Serializable;

/**
 * Created by dell on 2016/12/1.
 */
public class RankPO implements Serializable{
    int levelSum;//总共有几级（不超过6级）
    int[] standard;//每个级别的最低信用值标准
    double[] discount;
    PromotionState state;

    public RankPO(int levelSum, int[] standard, double[] discount,PromotionState state) {
        this.levelSum = levelSum;
        this.standard = standard;
        this.discount=discount;
        this.state=state;
    }

    public int getLevelSum() {
        return levelSum;
    }

    public int[] getStandard() {
        return standard;
    }

    public double[] getDiscount() {
        return discount;
    }

    public PromotionState getState() {
        return state;
    }
}
