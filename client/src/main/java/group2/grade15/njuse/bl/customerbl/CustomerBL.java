package bl.customerbl;

import utility.ResultMessage;
import vo.CustomerVO;

/**
 * Created by George on 2016/11/6.
 */
public interface CustomerBL {

    public CustomerVO getInfo(int customerId);

    public ResultMessage modifyInfo(CustomerVO vo);

}
