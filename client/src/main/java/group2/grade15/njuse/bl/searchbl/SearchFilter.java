package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.bl.hotelbl.GetHotelListBL;
import group2.grade15.njuse.bl.hotelbl.GetSpareRoomNumBL;
import group2.grade15.njuse.bl.hotelbl.HotelController;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/8.
 */
public class SearchFilter implements SearchFilterBL {
    GetSpareRoomNumBL getSpareRoomNum;

    public SearchFilter(){
        getSpareRoomNum = new HotelController();
    }

    @Override
    public ArrayList<HotelVO> filterByBooked(int customerID, ArrayList<HotelVO> hotelList) {
        GetHotelListBL getHotelListBL = new HotelController();
        HotelListVO hotelListVO = getHotelListBL.getBookedHotelList(customerID);
        ArrayList<HotelVO> bookedHotelList = hotelListVO.getList();
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
    }

    @Override
    public ArrayList<HotelVO> sort(SortMethod sortBy, ArrayList<HotelVO> hotelList) {
        ArrayList<HotelVO> newHotelList = new ArrayList();

        switch (sortBy) {
            case DEFAULT:
                newHotelList = hotelList;
                break;

            case PRICE:
                newHotelList = hotelList;
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
                break;

            case STAR_LEVEL:
                newHotelList = hotelList;
                for (int i = 1; i < newHotelList.size(); i++) {
                    for (int j = 0; j < i; j++) {
                        if (newHotelList.get(i).getRank() < newHotelList.get(j).getRank()) {
                            HotelVO temp = newHotelList.get(j);
                            newHotelList.set(j, newHotelList.get(i));
                            newHotelList.set(i, temp);
                        }
                    }
                }
                break;

            case SCORE:
                newHotelList = hotelList;
                for (int i = 1; i < newHotelList.size(); i++) {
                    for (int j = 0; j < i; j++) {
                        if (newHotelList.get(i).getScore() < newHotelList.get(j).getScore()) {
                            HotelVO temp = newHotelList.get(j);
                            newHotelList.set(j, newHotelList.get(i));
                            newHotelList.set(i, temp);
                        }
                    }
                }
                break;

            default:
                break;
        }

        return newHotelList;
    }

    @Override
    public ArrayList<HotelVO> filterByName(String name, ArrayList<HotelVO> hotelList) {
        String[] keyWords = name.split(" ");
        boolean isContain = false;
        ArrayList<HotelVO> newHotelList = new ArrayList();

        for (HotelVO hotel : hotelList) {
            //判定酒店的名字是否至少含有一个关键字
            for (String word : keyWords) {
                if (hotel.getName().contains(word)) {
                    isContain = true;
                }
            }

            //如果含有关键字，则加入到新的酒店列表中
            if (isContain) {
                newHotelList.add(hotel);
                isContain = false;
            }
        }

        return newHotelList;
    }

    @Override
    public ArrayList<HotelVO> filterByRoomPrice(double minPrice, double maxPrice, ArrayList<HotelVO> hotelList) {
        ArrayList<HotelVO> newHotelList = new ArrayList();
        boolean isContain = false; //酒店是否含有该区间价位的房间的标志

        for (HotelVO hotel : hotelList) {
            ArrayList<RoomVO> roomList = hotel.getRoomList();
            for (RoomVO room : roomList) {
                if (room.getPrice() <= maxPrice && room.getPrice() >= minPrice) {
                    isContain = true;
                }
            }

            if (isContain) {
                newHotelList.add(hotel);
                isContain = false;
            }
        }

        return newHotelList;
    }

    @Override
    public ArrayList<HotelVO> filterByRoomType(RoomType roomType, ArrayList<HotelVO> hotelList) {
        ArrayList<HotelVO> newHotelList = new ArrayList();
        boolean isFit = false;

        for (HotelVO hotel : hotelList) {

            ArrayList<RoomVO> roomList = hotel.getRoomList();
            for(RoomVO room : roomList){
                if( room.getType() == roomType ){
                    isFit = true;
                }
            }

            if(isFit){
                newHotelList.add(hotel);
                isFit = false;
            }

        }

        return newHotelList;
    }

    @Override
    public ArrayList<HotelVO> filterByStarLevel(int minStarLevel, ArrayList<HotelVO> hotelList) {
        ArrayList<HotelVO> newHotelList = new ArrayList();

        for (HotelVO hotelVO : hotelList) {
            if (hotelVO.getRank() >= minStarLevel) {
                newHotelList.add(hotelVO);
            }
        }

        return newHotelList;
    }

    @Override
    public ArrayList<HotelVO> filterByScore(double minScore, double maxScore, ArrayList<HotelVO> hotelList) {
        ArrayList<HotelVO> newHotelList = new ArrayList();

        for (HotelVO hotelVO : hotelList) {
            if ((hotelVO.getScore() >= minScore) && (hotelVO.getScore() <= maxScore)) {
                newHotelList.add(hotelVO);
            }
        }

        return newHotelList;
    }

    @Override
    public ArrayList<HotelVO> filterByTime(java.util.Date checkInTime, java.util.Date checkOutTime, int needRoom, RoomType type, ArrayList<HotelVO> hotelList) {
        ArrayList<HotelVO> newHotelList = new ArrayList();

        for (HotelVO hotel : hotelList) {

            Date checkIn = new Date(checkInTime.getTime());
            Date checkOut = new Date(checkOutTime.getTime());
            int spareRoomNum = getSpareRoomNum.getSpareRoomNumInTime(type, hotel.getId(), checkIn, checkOut);

            if((spareRoomNum < 10000) && (spareRoomNum >= needRoom)){
                newHotelList.add(hotel);
            }

        }
        return newHotelList;
    }
}
