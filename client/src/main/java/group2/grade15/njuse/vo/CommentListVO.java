package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class CommentListVO implements Serializable {
    private ArrayList<CommentVO> list;

    public CommentListVO(ArrayList<CommentVO> list) {
        this.list = list;
    }

    public ArrayList<CommentVO> getList() {
        return list;
    }

}
