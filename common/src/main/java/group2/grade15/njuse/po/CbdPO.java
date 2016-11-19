package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class CbdPO implements Serializable{
	ArrayList<String> cbd;
	
	public CbdPO (ArrayList<String> cbd){
		this.cbd=cbd;
	}

	public ArrayList<String> getCbd() {
		return cbd;
	}

}
