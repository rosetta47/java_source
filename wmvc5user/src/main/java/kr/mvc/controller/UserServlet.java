package kr.mvc.controller; 

import java.io.IOException; 
import java.util.StringTokenizer; 

import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

@WebServlet("*.m2") // 확장자가 .m2인 요청을 처리하는 서블릿 설정
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // 직렬화 버전 UID 설정
    // UserServlet의 멤버 필드 선언
    private ModelAndView modelAndView = null; // ModelAndView 객체 선언
    private Controller controller = null; // Controller 객체 선언
    
    // 클라이언트의 요청을 처리하는 service 메소드
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 요청 데이터의 인코딩을 UTF-8로 설정, 한글 깨짐 방지

        try {
            // 요청 URI를 가져옴
            String ss = request.getRequestURI();
            int idx = ss.lastIndexOf('/'); // URI에서 마지막 '/'의 인덱스를 찾음
            // '/' 이후의 문자열을 가져와 '.'을 기준으로 분리
            StringTokenizer st = new StringTokenizer(ss.substring(idx + 1), ".");
            ss = st.nextToken(); // 분리된 문자열의 첫 번째 토큰을 가져옴
            // System.out.println("ss : " + ss); // 디버깅용 출력: ss : login, ss : list, ss : view 등
            String command = ss; // 커맨드 문자열 설정
            
            controller = getController(command); // 커맨드에 해당하는 컨트롤러 객체를 가져옴
            modelAndView = controller.execute(request, response); // 컨트롤러의 execute 메소드를 호출하여 ModelAndView 객체를 가져옴
            
            // 응답 방식 선택
            if(modelAndView.isRedirect()) { // 리다이렉트 여부 확인
                response.sendRedirect(modelAndView.getViewName()); // 리다이렉트 방식으로 응답
            } else {
                // 포워드 방식으로 응답
                RequestDispatcher dispatcher = 
                        request.getRequestDispatcher("/WEB-INF/views/" + modelAndView.getViewName());
                dispatcher.forward(request, response); // 지정된 뷰로 포워드
            }
            
        } catch (Exception e) {
            System.out.println("service err:" + e); // 예외 발생 시 에러 메시지 출력
        }
    }
    
    // 커맨드 문자열에 해당하는 컨트롤러 객체를 반환하는 메소드
    public Controller getController(String command) throws Exception {
        if(command.equals("login")) { // 커맨드가 "login"인 경우
            controller = new LoginController(); // LoginController 객체 생성
        } else if(command.equals("list")) { // 커맨드가 "list"인 경우
            controller = new ListController(); // ListController 객체 생성
        } else if(command.equals("insert")) { // 커맨드가 "insert"인 경우
            controller = new InsertController(); // InsertController 객체 생성
        } else if(command.equals("view")) { // 커맨드가 "view"인 경우
            controller = new ViewController(); // ViewController 객체 생성
        } else if(command.equals("logout")) { // 커맨드가 "logout"인 경우
            controller = new LogoutController(); // LogoutController 객체 생성
        } else if(command.equals("updateform")) { // 커맨드가 "updateform"인 경우
            controller = new UpdateFormController(); // UpdateFormController 객체 생성
        } else if(command.equals("update")) { // 커맨드가 "update"인 경우
            controller = new UpdateController(); // UpdateController 객체 생성
        } else if(command.equals("delete")) { // 커맨드가 "delete"인 경우
            controller = new DeleteController(); // DeleteController 객체 생성
        }
        
        return controller; // 생성된 컨트롤러 객체 반환
    }
}
