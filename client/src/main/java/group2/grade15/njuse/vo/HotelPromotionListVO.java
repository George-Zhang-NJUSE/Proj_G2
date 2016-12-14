package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelPromotionListVO implements Serializable {
    private ArrayList<HotelPromotionVO> promotionList;

    public HotelPromotionListVO(ArrayList<HotelPromotionVO> promotionList) {
        this.promotionList = promotionList;
    }

    public ArrayList<HotelPromotionVO> getHotelPromotionList() {
        return promotionList;
    }
}
