package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

/**
 * 酒店业务逻辑的层间接口
 * 供界面层的酒店界面调用
 * 职责是处理酒店相关的逻辑功能
 * @author Guo
 */
public interface HotelServ {
    /**
     * 修改酒店的信息
     * @param hotel HotelVO型，界面层传递来的存有修改信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyInfo(HotelVO hotel);

    /**
     * 获取酒店的信息
     * @param hotelID int型，界面层传递来的酒店ID
     * @return 成功返回对应的HotelVO
     *         失败返回null
     */
    public HotelVO getInfo(int hotelID);

    /**
     * 修改酒店的房间信息
     * @param hotelID int型，界面层传递来需要修改房间信息的酒店ID
     * @param roomInfo RoomVO型，界面层传来包含修改后的房间信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo);

    /**
     * 增加酒店的房间种类
     * @param hotelID int型，界面层传递来需要增加房间种类的酒店ID
     * @param roomVO RoomVO型，界面层传来需要增添的房类信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage addRoomType(int hotelID, RoomVO roomVO);

    /**
     * 删除酒店的房间种类
     * @param hotelID int型，界面层传递来需要删除房间种类的酒店ID
     * @param type RoomType型，界面层传来需要删除的房型
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage deleteRoomType(int hotelID, RoomType type);

    /**
     * 获取用户预订过的酒店列表
     * @param customerID int型，界面层传递来的需要获取列表的客户ID
     * @return 成功返回对应的HotelListVO
     *         失败返回null
     */
    public HotelListVO getBookedHotelList(int customerID);

    /**
     * 删除酒店的图片
     * @param picNum int型，界面层传递来的需要删除的图片ID
     * @param hotelID int型，界面层传递来的需要删除的图片所在的酒店ID
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage deletePic(int picNum, int hotelID);

    /**
     * 上传酒店的图片
     * @param picture byte[][]型，界面层传递来的需要上传的图片ID
     * @param hotelID int型，界面层传递来的需要上传的图片所在的酒店ID
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage uploadPic(byte[][] picture, int hotelID);

    /**
     * 根据输入的省的编码，获取该省份的名称
     * @param provinceNum int型，用户需要翻译的省份编码
     * @return 返回该省份的名称
     */
    public String getProvinceName(String provinceNum);

    /**
     * 根据输入的城市的编码，获取该城市的名称
     * @param cityNum int型，用户需要翻译的城市编码
     * @return 返回该城市的名称
     */
    public String getCityName(String cityNum);

    /**
     * 根据输入的城市的编码，获取该城市的名称
     * @param districtNum int型，用户需要翻译的地区编码
     * @return 返回该城市的名称
     */
    public String getDistrictName(String districtNum);

    /**
     * 根据输入的商圈的编码，获取该商圈的名称
     * @param cbdNum int型，用户需要翻译的商圈编码
     * @return 返回该城市的名称
     */
    public String getCbdName(String cbdNum);
}
