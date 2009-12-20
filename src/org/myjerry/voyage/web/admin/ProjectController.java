package org.myjerry.voyage.web.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.util.StringUtils;
import org.myjerry.voyage.model.Project;
import org.myjerry.voyage.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ProjectController extends MultiActionController {
	
	@Autowired
	private ProjectService projectService;
	
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Collection<Project> projects = this.projectService.getProjects();
		mav.addObject("projects", projects);
		mav.addObject("totalProjects", projects.size());
		
		mav.setViewName(".admin.projects");
		return mav;
	}

	public ModelAndView viewAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("editMode", false);
		mav.setViewName(".admin.projects.add");
		return mav;
	}
	
	public ModelAndView editForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Long projectID = StringUtils.getLong(request.getParameter("projectID"));
		Project project = this.projectService.getProject(projectID);
		mav.addObject("project", project);
		mav.addObject("editMode", true);
		mav.setViewName(".admin.projects.add");
		return mav;
	}

	public ModelAndView removeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Long projectID = StringUtils.getLong(request.getParameter("projectID"));
		Project project = this.projectService.getProject(projectID);
		mav.addObject("project", project);
		
		mav.setViewName(".admin.projects.delete");
		return mav;
	}
	
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String urlAlias = request.getParameter("urlAlias");
		String shortDescription = request.getParameter("shortDescription");
		String repositoryPath = request.getParameter("repositoryPath");
		String documentationPath = request.getParameter("documentationPath");
		String downloadPath = request.getParameter("downloadPath");
		String issueTracker = request.getParameter("issueTracker");
		String description = request.getParameter("description");
		Long templateID = StringUtils.getLong(request.getParameter("templateID"));
		
		Project project = new Project();
		project.setName(name);
		project.setUrlAlias(urlAlias);
		project.setShortDescription(shortDescription);
		project.setRepositoryPath(repositoryPath);
		project.setDocumentationPath(documentationPath);
		project.setDownloadPath(downloadPath);
		project.setIssueTracker(issueTracker);
		project.setDescription(description);
		project.setTemplateID(templateID);
		this.projectService.addProject(project);
		
		return view(request, response);
	}
	
	public ModelAndView confirmDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Long projectID = StringUtils.getLong(request.getParameter("projectID"));
		boolean result = this.projectService.deleteProject(projectID);

		mav = view(request, response);
		if(result) {
			mav.addObject("operationResult", "Page successfully removed.");
		} else {
			mav.addObject("operationResult", "Unable to remove the page.");
		}
		
		return mav;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String urlAlias = request.getParameter("urlAlias");
		String shortDescription = request.getParameter("shortDescription");
		String repositoryPath = request.getParameter("repositoryPath");
		String documentationPath = request.getParameter("documentationPath");
		String downloadPath = request.getParameter("downloadPath");
		String issueTracker = request.getParameter("issueTracker");
		String description = request.getParameter("description");
		Long templateID = StringUtils.getLong(request.getParameter("templateID"));
		Long projectID = StringUtils.getLong(request.getParameter("projectID"));
		
		boolean result = false;
		
		if(projectID != null) {
			Project project = new Project();
			project.setProjectID(projectID);
			project.setName(name);
			project.setUrlAlias(urlAlias);
			project.setShortDescription(shortDescription);
			project.setRepositoryPath(repositoryPath);
			project.setDocumentationPath(documentationPath);
			project.setDownloadPath(downloadPath);
			project.setIssueTracker(issueTracker);
			project.setDescription(description);
			project.setTemplateID(templateID);
			
			result = this.projectService.updateProject(project);
		}
		
		ModelAndView mav = view(request, response);
		if(result) {
			mav.addObject("operationResult", "Developer successfully updated.");
		} else {
			mav.addObject("operationResult", "Unable to update the developer.");
		}
		
		return view(request, response);
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

}
