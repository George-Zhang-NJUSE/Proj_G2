package group2.grade15.njuse.blservice;

import group2.grade15.njuse.vo.*;


/**
 * 搜索的接口
 * 根据省，市，区，商圈逐级缩小范围，直到得到最后的酒店列表
 * 还可以根据SearchCondition中含有的数据对酒店列表进行筛选
 */
public interface SearchServ {

    /**
     * 根据搜索条件返回酒店列表的方法
     * 搜索条件包含在SearchConditionVO中
     * 此方法必须在用已经获取了某一商圈的酒店列表后使用
     * 传入的hotelListVO即为该商圈的所有酒店列表
     */
    public HotelListVO getHotelBySearch(SearchConditionVO searchCondition, HotelListVO hotelListVO);

    /**
     * 获取省的列表
     */
    public ProvinceListVO getProvince();

    /**
     * 根据输入的省名，获取该省份的所有城市名列表
     */
    public CityListVO getCity(String provinceNum);

    /**
     * 根据输入的城市名，获取该城市的所有地区名列表
     */
    public DistrictListVO getDistrict(String cityNum);

    /**
     * 根据输入地区名，获取该地区的所有商圈名列表
     */
    public CbdListVO getCbd(String districtNum);

    /**
     * 根据输入的商圈名，获取该商圈的所有酒店列表
     */
    public HotelListVO getHotel(String address);
}
