package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CommentPO;

import java.io.Serializable;
import java.sql.Date;

public class CommentVO implements Serializable{
	private int hotelID;
	private int userID;
	private String comment;
	private int commentID;
	private Date time;
	private double score;
	
	public CommentVO(CommentPO po){
		hotelID = po.getHotelID();
		userID = po.getUserID();
		comment = po.getComment();
		commentID = po.getCommentID();
		time = po.getTime();
		score = po.getScore();
	}

    public int getHotelID() {
        return hotelID;
    }

    public int getUserID() {
        return userID;
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

    public CommentPO toPO(){
        return new CommentPO(hotelID, userID, time, comment,commentID, score);
    }
}
