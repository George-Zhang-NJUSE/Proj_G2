package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.hotelbl.HotelList;
import group2.grade15.njuse.po.DistrictPO;
import group2.grade15.njuse.vo.*;

import java.util.ArrayList;

/**
 * 搜索的接口
 * 根据省，市，区，商圈逐级缩小范围，直到得到最后的酒店列表
 * 还可以根据SearchCondition中含有的数据对酒店列表进行筛选
 */
public interface SearchServ {

    public HotelListVO getHotelBySearch(SearchConditionVO searchCondition, HotelListVO hotelListVO);

	public ProvinceListVO getProvince();

	public CityListVO getCity(String provinceNum);

	public DistrictListVO getDistrict(String cityNum);

	public CbdListVO getCbd(String districtNum);

	public HotelListVO getHotel(String address);
}
