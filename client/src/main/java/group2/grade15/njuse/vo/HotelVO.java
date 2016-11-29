package group2.grade15.njuse.vo;

import group2.grade15.njuse.bl.customerbl.Customer;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;
import java.util.ArrayList;

import static group2.grade15.njuse.utility.IDType.hotel;

public class HotelVO implements Serializable{
	private int id;
	private String name;
	private String address;
	private String contact;
	private String introduction;
	private String facility;
	private ArrayList<RoomVO> roomList;
	private ArrayList<CustomerVO> vipList;
	private int rank;
	private double score;
    private Byte[][] picture;
	
	public HotelVO(HotelPO hotel){
		id = hotel.getId();
		name = hotel.getName();
		address = hotel.getAddress();
		contact = hotel.getContact();
		introduction = hotel.getIntroduction();
		facility = hotel.getFacility();
		rank = hotel.getRank();
		score = hotel.getScore();
        picture = hotel.getPicture();

        roomList = new ArrayList();
        ArrayList<RoomPO> roomPOList = hotel.getRoomList();
        for(RoomPO po : roomPOList) {
            RoomVO room = new RoomVO(po);
            roomList.add(room);
        }

        vipList = new ArrayList();
		ArrayList<CustomerPO> vipPOList = hotel.getVipList();
        for(CustomerPO po : vipPOList){
            CustomerVO customer = new CustomerVO(po);
            vipList.add(customer);
        }
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getContact() {
		return contact;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String  getFacility() {
		return facility;
	}

	public ArrayList<RoomVO> getRoomList() {
		return roomList;
	}

	public ArrayList<CustomerVO> getVipList() {
		return vipList;
	}

	public int getRank() {
		return rank;
	}

	public double getScore() {
		return score;
	}

	public Byte[][] getPicture(){
        return picture;
    }

	public HotelPO toPO(){
        ArrayList<RoomPO> roomPOList = new ArrayList();
        for(RoomVO vo : roomList){
            roomPOList.add(vo.toPO());
        }

        ArrayList<CustomerPO> vipPOList = new ArrayList();
        for(CustomerVO vo : vipList){
            vipPOList.add(vo.toPO());
        }

        return new HotelPO(id, name, address, contact, introduction, facility, roomPOList, rank, score, vipPOList, picture);
    }
}
