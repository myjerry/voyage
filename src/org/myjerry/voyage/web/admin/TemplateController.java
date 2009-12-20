package org.myjerry.voyage.web.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.util.StringUtils;
import org.myjerry.voyage.model.Template;
import org.myjerry.voyage.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class TemplateController extends MultiActionController {
	
	@Autowired
	private TemplateService templateService;
	
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Collection<Template> templates = this.templateService.getTemplates();
		mav.addObject("templates", templates);
		mav.addObject("totalTemplates", templates.size());
		
		mav.setViewName(".admin.templates");
		return mav;
	}

	public ModelAndView viewAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("editMode", false);
		mav.setViewName(".admin.templates.add");
		return mav;
	}
	
	public ModelAndView editForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Long templateID = StringUtils.getLong(request.getParameter("templateID"));
		Template template = this.templateService.getTemplate(templateID);
		mav.addObject("template", template);
		mav.addObject("editMode", true);
		mav.setViewName(".admin.templates.add");
		return mav;
	}

	public ModelAndView removeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Long templateID = StringUtils.getLong(request.getParameter("templateID"));
		Template template = this.templateService.getTemplate(templateID);
		mav.addObject("template", template);
		
		mav.setViewName(".admin.templates.delete");
		return mav;
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String contents = request.getParameter("contents");
		
		Template template = new Template();
		template.setName(name);
		template.setContents(contents);
		this.templateService.addTemplate(template);
		
		return view(request, response);
	}
	
	public ModelAndView confirmDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Long templateID = StringUtils.getLong(request.getParameter("templateID"));
		boolean result = this.templateService.deteteTemplate(templateID);

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
		String contents = request.getParameter("contents");
		Long templateID = StringUtils.getLong(request.getParameter("templateID"));
		
		boolean result = false;

		if(templateID != null) {
			Template template = new Template();
			template.setTemplateID(templateID);
			template.setName(name);
			template.setContents(contents);
			
			this.templateService.updateTemplate(template);
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
