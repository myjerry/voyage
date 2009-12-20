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
import org.myjerry.voyage.model.Project;
import org.myjerry.voyage.model.Template;
import org.myjerry.voyage.service.ProjectService;
import org.myjerry.voyage.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ViewProjectController extends MultiActionController {
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TemplateService templateService;

	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long projectID = StringUtils.getLong(request.getParameter("projectID"));
		
		Project project = this.projectService.getProject(projectID);
		Template template = this.templateService.getTemplate(project.getTemplateID());
		
		// create the model
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("project", project);
		model.put("projects", this.projectService.getProjects());
		
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
	 * @return the projectService
	 */
	public ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
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
	
}
