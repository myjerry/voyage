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
import org.myjerry.voyage.model.Page;
import org.myjerry.voyage.model.Project;
import org.myjerry.voyage.model.Template;
import org.myjerry.voyage.service.DeveloperService;
import org.myjerry.voyage.service.PageService;
import org.myjerry.voyage.service.ProjectService;
import org.myjerry.voyage.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ViewPageController extends MultiActionController {
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private DeveloperService developerService;
	
	public void view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Template template = null;
		Project project = null;
		
		Long pageID = StringUtils.getLong(request.getParameter("pageID"));
		
		Page page = this.pageService.getPage(pageID);
		if(page.getProjectID() != null) {
			project = this.projectService.getProject(page.getProjectID());
			template = this.templateService.getTemplate(project.getTemplateID());
		} else {
			template = this.templateService.getDefaultTemplate();
		}
		
		// build up the model
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pageBody", page.getContents());
		model.put("project", project);
		model.put("projects", this.projectService.getProjects());
		model.put("developers", this.developerService.getDevelopers());
		
		String templateContents = template.getContents();
		String generatedPage = null;
		if(StringUtils.isNotEmpty(templateContents)) {
			// generate the page
			StringWriter result = new StringWriter();
			this.velocityEngine.evaluate(new VelocityContext(model), result, "log string", templateContents);
			generatedPage = result.toString();
		} else {
			generatedPage = page.getContents();
		}
		
		// launch the page
		response.setContentType("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter writer = response.getWriter();
		writer.write(generatedPage);
		writer.close();
		response.flushBuffer();
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
	 * @return the pageService
	 */
	public PageService getPageService() {
		return pageService;
	}

	/**
	 * @param pageService the pageService to set
	 */
	public void setPageService(PageService pageService) {
		this.pageService = pageService;
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
