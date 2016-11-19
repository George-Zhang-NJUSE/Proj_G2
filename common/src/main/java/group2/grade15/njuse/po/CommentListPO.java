package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class CommentListPO implements Serializable{
	private ArrayList<CommentPO> list;
	
	public CommentListPO(ArrayList<CommentPO> list){
		this.list=list;
	}

	public ArrayList<CommentPO> getList() {
		return list;
	}

}
