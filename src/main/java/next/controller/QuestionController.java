package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class QuestionController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	private QuestionDao questionDao = new QuestionDao();
		
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String title = ServletRequestUtils.getRequiredStringParameter(request, "title");		
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		Question input = new Question(writer, title, contents);
		questionDao.insert(input);
		
		ModelAndView mav = jstlView("redirect:/list.next");
		return mav;
	}
}
