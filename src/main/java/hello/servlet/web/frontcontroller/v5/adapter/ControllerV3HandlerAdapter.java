package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

	@Override
	public boolean supports(final Object handler) {
		return (handler instanceof ControllerV3);
	}

	@Override
	public ModelView handle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws ServletException, IOException {
		ControllerV3 controller = (ControllerV3)handler;
		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controller.process(paramMap);
		return mv;
	}

	private static Map<String, String> createParamMap(final HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames()
			.asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		paramMap.forEach((key, value) -> System.out.println(key + value + "paramMap result"));
		return paramMap;
	}
}
