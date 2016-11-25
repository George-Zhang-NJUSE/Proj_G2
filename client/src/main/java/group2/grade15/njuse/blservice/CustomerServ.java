package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

public interface CustomerServ {

	public CustomerVO addCustomer(CustomerVO newCustomerVO);
	
	public CustomerVO getInfo(int customerID) ;

	public ResultMessage modifyInfo(CustomerVO vo);

}
