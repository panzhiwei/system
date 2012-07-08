package org.springside.examples.miniweb.web.Utils;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springside.examples.miniweb.web.Utils.JsonResp.Status;

public class JsonView extends MappingJacksonJsonView {

	@SuppressWarnings("unchecked")
	protected Object filterModel(Map<String, Object> model) {
		Map<String, Object> map = (Map<String, Object>)super.filterModel(model);
		JsonResp resp = new JsonResp();
		resp.setStatusCode(JsonResp.Status.OK);
		return resp;
	}

}
