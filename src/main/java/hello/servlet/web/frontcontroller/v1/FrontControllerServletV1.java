package hello.servlet.web.frontcontroller.v1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

	// 매핑 정보
	private Map<String, ControllerV1> controllerMap = new HashMap<>();

	public FrontControllerServletV1() {
		controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
		controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
		controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
	}

	@Override
	protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); // [/front-controller/v1/members/new-form]
		ControllerV1 controller = controllerMap.get(requestURI); // 1.매핑 정보에서 컨트롤러 조회
		if (controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		controller.process(request, response); // 2. 컨트롤러 호출
	}
}
