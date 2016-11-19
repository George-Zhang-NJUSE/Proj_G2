package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;
import java.util.Date;

public class CommentPO implements Serializable{
	private String hotelName;
	private int userID;
	private Date checkInTime;
	private RoomType type;
	private String comment;
	private double score;
	
	public CommentPO(String hotelName,int userID,Date checkInTime,RoomType type,String comment,double score){
		this.hotelName=hotelName;
		this.userID=userID;
		this.checkInTime=checkInTime;
		this.type=type;
		this.comment=comment;
		this.score=score;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getUserID() {
		return userID;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public RoomType getType() {
		return type;
	}

	public String getComment() {
		return comment;
	}

	public double getScore() {
		return score;
	}
}
