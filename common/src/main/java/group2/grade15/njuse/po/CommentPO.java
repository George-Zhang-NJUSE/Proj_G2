package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;
import java.sql.Date;

public class CommentPO implements Serializable{
	private int hotelID;
	private int userID;
	private String comment;
	private int commentID;
	private Date time;
	private double score;
	
	public CommentPO(int hotelID,int userID,Date commentTime,String comment,int commentID,double score){
		this.hotelID=hotelID;
		this.userID=userID;
		this.comment=comment;
		this.commentID=commentID;
		this.time=commentTime;
		this.score=score;
	}

	public int getHotelID() {
		return hotelID;
	}

	public int getCommentID() {
		return commentID;
	}

	public Date getTime() {
		return time;
	}

	public int getUserID() {
		return userID;
	}

	public String getComment() {
		return comment;
	}

	public double getScore() {
		return score;
	}
}
