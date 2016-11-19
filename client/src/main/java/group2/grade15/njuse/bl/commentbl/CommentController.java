package group2.grade15.njuse.bl.commentbl;

import group2.grade15.njuse.blservice.CommentServ;
import group2.grade15.njuse.dataservice.CommentDataService;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentListVO;
import group2.grade15.njuse.vo.CommentVO;

public class CommentController implements CommentServ, CommentBL{
	CommentDataService commentData;
	
	@Override
	public ResultMessage createComment(CommentVO CommentInfo) {
		return null;
	}

	@Override
	public ResultMessage modifyComment(CommentVO ModifyInfo) {
		return null;
	}

	@Override
	public CommentListVO getHotelCommentList(int hotelId) {
		return null;
	}

	@Override
	public CommentListVO getCustomerCommentList(int customerId) {
		return null;
	}

}
