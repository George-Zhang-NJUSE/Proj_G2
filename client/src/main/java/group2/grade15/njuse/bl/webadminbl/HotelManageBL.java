package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;


/**
 * Created by Guo on 2016/11/27.
 */
public interface HotelManageBL {

    public ResultMessage createHotel(HotelVO hotel);

    public HotelListVO getHotelList();

    public HotelListVO modifyHotel(HotelVO hotel);

    public ResultMessage deleteHotel(HotelVO hotel);
}
