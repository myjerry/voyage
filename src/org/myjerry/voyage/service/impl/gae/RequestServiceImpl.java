package org.myjerry.voyage.service.impl.gae;

import java.util.Collection;

import org.myjerry.util.StringUtils;
import org.myjerry.voyage.model.Developer;
import org.myjerry.voyage.model.Project;
import org.myjerry.voyage.service.DeveloperService;
import org.myjerry.voyage.service.PageService;
import org.myjerry.voyage.service.ProjectService;
import org.myjerry.voyage.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private DeveloperService developerService;

	@Override
	public String getRequestForward(String requestUri) {
		if(StringUtils.isNotEmpty(requestUri)) {

			// check if the URL exists in the database
			Long pageID = this.pageService.getPageForUri(requestUri);
			if(pageID != null) {
				return "/viewPage.voyage?pageID=" + pageID; 
			}
			
			// no the URL does not exist in database
			// check if this is a generic URL
			
			// check for project pages
			Collection<Project> projects = this.projectService.getProjects();
			if(projects != null) {
				for(Project project : projects) {
					String uri = "/" + project.getUrlAlias() + ".html";
					if(requestUri.equals(uri)) {
						return "/viewProjectHome.voyage?projectID=" + project.getProjectID();
					}
				}
			}
			
			// check for developer pages
			Collection<Developer> developers = this.developerService.getDevelopers();
			if(developers != null) {
				for(Developer developer : developers) {
					String uri = "/myfriends/" + developer.getAlias() + ".html";
					if(requestUri.equals(uri)) {
						return "/viewDeveloperHome.voyage?developerID=" + developer.getDeveloperID();
					}
				}
			}
		}
		
		return null;
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
