package hello.servlet.web.frontcontroller.v1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

public class MemberFormControllerV1 implements ControllerV1 {

	@Override
	public void process(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/views/new-form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);// 컨트롤러에서 뷰로 이동할 때 사용
		dispatcher.forward(request, response);
	}
}
