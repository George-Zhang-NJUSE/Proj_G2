package group2.grade15.njuse;

import group2.grade15.njuse.bl.commentbl.CommentController;
import group2.grade15.njuse.blservice.CommentServ;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentVO;
import org.junit.Before;
import org.junit.Test;


import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommentServTest {
    CommentServ comment;
    CommentVO commentVO;

    @Before
    public void setUp(){
        int hotelID = 0;
        int userID = 0;
        String commentInfo = "abc";
        int commentID = 0;
        Date time = null;
        double score = 1;
        int orderID = 0;
        commentVO = new CommentVO(hotelID, userID, commentInfo,  commentID, time, score, orderID);

        comment = new CommentController();
    }

    @Test
    public void testGetComment(){
        assertEquals(1, comment.getComment(1).getCommentID());
    }

    @Test
    public void testCreateComment(){
        assertEquals(ResultMessage.SUCCESS, comment.createComment(commentVO));
    }

    @Test
    public void testGetHotelCommentList(){
        ArrayList<CommentVO> commentList = null;
        assertEquals(commentList ,comment.getHotelCommentList(0).getList());
    }

    @Test
    public void testModifyComment() {
        assertEquals(ResultMessage.SUCCESS, comment.modifyComment(commentVO));
    }
}
