package next.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteJsonController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeleteController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AnswerDao answerDao = new AnswerDao();	
		QuestionDao questionDao = new QuestionDao();
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		logger.debug("questionId : {}", questionId);
		Question question = questionDao.findById(questionId);
		
		ModelAndView mav = jsonView();
		Result result;
		
		int countOfComment = question.getCountOfComment();
		
		// 댓글이 하나도 없는 경우
		if ( countOfComment == 0) {
			questionDao.delete(question);
			result = new Result("삭제완료" , "댓글이 하나도 없음");			 
		} else{
			logger.info("댓글이 있습니다. {} 개가 있어요.", countOfComment);
			List<Answer> answers;
			answers = answerDao.findAllByQuestionId(questionId);
			String QuestionWriter = question.getWriter();
			for (Answer an : answers) {
				// 댓글 하나라도 작성자와 다른 사람일 경우
				if(!QuestionWriter.equals(an.getWriter())) {
					result = new Result("삭제실패" , "다른 사람이 댓글을 달았다!");
					mav.addObject("Result", result);
					return mav;
				}
			}		
			// 모든 댓글의 작성자가 글의 작성자와 같은 경우
			logger.info("모든 댓글의 작성자가 같습니다. 글을 삭제합니다.");
			for(Answer an: answers) {
				answerDao.deleteById(an.getAnswerId());
			}
			questionDao.delete(question);
			result = new Result("삭제성공" , "모든 댓글을 스스로 달았습니다.");
		}
		
		mav.addObject("Result", result);
		return mav;
	}	
}
