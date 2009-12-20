package org.myjerry.voyage.web.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.util.StringUtils;
import org.myjerry.voyage.model.Page;
import org.myjerry.voyage.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class PageController extends MultiActionController {
	
	@Autowired
	private PageService pageService;
	
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Collection<Page> pages = this.pageService.getPages();
		mav.addObject("pages", pages);
		mav.addObject("totalPages", pages.size());
		
		mav.setViewName(".admin.pages");
		return mav;
	}

	public ModelAndView viewAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("editMode", false);
		mav.setViewName(".admin.pages.add");
		return mav;
	}
	
	public ModelAndView editForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Long pageID = StringUtils.getLong(request.getParameter("pageID"));
		Page page = this.pageService.getPage(pageID);
		mav.addObject("xPage", page);
		mav.addObject("editMode", true);
		mav.setViewName(".admin.pages.add");
		return mav;
	}

	public ModelAndView removeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Long pageID = StringUtils.getLong(request.getParameter("pageID"));
		Page page = this.pageService.getPage(pageID);
		mav.addObject("xPage", page);

		mav.setViewName(".admin.pages.delete");
		return mav;
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String url = request.getParameter("url");
		String projectID = request.getParameter("projectID");
		String contents = request.getParameter("contents");
				
		Page page = new Page();
		page.setTitle(title);
		page.setUrl(url);
		page.setProjectID(StringUtils.getLong(projectID));
		page.setContents(contents);
		this.pageService.addPage(page);
		
		return view(request, response);
	}

	public ModelAndView confirmDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Long pageID = StringUtils.getLong(request.getParameter("pageID"));
		boolean result = this.pageService.deletePage(pageID);

		mav = view(request, response);
		if(result) {
			mav.addObject("operationResult", "Page successfully removed.");
		} else {
			mav.addObject("operationResult", "Unable to remove the page.");
		}
		
		return mav;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String url = request.getParameter("url");
		String projectID = request.getParameter("projectID");
		String contents = request.getParameter("contents");
		Long pageID = StringUtils.getLong(request.getParameter("pageID"));
		
		boolean result = false;
		
		if(pageID != null) {
			Page page = new Page();
			page.setPageID(pageID);
			page.setTitle(title);
			page.setUrl(url);
			page.setProjectID(StringUtils.getLong(projectID));
			page.setContents(contents);
			
			result = this.pageService.updatePage(page);
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

}
