package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class EditController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(EditController.class);
	
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QuestionDao questionDao = new QuestionDao();
		Question question;
		String uri = request.getRequestURI();
		ModelAndView mav = jstlView("form.jsp");
		logger.debug("uri : {}", uri);
		
		if ( uri.equals("/edit.next")) {
			long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
			logger.debug("questionId : {}", questionId);
			question = questionDao.findById(questionId);
			mav.addObject("question", question);
			mav.addObject("url", "/update.next");	
		}
		else{
			mav.addObject("url", "/save.next");
		}
		return mav;
	}
}
