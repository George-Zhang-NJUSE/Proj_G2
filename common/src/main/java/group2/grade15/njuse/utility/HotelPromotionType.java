package group2.grade15.njuse.utility;

/**
 * Created by dell on 2016/12/1.
 */
public enum HotelPromotionType {
    BirthdayHotel,                  //生日特惠折扣
    MultipleHotel,                  //三间及以上预订特惠
    PartnerHotel,                   //合作企业客户折扣
    TimeHotel;                      //双11活动折扣（在特定的期间住宿有折扣）
    public String toString(){
        switch (this) {
            case BirthdayHotel:
                return "生日特惠折扣";
            case MultipleHotel:
                return "三间及以上预订特惠";
            case PartnerHotel:
                return "合作企业客户折扣";
            case TimeHotel:
                return "特定时间入住折扣";
        }
        return"";
    }
}
