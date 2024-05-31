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


@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		//세션을 만들면 알아서 쿠키를 양쪽 서버에다가 알아서 만든
		HttpSession session = request.getSession(true); //세션을 읽고있음
		
		ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list");//장바구니할때 이걸 많이써 arraylist
		// 냉장고에서 list를 꺼내서 glist를 줌(냉장고 안에서 밖으로 뺌)
		if(glist == null) glist = new ArrayList<Goods>(); // Goods 객체를 담을 glist 생성
		//glist 는 null이니까 glist 컬렉션을 만들어(냉장고 밖에서 일어나는 일)
		glist.add(new Goods(name, price));
		session.setAttribute("list", glist);// (session.setAttribute : 냉장고 안에 넣는 일)
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>★ "+ name + " 구입했습니다");
		out.println("<br>[<a href='myshop.html'>계속 쇼핑</a>] ");
		out.println("[<a href='Buy'>결제하기</a>]<br>");
		
		out.println("<p><table with='80%'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		for(int i =0; i<glist.size(); i++) {
			Goods goods = (Goods)glist.get(i);//상품 하나씩 빼는거
			out.println("<tr><td>" + goods.getName() + "</td>");
			out.println("<td>" + goods.getPrice() + "</td></tr>");
			
		}
		
		out.println("</table>");
		out.println("</body></html>");
		out.close();
	}

}
