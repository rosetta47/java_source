package pack.order;

import java.util.Hashtable;

public class CartMgr {
	private Hashtable<String, OrderBean> hCart = new Hashtable<String, OrderBean>();
	
	public void addCart(OrderBean obean) { //카트 담기
		
	}
	
	//카트 읽기 ( 카드 내용 읽기)
	public Hashtable<String, OrderBean> getCartList(){
		return hCart;
	}
	//장바구니 내용 수정
	public void updateCart(OrderBean obean) {
		
	}
	//장바구니 내용 삭제
	public void deleteCart(OrderBean obean) {
		
	}
}
