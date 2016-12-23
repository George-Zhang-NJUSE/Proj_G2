package group2.grade15.njuse.bl.searchbl.filter;


import group2.grade15.njuse.bl.hotelbl.GetHotelListBL;
import group2.grade15.njuse.bl.hotelbl.HotelController;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

/**
 * 根据SearchCondition中的isBooked对获得的酒店列表进行一次筛选
 */
public class BookedFileter implements FilterBL {
    @Override
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition, ArrayList<HotelVO> hotelList) {
        int customerID = searchCondition.getCsutomerID();

        //将酒店搜索的判定条件简化成新的布尔变量
        boolean isListNotNull = (hotelList != null);
        boolean isBooked = (searchCondition.isBooked() && isListNotNull);

        //根据搜索条件对酒店列表进行对应的筛选
        if (isBooked) {
            GetHotelListBL getHotelListBL = new HotelController();
            HotelListVO hotelVOs = getHotelListBL.getBookedHotelList(customerID);

            if (hotelVOs != null) {
                ArrayList<HotelVO> bookedHotelList = hotelVOs.getList();
                ArrayList<HotelVO> newHotelList = new ArrayList();

                for (HotelVO hotel : hotelList) {
                    for (HotelVO bookedHotel : bookedHotelList) {
                        if (hotel.getId() == bookedHotel.getId()) {
                            newHotelList.add(hotel);
                            break;
                        }
                    }
                }

                return newHotelList;
            } else {
                return hotelList;
            }
        }
        return hotelList;
    }
}
