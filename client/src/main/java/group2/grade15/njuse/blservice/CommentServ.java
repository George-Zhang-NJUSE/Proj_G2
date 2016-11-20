package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentListVO;
import group2.grade15.njuse.vo.CommentVO;

public interface CommentServ {
	public ResultMessage createComment (CommentVO CommentInfo); 
	
	public ResultMessage modifyComment (CommentVO ModifyInfo); 

	public CommentListVO getHotelCommentList (int hotelId); 

	public CommentListVO getCustomerCommentList (int customerId); 
}
