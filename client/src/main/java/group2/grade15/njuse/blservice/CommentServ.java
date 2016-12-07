package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentListVO;
import group2.grade15.njuse.vo.CommentVO;

public interface CommentServ {
    /**
     * 创建评价
     */
    public ResultMessage createComment(CommentVO CommentInfo);

    /**
     * 修改评价
     */
    public ResultMessage modifyComment(CommentVO ModifyInfo);

    /**
     * 通过评价ID获取单个评价
     */
    public CommentVO getComment(int commentID, int customerID);

    /**
     * 根据酒店的ID获取酒店评价列表
     */
    public CommentListVO getHotelCommentList(int hotelId);
}
