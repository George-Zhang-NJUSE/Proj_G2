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
	public ArrayList<HotelVO> getHotelBySearch(SearchConditionVO searchCondition) {
		return null;
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
		return null;
	}

	@Override
	public ArrayList<DistrictVO> getDistrict(String cityNum) {
		return null;
	}

	@Override
	public ArrayList<CbdVO> getCbd(String districtNum) {
		return null;
	}

	@Override
	public ArrayList<HotelVO> getHotel(String address) {
		return null;
	}

	/**
	 * 根据SearchCondition中的isBooked对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByBooked(){
		return null;
	}

	/**
	 * 根据SearchCondition中的排序选择进行排序
	 */
	private ArrayList<HotelVO> sort(SortMethod sortBy){
		return null;
	}

	/**
	 * 根据SearchCondition中的酒店名称对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByName(String name){
		return null;
	}

	/**
	 * 根据SearchCondition中的房间信息对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByRoomInfo(RoomType roomType, double minPrice, double maxPrice, int freeRoomNum){
		return null;
	}

	/**
	 * 根据SearchCondition中的酒店信息对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByHotelInfo(int starLevel, double minScore, double maxScore){
		return null;
	}

	/**
	 * 根据SearchCondition中的入住时间信息对获得的酒店列表进行一次筛选
	 */
	private ArrayList<HotelVO> filterByTime(Date checkInTime, Date checkOutTime){
		return null;
	}
}
