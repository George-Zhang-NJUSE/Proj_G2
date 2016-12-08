package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentListVO;
import group2.grade15.njuse.vo.CommentVO;

/**
 * 酒店评论的层间接口
 * 供界面层层调用
 * 职责是处理酒店评论相关的逻辑功能
 * @author Guo
 */
public interface CommentServ {

    /**
     * 创建新的酒店评论
     * @param commentInfo CommentVO型，界面层传递来的存有评论信息的数据对象
     * @return 成功返回ResultMessage.success 失败返回ResultMessage.failed
     */
    public ResultMessage createComment(CommentVO commentInfo);

    /**
     * 修改已有的酒店评论
     * @param  modifyInfo commentVO型，界面层传递来的存有修改后的评论信息的数据对象
     * @return 成功返回ResultMessage.success 失败返回ResultMessage.failed
     */
    public ResultMessage modifyComment(CommentVO modifyInfo);

    /**
     * 根据评价ID和客户ID获取单个评价
     * @param  commentID int型，界面层传递来的评论ID
     * @param  customerID int型，界面层传递来的客户ID
     * @return 成功返回对应酒店的CommentListVO, 失败则返回null
     */
    public CommentVO getComment(int commentID, int customerID);


    /**
     * 根据酒店ID获取该酒店所有的评论列表
     * @param  hotelID int型，界面层传递来的酒店ID
     * @return 成功返回对应酒店的CommentListVO, 失败则返回null
     */
    public CommentListVO getHotelCommentList(int hotelID);
}
