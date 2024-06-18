package pack.order;

import java.util.Hashtable;

public class CartMgr {
	private Hashtable<String, OrderBean> hCart = new Hashtable<String, OrderBean>();
	
	public void addCart(OrderBean obean) { //카트 담기 
		String product_no = obean.getProduct_no(); // 꺼내봐야지
		int quantity = Integer.parseInt(obean.getQuantity());
		
		if(quantity > 0) {
			// 동일 상품 주문인 경우에는 주문 수량만 누적되게 해야되(containsKey)
			if(hCart.containsKey(product_no)) { // 이미 1회에 이상 주문된 상품인 경우
				// 누적을 위해서 꺼내야되
				OrderBean temp = hCart.get(product_no);
				quantity += Integer.parseInt(temp.getQuantity()); //누적
				temp.setQuantity(Integer.toString(quantity));
				hCart.put(product_no, temp);
			}else {
				hCart.put(product_no, obean); 
				// 카트에 아무 물건도 안 담가져 있을 때 담기는 최초의 상품 (상품의 종류가 달라짐)
				
			}
		}
		
		
	}
	
	//카트 읽기 ( 카드 내용 읽기)
	public Hashtable<String, OrderBean> getCartList(){
		return hCart;
	}
	//장바구니 내용 수정 ( 주문수량 수정)
	public void updateCart(OrderBean obean) {
		String product_no = obean.getProduct_no();
		hCart.put(product_no, obean); // 넣을 때에는 put
		
	}
	//장바구니 내용 삭제
	public void deleteCart(OrderBean obean) {
		String product_no = obean.getProduct_no();
		hCart.remove(product_no); // 지울 때에는 remove
		// 카트는 데이터에 들어가 있지 않고 Lam으로 저장
	}
}
