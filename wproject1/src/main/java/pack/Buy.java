package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니 가격 합을 구하고 출력 후 세션을 삭제
		// 클라이언트가 넘겨준 세션id를 이용해 세션 읽기는 하는 거임
		HttpSession session = request.getSession(false);
		if(session == null) return;
		
		ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list");
		if(glist == null) return;
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		out.println("<p><table with='80%'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		int total = 0;
		
		for(int i =0; i<glist.size(); i++) {
			Goods goods = (Goods)glist.get(i);//상품 하나씩 빼는거
			out.println("<tr><td>" + goods.getName() + "</td>");
			out.println("<td>" + goods.getPrice() + "</td></tr>");	
			total += goods.getPrice();
		}
		out.println("<tr><td colspan='2'>결제 총액:" + total +"</td></tr>"); 
		//실제적인 결제는 현실적으로 불가, 결제가 되었다고 가정하고 
		//session.invalidate(); //.invalidate(): 해당 고객의 모든 섹션 삭제
		session.removeAttribute("list"); // .removeAttribute(): 해당 고객의 특정 세션 삭제
		
		out.println("<br>고객님 감사합니다");
		out.println("<br>[<a href='myshop.html'>다시 새마음으로 쇼핑</a>] ");
		out.println("</table>");
		out.println("</body></html>");
		out.close();
	}

}
