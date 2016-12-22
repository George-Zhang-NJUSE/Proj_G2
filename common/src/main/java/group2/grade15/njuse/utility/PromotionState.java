package group2.grade15.njuse.utility;

/**
 * Created by dell on 2016/12/1.
 */
public enum PromotionState {
    start,
    unlaunched,
    stop;
    public String toString(){
        switch (this) {
            case start:
                return"激活";
            case unlaunched:
                return "未启动";
            case stop:
                return "已停止";
        }
        return "";
    }
}
