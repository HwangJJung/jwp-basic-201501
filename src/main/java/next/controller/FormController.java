package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class FormController extends AbstractController {	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = jstlView("form.jsp");
		mav.addObject("url", "/save.next");
		return mav;
	}
}
