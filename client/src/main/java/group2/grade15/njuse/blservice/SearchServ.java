package group2.grade15.njuse.blservice;

import group2.grade15.njuse.vo.*;

import java.util.ArrayList;

/**
 * 搜索的接口
 * 根据省，市，区，商圈逐级缩小范围，直到得到最后的酒店列表
 * 还可以根据SearchCondition中含有的数据对酒店列表进行筛选
 */
public interface SearchServ {

    public ArrayList<HotelVO> getHotelBySearch(SearchConditionVO searchCondition, HotelListVO hotelListVO);

	public ArrayList<ProvinceVO> getProvince();

	public ArrayList<CityVO> getCity(String provinceNum);

	public ArrayList<DistrictVO> getDistrict(String cityNum);

	public ArrayList<CbdVO> getCbd(String districtNum);

	public ArrayList<HotelVO> getHotel(String address);
}
