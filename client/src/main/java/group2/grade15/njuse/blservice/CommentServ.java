package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentListVO;
import group2.grade15.njuse.vo.CommentVO;

public interface CommentServ {
	/**
	 * 创建评价
	 */
	public ResultMessage createComment (CommentVO CommentInfo);

	/**
	 * 修改评价
	 */
	public ResultMessage modifyComment (CommentVO ModifyInfo);

	/**
	 * 根据酒店的ID获取酒店评价列表
	 */
	public CommentListVO getHotelCommentList (int hotelId);

	/**
	 * 根据顾客的ID获取顾客的评价列表
	 */
	public CommentListVO getCustomerCommentList (int customerId); 
}
