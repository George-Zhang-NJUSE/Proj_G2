package bl.hotelbl.mock;

import bl.hotelbl.Hotel;
import po.CustomerPO;
import po.HotelPO;
import utility.ResultMessage;
import vo.HotelVO;
import vo.RoomVO;

import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class MockHotel extends Hotel {
    private int id;
    private String name;
    private String address;
    private String contact;
    private String introduction;
    private ArrayList<String> facility;
    private ArrayList<RoomVO> roomList;
    private int rank;
    private int score;
    private ArrayList<CustomerPO> vipList;

    public MockHotel (HotelPO po){
        this.id=po.getId();
        this.name=po.getName();
        this.address=po.getAddress();
        this.contact=po.getContact();
        this.introduction=po.getIntroduction();
        this.facility=po.getFacility();
        this.score=po.getScore();
        this.vipList=po.getVipList();
        //this.roomList=po.getRoomList();
        this.rank=rank;
    }

    public ResultMessage modifyInfo (HotelVO hotel){
        return ResultMessage.SUCCESS;
    }

    public HotelVO getInfo (int hotelID){
        return new HotelVO(name,address,contact,introduction,facility,roomList,rank,score);
    }

    public ResultMessage modifyRoomInfo (RoomVO roomInfo){
        return ResultMessage.SUCCESS;
    }

    public ResultMessage addCompany(int customerID){
        return ResultMessage.SUCCESS;
    }

    public ResultMessage deleteCompany(int customerID){
        return ResultMessage.SUCCESS;
    }
}
