package blservice;

import utility.IDType;
import utility.ResultMessage;

public interface LoginControllerServ {
	public ResultMessage login(int customerId,String password, IDType type);
}
