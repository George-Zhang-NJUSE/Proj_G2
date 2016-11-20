package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.ResultMessage;

public interface LoginControllerServ {
	public ResultMessage login(int id,String password, IDType type);
}
