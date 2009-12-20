package org.myjerry.voyage.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.voyage.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class DefaultTemplateController extends MultiActionController {
	
	@Autowired
	private TemplateService templateService;
	
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(".admin");
		return mav;
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
