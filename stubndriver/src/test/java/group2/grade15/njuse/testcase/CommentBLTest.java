package testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import bl.commentbl.CommentBL;
import bl.commentbl.mock.MockCommentBL;
import utility.ResultMessage;
import vo.CommentListVO;
import vo.CommentVO;

public class CommentBLTest {
	 @Test
	 public void testCreateComment() {
		 CommentBL comment = new MockCommentBL();
		 CommentVO commentInfo = new CommentVO(null, 0, null, null, null, 0);
	     assertEquals(ResultMessage.SUCCESS, comment.createComment(commentInfo));
	 }

	 @Test
	 public void testModifyComment() {
		 CommentBL comment = new MockCommentBL();
		 CommentVO modifyInfo = new CommentVO(null, 0, null, null, null, 0);
	     assertEquals(ResultMessage.SUCCESS, comment.modifyComment(modifyInfo));
	 }
	 
	 @Test
	 public void testGetHotelCommentList() {
		 int hotelId = 111111;
		 CommentBL comment = new MockCommentBL();
		 CommentListVO list = new CommentListVO(null);
	     assertEquals(list, comment.getHotelCommentList(hotelId));
	 }
	 
	 @Test
	 public void testGetCustomerCommentList() {
		 int customerId = 111111;
		 CommentBL comment = new MockCommentBL();
		 CommentListVO list = new CommentListVO(null);
	     assertEquals(list, comment.getCustomerCommentList(customerId));
	 }
}
