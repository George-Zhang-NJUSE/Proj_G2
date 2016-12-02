package group2.grade15.njuse.po;

import java.io.Serializable;

/**
 * Created by dell on 2016/12/1.
 */
public class RankPO implements Serializable{
    int levelSum;//总共有几级（不超过6级）
    int[] standard;//每个级别的最低信用值标准

    public RankPO(int levelSum, int[] standard) {
        this.levelSum = levelSum;
        this.standard = standard;
    }

    public int getLevelSum() {
        return levelSum;
    }

    public int[] getStandard() {
        return standard;
    }
}
