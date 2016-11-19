package group2.grade15.njuse.bl.commentbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.CommentListVO;
import group2.grade15.njuse.vo.CommentVO;

import java.util.Date;

public class Comment {
	private String hotelName;
	private int userID;
	private Date checkInTime;
	private RoomType type;
	private String comment;
	private double score;
	
	public ResultMessage createComment(CommentVO CommentInfo){
		return null;
	}

	public ResultMessage modifyComment(CommentVO ModifyInfo){
		return null;
	}

	public CommentListVO getHotelCommentList (){
		return null;
	}
	
	public CommentListVO getCustomerCommentList (){
		return null;
	}
}
