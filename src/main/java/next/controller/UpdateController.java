package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class UpdateController extends AbstractController {
	
	
		
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QuestionDao questionDao = new QuestionDao();
		Long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");	
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String title = ServletRequestUtils.getRequiredStringParameter(request, "title");		
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		
		Question question = questionDao.findById(questionId);
		question.setWriter(writer);
		question.setTitle(title);
		question.setContents(contents);
		questionDao.update(question);
		
		ModelAndView mav = jstlView("redirect:/list.next");
		return mav;
	}
}
