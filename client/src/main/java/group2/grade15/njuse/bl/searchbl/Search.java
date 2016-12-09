package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.bl.hotelbl.GetHotelListBL;
import group2.grade15.njuse.bl.hotelbl.GetSpareRoomNumBL;
import group2.grade15.njuse.bl.hotelbl.HotelController;
import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Search implements SearchServ {
    SearchFilterBL searchFilter;

    public Search(){
        searchFilter = new SearchFilter();
    }

    @Override
    public HotelListVO getHotelBySearch(SearchConditionVO searchCondition, HotelListVO hotelListVO) {
        ArrayList<HotelVO> hotelList = hotelListVO.getList();

        //将酒店搜索的判定条件简化成一系列新的布尔变量
        boolean isListNull = (hotelList != null);
        boolean isSort = (searchCondition.getSortBy() != SortMethod.DEFAULT) && isListNull;
        boolean isFilterName = (searchCondition.getName() != null)
                                && isListNull;
        boolean isFilterStar = (searchCondition.getMinStarLevel() != 0)
                                && isListNull;
        boolean isFilterScore = (searchCondition.getMinScore() != 0
                                 || searchCondition.getMaxScore() != 10)
                                 && isListNull;
        boolean isFilterPrice = (searchCondition.getMinPrice() != 0
                                 || searchCondition.getMaxPrice() != 0)
                                 && isListNull;
        boolean isFilterRoom = (searchCondition.getFreeRoomNum() != 0
                                || searchCondition.getRoomType() != RoomType.all)
                                && isListNull;
        boolean isFilterTime = (searchCondition.getCheckInTime() != null
                                || searchCondition.getCheckOutTime() != null)
                                && isListNull;

        //根据搜索条件对酒店列表进行对应的筛选
        if (isListNull) {
            hotelList = searchFilter.filterByBooked(searchCondition.getCsutomerID(), hotelList);
        }

        if (isSort) {
            hotelList = searchFilter.sort(searchCondition.getSortBy(), hotelList);
        }

        if (isFilterName) {
            hotelList = searchFilter.filterByName(searchCondition.getName(), hotelList);
        }

        if (isFilterStar) {
            hotelList = searchFilter.filterByStarLevel(searchCondition.getMinStarLevel(), hotelList);
        }

        if (isFilterScore) {
            hotelList = searchFilter.filterByScore(searchCondition.getMinScore(), searchCondition.getMaxScore(), hotelList);
        }

        if (isFilterPrice) {
            hotelList = searchFilter.filterByRoomPrice(searchCondition.getMinPrice(), searchCondition.getMaxPrice(), hotelList);
        }

        if (isFilterRoom) {
            hotelList = searchFilter.filterByRoomType(searchCondition.getRoomType(), hotelList);
        }

        if (isFilterTime) {
            hotelList = searchFilter.filterByTime(searchCondition.getCheckInTime(), searchCondition.getCheckOutTime(), searchCondition.getFreeRoomNum(), searchCondition.getRoomType(), hotelList);
        }

        return new HotelListVO(hotelList);
    }

    @Override
    public ProvinceListVO getProvince() {
        ArrayList<ProvincePO> provincePOList = new ArrayList();
        ArrayList<ProvinceVO> provinceList = new ArrayList();

        try {
            provincePOList = RemoteHelper.getInstance().getAreaDataService().getProvince();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (ProvincePO po : provincePOList) {
            provinceList.add(new ProvinceVO(po));
        }

        return new ProvinceListVO(provinceList);
    }

    @Override
    public CityListVO getCity(String provinceNum) {
        ArrayList<CityPO> cityPOList = new ArrayList();
        ArrayList<CityVO> cityList = new ArrayList();

        try {
            cityPOList = RemoteHelper.getInstance().getAreaDataService().getCity(provinceNum);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (CityPO po : cityPOList) {
            cityList.add(new CityVO(po));
        }

        return new CityListVO(cityList);
    }

    @Override
    public DistrictListVO getDistrict(String cityNum) {
        ArrayList<DistrictPO> districtPOList = new ArrayList();
        ArrayList<DistrictVO> districtList = new ArrayList();

        try {
            districtPOList = RemoteHelper.getInstance().getAreaDataService().getDistrict(cityNum);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (DistrictPO po : districtPOList) {
            districtList.add(new DistrictVO(po));
        }
        return new DistrictListVO(districtList);
    }

    @Override
    public CbdListVO getCbd(String districtNum) {
        ArrayList<CbdPO> cbdPOList = new ArrayList();
        ArrayList<CbdVO> cbdList = new ArrayList();

        try {
            cbdPOList = RemoteHelper.getInstance().getAreaDataService().getCbd(districtNum);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (CbdPO po : cbdPOList) {
            cbdList.add(new CbdVO(po));
        }
        return new CbdListVO(cbdList);
    }

    @Override
    public HotelListVO getHotel(String address) {
        ArrayList<HotelPO> hotelPOList = new ArrayList();
        ArrayList<HotelVO> hotelList = new ArrayList();

        try {
            hotelPOList = RemoteHelper.getInstance().getAreaDataService().getHotelByAddress(address);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (HotelPO po : hotelPOList) {
            hotelList.add(new HotelVO(po));
        }
        return new HotelListVO(hotelList);
    }

}
