package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class Search implements SearchServ{

	@Override
	public HotelListVO getHotelBySearch(SearchConditionVO searchCondition, HotelListVO hotelListVO) {
		ArrayList<HotelVO> hotelList = hotelListVO.getList();

		if ( hotelList != null) {
			hotelList = filterByBooked(searchCondition.getCsutomerID(), hotelList);
		}

		if (searchCondition.getSortBy() != SortMethod.DEFAULT && hotelList != null) {
			hotelList = sort(searchCondition.getSortBy(), hotelList);
		}

		if (searchCondition.getName() != null && hotelList != null) {
			hotelList = filterByName(searchCondition.getName(), hotelList);
		}

		if (searchCondition.getMinStarLevel() != 0 || searchCondition.getMaxStarLevel() != 5 && hotelList != null){
			hotelList = filterByStarLevel(searchCondition.getMinStarLevel(), searchCondition.getMaxStarLevel(), hotelList);
		}

		if(searchCondition.getMinScore() != 0 || searchCondition.getMaxScore() != 10 && hotelList != null) {
			hotelList = filterByScore(searchCondition.getMinScore(), searchCondition.getMaxScore(), hotelList);
		}

		if(searchCondition.getCheckInTime() != null || searchCondition.getCheckOutTime() != null && hotelList != null) {
			hotelList = filterByTime(searchCondition.getCheckInTime(), searchCondition.getCheckOutTime(), hotelList);
		}

		if(searchCondition.getMinPrice() != 0 || searchCondition.getMaxPrice() != 0 && hotelList != null){
			hotelList = filterByRoomPrice(searchCondition.getMinPrice(), searchCondition.getMaxPrice(), hotelList);
		}

		if(searchCondition.getFreeRoomNum() != 0 || searchCondition.getRoomType() != RoomType.all && hotelList != null){
			hotelList = filterByRoomInfo(searchCondition.getRoomType(), searchCondition.getFreeRoomNum(), hotelList);
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

		for(ProvincePO po : provincePOList){
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

		for(CityPO po : cityPOList){
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

		for(DistrictPO po : districtPOList){
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

		for(CbdPO po : cbdPOList){
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

		for(HotelPO po : hotelPOList){
			hotelList.add(new HotelVO(po));
		}
		return new HotelListVO(hotelList);
	}

	/**
	 * 根据SearchCondition中的isBooked对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByBooked(int customerID, ArrayList<HotelVO> hotelList){

		return null;
	}

	/**
	 * 根据SearchCondition中的排序选择进行排序
	 */
	private ArrayList<HotelVO> sort(SortMethod sortBy, ArrayList<HotelVO> hotelList){
		ArrayList<HotelVO> newHotelList = new ArrayList();

		switch (sortBy){
			case DEFAULT:

				break;

			case PRICE:

				break;

			case STAR_LEVEL:

				break;

			case SCORE:

				break;

			default:
				break;
		}

		return newHotelList;
	}

	/**
	 * 根据SearchCondition中的酒店名称对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByName(String name, ArrayList<HotelVO> hotelList){
		String[] keyWords = name.split(" ");
		boolean isContain = false;
		ArrayList<HotelVO> newHotelList = new ArrayList();

		for(HotelVO vo : hotelList){
			//判定酒店的名字是否至少含有一个关键字
			for(String word : keyWords) {
				if(vo.getName().contains(word)){
					isContain = true;
				}
			}

			//如果含有关键字，则加入到新的酒店列表中
			if(isContain){
				newHotelList.add(vo);
				isContain = false;
			}
		}

		return newHotelList;
	}

	/**
	 * 根据SearchCondition中的房间价格对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByRoomPrice(double minPrice, double maxPrice, ArrayList<HotelVO> hotelList){
		ArrayList<HotelVO> newHotelList = new ArrayList();

		for(HotelVO vo : hotelList){
			ArrayList<RoomVO> roomList = vo.getRoomList();
//			roomList
		}

		return newHotelList;
	}

	/**
	 * 根据SearchCondition中的房间信息对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByRoomInfo(RoomType roomType, int freeRoomNum, ArrayList<HotelVO> hotelList){
		return null;
	}

	/**
	 * 根据SearchCondition中的酒店星级区间对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByStarLevel(int minStarLevel, int maxStarLevel, ArrayList<HotelVO> hotelList){
		ArrayList<HotelVO> newHotelList = new ArrayList();

		for(HotelVO vo : hotelList){
			if( (vo.getRank() >= minStarLevel) && (vo.getRank() <= maxStarLevel) ){
				newHotelList.add(vo);
			}
		}

		return newHotelList;
	}

	/**
	 * 根据SearchCondition中的酒店评分区间对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByScore(double minScore, double maxScore, ArrayList<HotelVO> hotelList){
		ArrayList<HotelVO> newHotelList = new ArrayList();

		for(HotelVO vo : hotelList){
			if( (vo.getScore() >= minScore) && (vo.getScore() <= maxScore) ){
				newHotelList.add(vo);
			}
		}

		return newHotelList;
	}

	/**
	 * 根据SearchCondition中的入住时间信息对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByTime(Date checkInTime, Date checkOutTime, ArrayList<HotelVO> hotelList){
		return null;
	}
}
