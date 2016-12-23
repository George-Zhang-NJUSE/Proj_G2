package group2.grade15.njuse.bl.searchbl.filter;


import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

/**
 * 根据SearchCondition中的排序选择进行排序(升序)
 */
public class SortFilter implements FilterBL {
    @Override
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition, ArrayList<HotelVO> hotelList) {
        SortMethod sortBy = searchCondition.getSortBy();
        //将酒店搜索的判定条件简化成一系列新的布尔变量
        boolean isListNotNull = (hotelList != null);
        boolean isSort = (searchCondition.getSortBy() != SortMethod.DEFAULT)
                && isListNotNull;

        //根据搜索条件对酒店列表进行对应的筛选
        if (isSort) {
            ArrayList<HotelVO> newHotelList = new ArrayList();

            switch (sortBy) {
                case DEFAULT:
                    newHotelList = hotelList;
                    break;

                case PRICE:
                    newHotelList = sortByPrice(hotelList);
                    break;

                case STAR_LEVEL:
                    newHotelList = sortByStarLevel(hotelList);
                    break;

                case SCORE:
                    newHotelList = sortByScore(hotelList);
                    break;

                default:
                    break;
            }

            return newHotelList;
        } else {
            return hotelList;
        }
    }

    private ArrayList<HotelVO> sortByPrice(ArrayList<HotelVO> hotelList){
        ArrayList<HotelVO> newHotelList = hotelList;

        for (int i = 1; i < newHotelList.size(); i++) {
            for (int j = 0; j < i; j++) {

                //计算出第一个酒店的最低房价
                double minRoomPrice1 = 0;
                for (RoomVO room : newHotelList.get(i).getRoomList()) {
                    if (minRoomPrice1 > room.getPrice() || minRoomPrice1 == 0) {
                        minRoomPrice1 = room.getPrice();
                    }
                }

                //计算出第二个酒店的最低房价
                double minRoomPrice2 = 0;
                for (RoomVO room : newHotelList.get(j).getRoomList()) {
                    if (minRoomPrice2 > room.getPrice() || minRoomPrice1 == 0) {
                        minRoomPrice2 = room.getPrice();
                    }
                }

                if (minRoomPrice1 < minRoomPrice2) {
                    HotelVO temp = newHotelList.get(j);
                    newHotelList.set(j, newHotelList.get(i));
                    newHotelList.set(i, temp);
                }
            }
        }

        return newHotelList;
    }

    private ArrayList<HotelVO> sortByStarLevel(ArrayList<HotelVO> hotelList){
        ArrayList<HotelVO> newHotelList = hotelList;

        for (int i = 1; i < newHotelList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (newHotelList.get(i).getRank() < newHotelList.get(j).getRank()) {
                    HotelVO temp = newHotelList.get(j);
                    newHotelList.set(j, newHotelList.get(i));
                    newHotelList.set(i, temp);
                }
            }
        }

        return newHotelList;
    }

    private ArrayList<HotelVO> sortByScore(ArrayList<HotelVO> hotelList){
        ArrayList<HotelVO> newHotelList = hotelList;

        for (int i = 1; i < newHotelList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (newHotelList.get(i).getScore() < newHotelList.get(j).getScore()) {
                    HotelVO temp = newHotelList.get(j);
                    newHotelList.set(j, newHotelList.get(i));
                    newHotelList.set(i, temp);
                }
            }
        }

        return newHotelList;
    }
}
