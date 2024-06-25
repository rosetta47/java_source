package pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestJstl")
public class TestJstl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String irum = "신기해";
		request.setAttribute("irum", irum);
		
		Person person = new Person();
		person.setName("한국인");
		request.setAttribute("person", person); //object을 담는 거임
		
		Student student = new Student();
		student.setAge(22);
		request.setAttribute("student", student);
		
		String[] ani = {"댕댕이", "냥이", "말"};
		request.setAttribute("animal", ani);
		
		String[] foods = {"당근", "시금치", "호박"};
		List<Object> list = new ArrayList<Object>();
		list.add(ani);
		list.add(foods);
		request.setAttribute("list", list);
		
		//response.sendRedirect("testjst1.jsp?irum=irum&person=person&list=list");
		// 이렇게 하면 irum하나만 넘어가 오류야
		
		request.getRequestDispatcher("testjst1.jsp").forward(request, response);
		// 순서 바꾸면 안되 jsp부르고 .forward해야 되
	}

}
