package group2.grade15.njuse.blservice;

import group2.grade15.njuse.vo.*;

/**
 * 搜索的接口
 * 根据省，市，区，商圈逐级缩小范围，直到得到最后的酒店列表
 * 还可以根据SearchCondition中含有的数据对酒店列表进行筛选
 * 供界面层的搜索界面调用
 * 职责是处理搜索相关的逻辑功能
 * @author Guo
 */
public interface SearchServ {
    /**
     * 根据搜索条件返回酒店列表的方法
     * 搜索条件包含在SearchConditionVO中
     * 此方法必须在用已经获取了某一商圈的酒店列表后使用
     * 传入的hotelListVO即为该商圈的所有酒店列表
     * @param searchCondition SearchConditionVO型，含有所有搜索条件的数据对象
     * @param hotelListVO HotelListVO型，需要根据条件筛选的酒店列表
     * @return 根据条件筛选后的酒店列表HotelListVO
     */
    public HotelListVO getHotelBySearch(SearchConditionVO searchCondition, HotelListVO hotelListVO);

    /**
     * 获取省的列表
     * @return 省份列表ProvinceListVO
     */
    public ProvinceListVO getProvince();

    /**
     * 根据输入的省名，获取该省份的所有城市名列表
     * @param provinceNum String型，用户在搜索界面选择的省份
     * @return 返回该省份的所有城市列表CityListVO
     */
    public CityListVO getCity(String provinceNum);

    /**
     * 根据输入的城市名，获取该城市的所有地区名列表
     * @param cityNum String型，用户在搜索界面选择的城市
     * @return 返回该城市的所有地区列表DistrictListVO
     */
    public DistrictListVO getDistrict(String cityNum);

    /**
     * 根据输入地区名，获取该地区的所有商圈名列表
     * @param districtNum String型，用户在搜索界面选择的地区名
     * @return 返回该地区所有的商圈列表CbdListVO
     */
    public CbdListVO getCbd(String districtNum);

    /**
     * 根据输入的商圈名，获取该商圈的所有酒店列表
     * @param address String型，用户在搜索界面选择的商圈名
     * @return 返回该商圈的所有酒店列表
     */
    public HotelListVO getHotel(String address);
}
