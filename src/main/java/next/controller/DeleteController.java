package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeleteController.class);
	private AnswerDao answerDao = new AnswerDao();	
	private QuestionDao questionDao = new QuestionDao();
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		long answerId = ServletRequestUtils.getRequiredLongParameter(request, "answerId");
		logger.debug("questionId : {},  answerId: {}", questionId,answerId);
		answerDao.deleteById(answerId);
		questionDao.decreaseCountOfComment(questionId);
		
		ModelAndView mav = jsonView();
		mav.addObject("status", "success");
		return mav;
	}
	
}
