package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.bl.searchbl.filter.SearchFilter;
import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Search implements SearchServ, SearchBL {
    SearchFilter searchFilter;
    public Search(){
        searchFilter = new SearchFilter();
    }

    @Override
    public HotelListVO getHotelBySearch(SearchConditionVO searchCondition, HotelListVO hotelListVO) {

        ArrayList<HotelVO> hotelList = searchFilter.filterByCondition(searchCondition, hotelListVO);

        if(hotelList == null || hotelList.size() == 0) {
            return null;
        } else {
            return new HotelListVO(hotelList);
        }
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

        if(provinceList.size() != 0){
            return new ProvinceListVO(provinceList);
        } else {
            return null;
        }
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

        if(cityList.size() != 0) {
            return new CityListVO(cityList);
        } else {
            return null;
        }
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

        if(districtList.size() != 0) {
            return new DistrictListVO(districtList);
        } else {
            return null;
        }
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

        if(cbdList.size() != 0) {
            return new CbdListVO(cbdList);
        } else {
            return null;
        }
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

        if( hotelList.size() != 0) {
            return new HotelListVO(hotelList);
        } else {
            return null;
        }
    }
}
