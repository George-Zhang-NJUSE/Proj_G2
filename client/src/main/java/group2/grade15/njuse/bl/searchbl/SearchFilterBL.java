package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/8.
 */
public interface SearchFilterBL {
    /**
     * 根据SearchCondition中的isBooked对获得的酒店列表进行一次筛选
     */
    public ArrayList<HotelVO> filterByBooked(int customerID, ArrayList<HotelVO> hotelList);

    /**
     * 根据SearchCondition中的排序选择进行排序(升序)
     */
    public ArrayList<HotelVO> sort(SortMethod sortBy, ArrayList<HotelVO> hotelList);

    /**
     * 根据SearchCondition中的酒店名称对获得的酒店列表进行一次筛选
     */
    public ArrayList<HotelVO> filterByName(String name, ArrayList<HotelVO> hotelList);

    /**
     * 根据SearchCondition中的房间价格对获得的酒店列表进行一次筛选
     */
    public ArrayList<HotelVO> filterByRoomPrice(double minPrice, double maxPrice, ArrayList<HotelVO> hotelList);

    /**
     * 根据SearchCondition中的房间类型对获得的酒店列表进行一次筛选
     */
    public ArrayList<HotelVO> filterByRoomType(RoomType roomType, ArrayList<HotelVO> hotelList);
    /**
     * 根据SearchCondition中的酒店星级区间对获得的酒店列表进行一次筛选
     */
    public ArrayList<HotelVO> filterByStarLevel(int minStarLevel, ArrayList<HotelVO> hotelList);
    /**
     * 根据SearchCondition中的酒店评分区间对获得的酒店列表进行一次筛选
     */
    public ArrayList<HotelVO> filterByScore(double minScore, double maxScore, ArrayList<HotelVO> hotelList);

    /**
     * 根据SearchCondition中的入住时间信息和所需房间的所剩量对获得的酒店列表进行一次筛选
     */
    public ArrayList<HotelVO> filterByTime(java.util.Date checkInTime, java.util.Date checkOutTime, int needRoom, RoomType type, ArrayList<HotelVO> hotelList);

}
