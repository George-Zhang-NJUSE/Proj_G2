package bl.webpromotionbl.mock;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.promotion.Promotion;
import bl.webpromotionbl.WebPromotionBL;
import data.webpromotiondata.WebPromotionData;
import po.WebPromotionListPO;
import vo.WebPromotionListVO;
import vo.WebPromotionVO;

public class MockWebPromotionBL implements WebPromotionBL{

	@Override
	public WebPromotionListVO getWebPromotionList(){
		// TODO Auto-generated method stub
		WebPromotionData wpData=new WebPromotionData();
		WebPromotionListPO wpList=new WebPromotionListPO(null);
		try{
			wpList=wpData.getList();
		}catch(RemoteException e){
			e.printStackTrace();
		}
		if(wpList==null){
			return null;
		}
		else{
			ArrayList<Promotion> list=wpList.getWebPromotion();
			WebPromotionListVO wpListVO=new WebPromotionListVO(list);
			return wpListVO;
		}
		
	}

}
