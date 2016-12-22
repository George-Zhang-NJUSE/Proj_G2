package group2.grade15.njuse.bl.promotionfactory;

/**
 * 采用工厂模式+反射机制
 * 需要生成对应的优惠策略的实例化对象时，只需要传入promotionType，接着就会由反射机制实例化对象
 * 可以通过这个方法避免根据type而需要进行switch判断
 * 同时，该方法保证了优惠策略的可修改性
 * 当增加新的优惠策略时，只需要创建一个实现了对应PromotionBL接口的新类即可，不需要对工厂类进行任何修改
 */
public class HotelPromotionFactory{

    //利用反射生成具体的酒店优惠策略
    public static HotelPromotionBL getHotelPromotion(String promotionType){
        HotelPromotionBL hotelPromotion;
        try {
            hotelPromotion = (HotelPromotionBL) Class.forName("group2.grade15.njuse.bl.hotelpromotionbl." + promotionType + "Promotion").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            hotelPromotion = null;
        }
        return hotelPromotion;
    }
}
