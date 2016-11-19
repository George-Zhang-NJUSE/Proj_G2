package group2.grade15.njuse.bl.commentbl.mock;

import java.util.Date;

import group2.grade15.njuse.bl.commentbl.CommentBL;
import group2.grade15.njuse.data.commentdata.CommentData;
import group2.grade15.njuse.dataservice.CommentDataService;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.CommentListVO;
import group2.grade15.njuse.vo.CommentVO;

public class MockCommentBL implements CommentBL {
	
	@Override
	public ResultMessage createComment(CommentVO CommentInfo) {
        CommentDataService commentData = new CommentData();
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modifyComment(CommentVO ModifyInfo) {
	    CommentDataService commentData = new CommentData();
		return ResultMessage.SUCCESS;
	}

	@Override
	public CommentListVO getHotelCommentList(int hotelId) {
		CommentDataService commentData = new CommentData();
		CommentListVO list = new CommentListVO(null);
		return list;
	}

	@Override
	public CommentListVO getCustomerCommentList(int customerId) {
		CommentDataService commentData = new CommentData();
		CommentListVO list = new CommentListVO(null);
		return list;
	}

}
