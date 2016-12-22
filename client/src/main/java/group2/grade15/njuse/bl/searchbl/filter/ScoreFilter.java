package group2.grade15.njuse.bl.searchbl.filter;


import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

/**
 * 根据SearchCondition中的酒店评分区间对获得的酒店列表进行一次筛选
 */
public class ScoreFilter implements FilterBL{
    @Override
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition, ArrayList<HotelVO> hotelList) {
        double minScore = searchCondition.getMinScore();
        double maxScore = searchCondition.getMaxScore();

        //将酒店搜索的判定条件简化成一系列新的布尔变量
        boolean isListNotNull = (hotelList != null);
        boolean isFilterScore = (searchCondition.getMinScore() != 0
                || searchCondition.getMaxScore() != 10)
                && isListNotNull;

        //根据搜索条件对酒店列表进行对应的筛选
        if (isFilterScore) {
            ArrayList<HotelVO> newHotelList = new ArrayList();

            for (HotelVO hotelVO : hotelList) {
                boolean isFit = (hotelVO.getScore() >= minScore)
                        && (hotelVO.getScore() <= maxScore);
                if (isFit) {
                    newHotelList.add(hotelVO);
                }
            }

            return newHotelList;
        } else {
            return hotelList;
        }

    }
}
