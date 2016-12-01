package group2.grade15.njuse.vo;

import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;

import java.util.Date;

/**
 * Created by Guo on 2016/11/30.
 */
public class SearchConditionVO {
    private SortMethod sortBy;
    private String name;
    private RoomType roomType;
    private double minPrice;
    private double maxPrice;
    private int freeRoomNum;
    private int starLevel;
    private Date checkInTime;
    private Date checkOutTime;
    private double minScore;
    private double maxScore;
    private boolean isBooked;

    public SearchConditionVO(SortMethod sortBy, String name, RoomType roomType, double minPrice, double maxPrice, int freeRoomNum, int starLevel, Date checkInTime, Date checkOutTime, double minScore, double maxScore, boolean isBooked) {
        this.sortBy = sortBy;
        this.name = name;
        this.roomType = roomType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.freeRoomNum = freeRoomNum;
        this.starLevel = starLevel;
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

    public int getStarLevel() {
        return starLevel;
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
