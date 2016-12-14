package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CommentPO;

import java.io.Serializable;
import java.sql.Date;

public class CommentVO implements Serializable {
    private int hotelID;
    private int userID;
    private String comment;
    private int commentID;
    private Date time;
    private double score;
    private int orderID;

    public CommentVO(CommentPO po) {
        hotelID = po.getHotelID();
        userID = po.getUserID();
        comment = po.getComment();
        commentID = po.getCommentID();
        time = po.getTime();
        score = po.getScore();
        orderID = po.getOrderID();
    }

    public CommentVO(int hotelID, int userID, String comment, int commentID, Date time, double score, int orderID) {
        this.hotelID = hotelID;
        this.userID = userID;
        this.comment = comment;
        this.commentID = commentID;
        this.time = time;
        this.score = score;
        this.orderID = orderID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public int getUserID() {
        return userID;
    }

    public int getOrderID(){
        return orderID;
    }

    public String getComment() {
        return comment;
    }

    public int getCommentID() {
        return commentID;
    }

    public Date getTime() {
        return time;
    }

    public double getScore() {
        return score;
    }

    public CommentPO toPO() {
        return new CommentPO(hotelID, userID, time, comment, commentID, score, orderID);
    }
}
