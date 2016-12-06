package group2.grade15.njuse.bl.promotionfactory;


import group2.grade15.njuse.bl.hotelpromotionbl.BirthdayHotelPromotion;
import group2.grade15.njuse.bl.hotelpromotionbl.MultipleHotelPromotion;
import group2.grade15.njuse.bl.hotelpromotionbl.PartnerHotelPromotion;
import group2.grade15.njuse.bl.hotelpromotionbl.TimeHotelPromotion;
import group2.grade15.njuse.bl.webpromotionbl.AreaWebPromotion;
import group2.grade15.njuse.bl.webpromotionbl.LevelWebPromotion;
import group2.grade15.njuse.bl.webpromotionbl.TimeWebPromotion;


/**
 * Created by Guo on 2016/11/30.
 */
public class PromotionFactory {
    static PromotionFactory promotionFactory = null;

    private BirthdayHotelPromotion birthdayHotelPromotion = null;
    private MultipleHotelPromotion multipleHotelPromotion = null;
    private PartnerHotelPromotion partnerHotelPromotion = null;
    private TimeHotelPromotion timeHotelPromotion = null;

    private AreaWebPromotion areaWebPromotion = null;
    private LevelWebPromotion levelWebPromotion = null;
    private TimeWebPromotion timeWebPromotion = null;

    private PromotionFactory(){}

    public PromotionFactory getInstance(){
        if(promotionFactory == null){
            promotionFactory = new PromotionFactory();
        }
        return promotionFactory;
    }

    public BirthdayHotelPromotion createBirthdayHotelPromotion(){
        if(birthdayHotelPromotion == null){
            birthdayHotelPromotion = new BirthdayHotelPromotion();
        }
        return birthdayHotelPromotion;
    }

    public MultipleHotelPromotion createMultipleHotelPromotion(){
        if(multipleHotelPromotion == null){
            multipleHotelPromotion = new MultipleHotelPromotion();
        }
        return multipleHotelPromotion;
    }

    public PartnerHotelPromotion createPartnerHotelPromotion(){
        if(partnerHotelPromotion == null){
            partnerHotelPromotion = new PartnerHotelPromotion();
        }
        return partnerHotelPromotion;
    }

    public TimeHotelPromotion createTimeHotelPromotion(){
        if(timeHotelPromotion == null){
            timeHotelPromotion = new TimeHotelPromotion();
        }
        return timeHotelPromotion;
    }


    public AreaWebPromotion createAreaWebPromotion(){
        if(areaWebPromotion == null){
            areaWebPromotion = new AreaWebPromotion();
        }
        return areaWebPromotion;
    }

    public LevelWebPromotion createLevelWebPromotion(){
        if(levelWebPromotion == null){
            levelWebPromotion = new LevelWebPromotion();
        }
        return levelWebPromotion;
    }

    public TimeWebPromotion createTimeWebPromotion(){
        if(timeWebPromotion == null){
            timeWebPromotion = new TimeWebPromotion();
        }
        return timeWebPromotion;
    }
}
