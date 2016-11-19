package blservice;

import utility.ResultMessage;
import vo.CustomerVO;

public interface CustomerServ {

	public ResultMessage addCustomer(CustomerVO newCustomerVO);
	
	public CustomerVO getInfo(int customerID) ;

	public ResultMessage modifyInfo(CustomerVO vo);

}
