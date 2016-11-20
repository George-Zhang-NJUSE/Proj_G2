package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
abstract public class Hotel implements HotelServ{
    private int id;
    private String name;
    private String address;
    private String contact;
    private String introduction;
    private ArrayList<String> facility;
    private ArrayList<RoomPO> roomList;
    private int rank;
    private int score;
    private ArrayList<CustomerPO> vipList;


    abstract public ResultMessage modifyInfo (HotelVO hotel);

    abstract public HotelVO getInfo (int hotelID);

    abstract public ResultMessage modifyRoomInfo (RoomVO roomInfo);

    abstract public ResultMessage addCompany(int customerID);

    abstract public ResultMessage deleteCompany(int customerID);

}
