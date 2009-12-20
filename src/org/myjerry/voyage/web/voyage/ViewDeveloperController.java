package org.myjerry.voyage.web.voyage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.myjerry.util.StringUtils;
import org.myjerry.voyage.model.Developer;
import org.myjerry.voyage.model.Template;
import org.myjerry.voyage.service.DeveloperService;
import org.myjerry.voyage.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ViewDeveloperController extends MultiActionController {

	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private TemplateService templateService;

	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long developerID = StringUtils.getLong(request.getParameter("developerID"));
		
		Developer developer = this.developerService.getDeveloper(developerID);
		Template template = this.templateService.getDefaultTemplate();
		
		// create the model
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("developer", developer);
		model.put("developers", this.developerService.getDevelopers());
		
		// generate the page
		StringWriter result = new StringWriter();
		this.velocityEngine.evaluate(new VelocityContext(model), result, "log string", template.getContents());
		String generatedPage = result.toString();
		
		// launch the page
		response.setContentType("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter writer = response.getWriter();
		writer.write(generatedPage);
		writer.close();
		response.flushBuffer();
		
		return null;
	}

	/**
	 * @return the velocityEngine
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	/**
	 * @param velocityEngine the velocityEngine to set
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * @return the templateService
	 */
	public TemplateService getTemplateService() {
		return templateService;
	}

	/**
	 * @param templateService the templateService to set
	 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	/**
	 * @return the developerService
	 */
	public DeveloperService getDeveloperService() {
		return developerService;
	}

	/**
	 * @param developerService the developerService to set
	 */
	public void setDeveloperService(DeveloperService developerService) {
		this.developerService = developerService;
	}
	
}
