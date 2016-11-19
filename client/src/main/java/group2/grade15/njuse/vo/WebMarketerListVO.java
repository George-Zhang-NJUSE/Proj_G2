package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class WebMarketerListVO implements Serializable{
	private ArrayList<WebMarketerVO> list;
	
	public WebMarketerListVO(ArrayList<WebMarketerVO> list){
		this.list=list;
	}

	public ArrayList<WebMarketerVO> getList() {
		return list;
	}

}
