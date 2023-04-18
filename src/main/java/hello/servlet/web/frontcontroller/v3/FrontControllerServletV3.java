package hello.servlet.web.frontcontroller.v3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

	// 매핑 정보
	private Map<String, ControllerV3> controllerMap = new HashMap<>();

	public FrontControllerServletV3() {
		controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
		controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
	}

	@Override
	protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); // [/front-controller/v1/members/new-form]
		ControllerV3 controller = controllerMap.get(requestURI); // 1.매핑 정보에서 컨트롤러 조회
		if (controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		Map<String, String> paramMap = createParamMap(request);

		ModelView mv = controller.process(paramMap);

		String viewName = mv.getViewName();
		MyView view = viewResolver(viewName);

		view.render(mv.getModel(), request, response);
	}

	private static Map<String, String> createParamMap(final HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames()
			.asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		paramMap.forEach((key, value) -> System.out.println(key + value + "paramMap result"));
		return paramMap;
	}

	private MyView viewResolver(final String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
}
