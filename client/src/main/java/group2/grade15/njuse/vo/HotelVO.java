package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class HotelVO implements Serializable {
    private int id;
    private String name;
    private String address;         //商圈地址
    private String concreteAddress; //具体地址
    private String contact;
    private String introduction;
    private String facility;
    private ArrayList<RoomVO> roomList;
    private int rank;
    private double score;
    private byte[][] picture;

    public HotelVO(HotelPO hotel) {
        id = hotel.getId();
        name = hotel.getName();
        address = hotel.getAddress();
        concreteAddress = hotel.getConcreteAddress();
        contact = hotel.getContact();
        introduction = hotel.getIntroduction();
        facility = hotel.getFacility();
        rank = hotel.getRank();
        score = hotel.getScore();
        picture = hotel.getPicture();

        roomList = new ArrayList();
        ArrayList<RoomPO> roomPOList = hotel.getRoomList();
        if(roomPOList!=null) {
            for (RoomPO po : roomPOList) {
                RoomVO room = new RoomVO(po);
                roomList.add(room);
            }
        }
    }

    public HotelVO(int id, String name, String address, String concreteAddress, String contact, String introduction,
                   String facility, ArrayList<RoomVO> roomList, int rank, double score,
                   byte[][] picture) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.concreteAddress = concreteAddress;
        this.contact = contact;
        this.introduction = introduction;
        this.facility = facility;
        this.roomList = roomList;
        this.rank = rank;
        this.score = score;
        this.picture = picture;
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

    public String getFacility() {
        return facility;
    }

    public ArrayList<RoomVO> getRoomList() {
        return roomList;
    }

    public int getRank() {
        return rank;
    }

    public double getScore() {
        return score;
    }

    public byte[][] getPicture() {
        return picture;
    }

    public String getConcreteAddress() {
        return concreteAddress;
    }

    public HotelPO toPO() {
        ArrayList<RoomPO> roomPOList;
        if(roomList != null) {
            roomPOList = roomList.stream()
                    .map(RoomVO::toPO)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            roomPOList = null;
        }
        return new HotelPO(id, name, address, concreteAddress, contact, introduction, facility, roomPOList, rank, score, picture);
    }
}
