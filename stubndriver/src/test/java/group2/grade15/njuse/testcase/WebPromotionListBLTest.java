package testcase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bl.promotion.Promotion;
import bl.webpromotionbl.WebPromotion;
import bl.webpromotionbl.WebPromotionBL;
import utility.PromotionType;
import vo.WebPromotionListVO;

public class WebPromotionListBLTest {
	WebPromotionBL wpList;
	
	@Before
	public void setUp() throws Exception {
		wpList=new WebPromotion();
		
	}

	@Test
	public void test() {
		WebPromotionListVO listVO=wpList.getWebPromotionList();
		Promotion promotion=listVO.getWebPromotion().get(0);
		assertEquals(promotion.getId(),111111111);
		assertEquals(promotion.getType(),PromotionType.areaPromotion);
	}

}
