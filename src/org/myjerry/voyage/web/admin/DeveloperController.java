package org.myjerry.voyage.web.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.util.StringUtils;
import org.myjerry.voyage.model.Developer;
import org.myjerry.voyage.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class DeveloperController extends MultiActionController {
	
	@Autowired
	private DeveloperService developerService;
	
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Collection<Developer> developers = this.developerService.getDevelopers();
		mav.addObject("developers", developers);
		mav.addObject("totalDevelopers", developers.size());
		
		mav.setViewName(".admin.developers");
		return mav;
	}

	public ModelAndView viewAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(".admin.developers.add");
		mav.addObject("editMode", false);
		return mav;
	}
	
	public ModelAndView editForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("dev id=" + request.getParameter("developerID"));
		
		Long developerID = StringUtils.getLong(request.getParameter("developerID"));
		Developer developer = this.developerService.getDeveloper(developerID);
		mav.addObject("developer", developer);
		mav.addObject("editMode", true);
		mav.setViewName(".admin.developers.add");
		return mav;
	}

	public ModelAndView removeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Long developerID = StringUtils.getLong(request.getParameter("developerID"));
		Developer developer = this.developerService.getDeveloper(developerID);
		mav.addObject("developer", developer);

		mav.setViewName(".admin.developers.delete");
		return mav;
	}
	
	public ModelAndView confirmDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long developerID = StringUtils.getLong(request.getParameter("developerID"));
		boolean result = this.developerService.deleteDeveloper(developerID);

		ModelAndView mav = view(request, response);
		if(result) {
			mav.addObject("operationResult", "Developer successfully removed.");
		} else {
			mav.addObject("operationResult", "Unable to remove the developer.");
		}
		
		return mav;
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String alias = request.getParameter("alias");
		String homepageUrl = request.getParameter("homepageUrl");
		String emailAddress = request.getParameter("emailAddress");
		String description = request.getParameter("description");
		String tagLine = request.getParameter("tagLine");
		
		Developer developer = new Developer();
		developer.setName(name);
		developer.setAlias(alias);
		developer.setHomepageUrl(homepageUrl);
		developer.setEmailAddress(emailAddress);
		developer.setDescription(description);
		developer.setTagLine(tagLine);
		
		this.developerService.addDeveloper(developer);
		
		return view(request, response);
	}
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String alias = request.getParameter("alias");
		String homepageUrl = request.getParameter("homepageUrl");
		String emailAddress = request.getParameter("emailAddress");
		String description = request.getParameter("description");
		String tagLine = request.getParameter("tagLine");
		Long developerID = StringUtils.getLong(request.getParameter("developerID"));
		
		boolean result = false;
		
		if(developerID != null) {
			Developer developer = new Developer();
			developer.setDeveloperID(developerID);
			developer.setName(name);
			developer.setAlias(alias);
			developer.setHomepageUrl(homepageUrl);
			developer.setEmailAddress(emailAddress);
			developer.setDescription(description);
			developer.setTagLine(tagLine);
			
			result = this.developerService.updateDeveloper(developer);
		}
		
		ModelAndView mav = view(request, response);
		if(result) {
			mav.addObject("operationResult", "Developer successfully updated.");
		} else {
			mav.addObject("operationResult", "Unable to update the developer.");
		}
		
		return mav;
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
