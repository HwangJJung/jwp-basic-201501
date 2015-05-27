package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.AnswerController;
import next.controller.CreateController;
import next.controller.DeleteController;
import next.controller.EditController;
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
		mappings.put("/edit.next", new EditController());
		mappings.put("/form.next", new FormController());
		mappings.put("/save.next", new CreateController());
		mappings.put("/update.next", new UpdateController());
		mappings.put("/api/addanswer.next", new AnswerController());
		mappings.put("/api/deleteAnswer.next", new DeleteController());
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
