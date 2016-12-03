package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.bl.hotelbl.GetHotelListBL;
import group2.grade15.njuse.bl.hotelbl.Hotel;
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

		if (searchCondition.getMinStarLevel() != 0 && hotelList != null){
			hotelList = filterByStarLevel(searchCondition.getMinStarLevel(), hotelList);
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
		GetHotelListBL getHotelListBL = new Hotel();
		HotelListVO hotelListVO = getHotelListBL.getBookedHotelList(customerID);
		ArrayList<HotelVO> bookedHotelList = hotelListVO.getList();
		ArrayList<HotelVO> newHotelList = new ArrayList();

		for(HotelVO hotel : hotelList){
			for(HotelVO bookedHotel : bookedHotelList){
				if(hotel.getId() == bookedHotel.getId()){
					newHotelList.add(hotel);
					break;
				}
			}
		}

		return newHotelList;
	}

	/**
	 * 根据SearchCondition中的排序选择进行排序(升序)
	 */
	private ArrayList<HotelVO> sort(SortMethod sortBy, ArrayList<HotelVO> hotelList){
		ArrayList<HotelVO> newHotelList = new ArrayList();

		switch (sortBy){
			case DEFAULT:
				newHotelList = hotelList;
				break;

			case PRICE:
				newHotelList = hotelList;
				for(int i = 1; i < newHotelList.size(); i++){
					for(int j = 0; j < i; j ++ ){

						//计算出第一个酒店的最低房价
						double minRoomPrice1 = 0;
						for(RoomVO room : newHotelList.get(i).getRoomList()){
							if(minRoomPrice1 > room.getPrice() || minRoomPrice1 == 0){
								minRoomPrice1 = room.getPrice();
							}
						}

						//计算出第二个酒店的最低房价
						double minRoomPrice2 = 0;
						for(RoomVO room : newHotelList.get(j).getRoomList()){
							if(minRoomPrice2 > room.getPrice() || minRoomPrice1 == 0){
								minRoomPrice2 = room.getPrice();
							}
						}

						if(minRoomPrice1 < minRoomPrice2){
							HotelVO temp = newHotelList.get(j);
							newHotelList.set(j, newHotelList.get(i));
							newHotelList.set(i, temp);
						}
					}
				}
				break;

			case STAR_LEVEL:
				newHotelList = hotelList;
				for(int i = 1; i < newHotelList.size(); i++){
					for(int j = 0; j < i; j ++ ){
						if(newHotelList.get(i).getRank() < newHotelList.get(j).getRank()){
							HotelVO temp = newHotelList.get(j);
							newHotelList.set(j, newHotelList.get(i));
							newHotelList.set(i, temp);
						}
					}
				}
				break;

			case SCORE:
				newHotelList = hotelList;
				for(int i = 1; i < newHotelList.size(); i++){
					for(int j = 0; j < i; j ++ ){
						if(newHotelList.get(i).getScore() < newHotelList.get(j).getScore()){
							HotelVO temp = newHotelList.get(j);
							newHotelList.set(j, newHotelList.get(i));
							newHotelList.set(i, temp);
						}
					}
				}
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

		for(HotelVO hotel : hotelList){
			//判定酒店的名字是否至少含有一个关键字
			for(String word : keyWords) {
				if(hotel.getName().contains(word)){
					isContain = true;
				}
			}

			//如果含有关键字，则加入到新的酒店列表中
			if(isContain){
				newHotelList.add(hotel);
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
		boolean isContain = false; //酒店是否含有该区间价位的房间的标志

		for(HotelVO hotel : hotelList){
			ArrayList<RoomVO> roomList = hotel.getRoomList();
			for(RoomVO room : roomList){
				if( room.getPrice() <= maxPrice && room.getPrice() >= minPrice ){
					isContain = true;
				}
			}

			if(isContain){
				newHotelList.add(hotel);
				isContain = false;
			}
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
	private ArrayList<HotelVO> filterByStarLevel(int minStarLevel, ArrayList<HotelVO> hotelList){
		ArrayList<HotelVO> newHotelList = new ArrayList();

		for(HotelVO hotelVO : hotelList){
			if( hotelVO.getRank() >= minStarLevel ){
				newHotelList.add(hotelVO);
			}
		}

		return newHotelList;
	}

	/**
	 * 根据SearchCondition中的酒店评分区间对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByScore(double minScore, double maxScore, ArrayList<HotelVO> hotelList){
		ArrayList<HotelVO> newHotelList = new ArrayList();

		for(HotelVO hotelVO : hotelList){
			if( (hotelVO.getScore() >= minScore) && (hotelVO.getScore() <= maxScore) ){
				newHotelList.add(hotelVO);
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
