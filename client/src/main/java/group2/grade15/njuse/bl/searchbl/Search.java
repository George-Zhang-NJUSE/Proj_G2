package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

        try {
            provincePOList = RemoteHelper.getInstance().getAreaDataService().getProvince();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<ProvinceVO> provinceList = new ArrayList();
        provinceList.addAll(provincePOList.stream()
                                          .map(ProvinceVO::new)
                                          .collect(Collectors.toList()));

        return new ProvinceListVO(provinceList);
    }

    @Override
    public CityListVO getCity(String provinceNum) {
        ArrayList<CityPO> cityPOList = new ArrayList();

        try {
            cityPOList = RemoteHelper.getInstance().getAreaDataService().getCity(provinceNum);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<CityVO> cityList = new ArrayList();
        cityList.addAll(cityPOList.stream()
                                  .map(CityVO::new)
                                  .collect(Collectors.toList()));

        return new CityListVO(cityList);
    }

    @Override
    public DistrictListVO getDistrict(String cityNum) {
        ArrayList<DistrictPO> districtPOList = new ArrayList();

        try {
            districtPOList = RemoteHelper.getInstance().getAreaDataService().getDistrict(cityNum);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<DistrictVO> districtList = new ArrayList();
        districtList.addAll(districtPOList.stream()
                                          .map(DistrictVO::new)
                                          .collect(Collectors.toList()));

        return new DistrictListVO(districtList);
    }

    @Override
    public CbdListVO getCbd(String districtNum) {
        ArrayList<CbdPO> cbdPOList = new ArrayList();

        try {
            cbdPOList = RemoteHelper.getInstance().getAreaDataService().getCbd(districtNum);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<CbdVO> cbdList = new ArrayList();
        cbdList.addAll(cbdPOList.stream()
                                .map(CbdVO::new)
                                .collect(Collectors.toList()));

        return new CbdListVO(cbdList);
    }

    @Override
    public HotelListVO getHotel(String address) {
        ArrayList<HotelPO> hotelPOList = new ArrayList();

        try {
            hotelPOList = RemoteHelper.getInstance().getAreaDataService().getHotelByAddress(address);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<HotelVO> hotelList = new ArrayList();
        hotelList.addAll(hotelPOList.stream()
                                    .map(HotelVO::new)
                                    .collect(Collectors.toList()));

        if( hotelList.size() == 0) {
            return null;
        } else {
            return new HotelListVO(hotelList);
        }
    }

}
