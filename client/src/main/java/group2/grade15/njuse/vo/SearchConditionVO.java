package group2.grade15.njuse.vo;

import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;

import java.util.Date;

/**
 * Created by Guo on 2016/11/30.
 */
public class SearchConditionVO {
    SortMethod sortBy;
    String name;
    RoomType roomType;
    double minPrice;
    double maxPrice;
    int freeRoomNum;
    int starLevel;
    Date checkInTime;
    Date checkOutTime;
    double minScore;
    double maxScore;
    boolean isBooked;
}
