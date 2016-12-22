package group2.grade15.njuse.utility;

public enum OrderState {
    unexecuted,
    executed,
    revoked,
    abnormal,
    complete;

    public String toString(){
        switch (this) {
            case unexecuted:
                return "未执行";
            case executed:
                return "已执行";
            case revoked:
                return "已撤销";
            case abnormal:
                return "异常";
            case complete:
                return "已完成";
            default:
                return "";
        }
    }
}
