package group2.grade15.njuse.bl.customerbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

/**
 * Created by George on 2016/11/6.
 */
public interface CustomerBL {

    public CustomerVO getInfo(int customerId);

    public ResultMessage modifyInfo(CustomerVO vo);

}
