package blservice;

import utility.ResultMessage;
import vo.CommentListVO;
import vo.CommentVO;

public interface CommentServ {
	public ResultMessage createComment (CommentVO CommentInfo); 
	
	public ResultMessage modifyComment (CommentVO ModifyInfo); 

	public CommentListVO getHotelCommentList (int hotelId); 

	public CommentListVO getCustomerCommentList (int customerId); 
}
