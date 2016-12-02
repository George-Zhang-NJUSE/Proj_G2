package group2.grade15.njuse.vo;

import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;

import java.util.Date;

/**
 * Created by Guo on 2016/11/30.
 */
public class SearchConditionVO {
    private SortMethod sortBy;              //酒店列表的排序方式，若客户未选，则设置为DEFAULT
    private String name;                    //酒店的名字（可以是关键字），若客户未填写，则设置为null
    private RoomType roomType;              //顾客所需要的房间类型，若客户未选，则设置为all
    private double minPrice;                //顾客所接受的房间最低价格，若客户未填写，则设置为0
    private double maxPrice;                //顾客所接受的房间最大价格，若客户未填写，则设置为0
    private int freeRoomNum;                //顾客所接受的酒店房间的最小空闲量，若客户未填写，则设置为0
    private int minStarLevel;               //顾客所接受的酒店最低星级，若客户未填写，则设置为0
    private int maxStarLevel;               //顾客所接受的酒店最高星级，若客户未填写，则设置为5
    private Date checkInTime;               //顾客的预计入住时段，若客户未填写，则设置为null
    private Date checkOutTime;              //顾客的预计退房时间，若客户未填写，则设置为null
    private double minScore;                //顾客所接受的酒店最低评分，若客户未填写，则设置为0
    private double maxScore;                //顾客所接受的酒店最高评分，若客户未填写，则设置为10
    private boolean isBooked;               //顾客选择是否在已预订过的酒店中搜索，若客户未填写，则设置为false

    public SearchConditionVO(SortMethod sortBy, String name, RoomType roomType, double minPrice, double maxPrice, int freeRoomNum, int minStarLevel, int maxStarLevel, Date checkInTime, Date checkOutTime, double minScore, double maxScore, boolean isBooked) {
        this.sortBy = sortBy;
        this.name = name;
        this.roomType = roomType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.freeRoomNum = freeRoomNum;
        this.minStarLevel = minStarLevel;
        this.maxStarLevel = maxStarLevel;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.isBooked = isBooked;
    }

    public SortMethod getSortBy() {
        return sortBy;
    }

    public String getName() {
        return name;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public int getFreeRoomNum() {
        return freeRoomNum;
    }

    public int getMinStarLevel() {
        return minStarLevel;
    }

    public int getMaxStarLevel() {
        return maxStarLevel;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public double getMinScore() {
        return minScore;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public boolean isBooked() {
        return isBooked;
    }
}
