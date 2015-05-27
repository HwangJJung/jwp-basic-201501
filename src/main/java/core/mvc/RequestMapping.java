package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.AnswerCreateController;
import next.controller.AnswerDeleteController;
import next.controller.CreateController;
import next.controller.DeleteController;
import next.controller.DeleteJsonController;
import next.controller.FormController;
import next.controller.ListController;
import next.controller.ListJsonController;
import next.controller.ShowController;
import next.controller.UpdateController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		
		
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/edit.next", new FormController());
		mappings.put("/form.next", new FormController());
		mappings.put("/save.next", new CreateController());
		mappings.put("/update.next", new UpdateController());
		mappings.put("/delete.next", new DeleteController());
		
		
		
		mappings.put("/api/delete.next", new DeleteJsonController());
		mappings.put("/api/addanswer.next", new AnswerCreateController());
		mappings.put("/api/deleteAnswer.next", new AnswerDeleteController());
		mappings.put("/api/list.next", new ListJsonController());
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
