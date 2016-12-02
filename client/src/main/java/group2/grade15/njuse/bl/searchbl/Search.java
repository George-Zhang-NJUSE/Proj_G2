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
	public ArrayList<HotelVO> getHotelBySearch(SearchConditionVO searchCondition, HotelListVO hotelListVO) {
		ArrayList<HotelVO> hotelList = hotelListVO.getList();

		if (searchCondition.getSortBy() != SortMethod.DEFAULT && hotelList != null) {
			hotelList = filterByBooked(hotelList);
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

		return hotelList;
	}

	@Override
	public ArrayList<ProvinceVO> getProvince() {
		ArrayList<ProvincePO> provincePOList = new ArrayList();
		ArrayList<ProvinceVO> promotionList = new ArrayList();

		try {
			provincePOList = RemoteHelper.getInstance().getAreaDataService().getProvince();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for(ProvincePO po : provincePOList){
			promotionList.add(new ProvinceVO(po));
		}

		return promotionList;
	}

	@Override
	public ArrayList<CityVO> getCity(String provinceNum) {
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

		return cityList;
	}

	@Override
	public ArrayList<DistrictVO> getDistrict(String cityNum) {
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
		return districtList;
	}

	@Override
	public ArrayList<CbdVO> getCbd(String districtNum) {
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
		return cbdList;
	}

	@Override
	public ArrayList<HotelVO> getHotel(String address) {
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
		return hotelList;
	}

	/**
	 * 根据SearchCondition中的isBooked对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByBooked(ArrayList<HotelVO> hotelList){
		return null;
	}

	/**
	 * 根据SearchCondition中的排序选择进行排序
	 */
	private ArrayList<HotelVO> sort(SortMethod sortBy, ArrayList<HotelVO> hotelList){
		return null;
	}

	/**
	 * 根据SearchCondition中的酒店名称对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByName(String name, ArrayList<HotelVO> hotelList){
		return null;
	}

	/**
	 * 根据SearchCondition中的房间价格对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByRoomPrice(double minPrice, double maxPrice, ArrayList<HotelVO> hotelList){
		return null;
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
		return null;
	}

	/**
	 * 根据SearchCondition中的酒店评分区间对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByScore(double minScore, double maxScore, ArrayList<HotelVO> hotelList){
		return null;
	}

	/**
	 * 根据SearchCondition中的入住时间信息对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByTime(Date checkInTime, Date checkOutTime, ArrayList<HotelVO> hotelList){
		return null;
	}
}
