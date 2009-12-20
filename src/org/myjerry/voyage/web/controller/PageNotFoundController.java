package org.myjerry.voyage.web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.util.StringUtils;
import org.myjerry.voyage.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class PageNotFoundController extends SimpleFormController {
	
	@Autowired
	private RequestService requestService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String uri = request.getRequestURI();
		
		String forward = this.requestService.getRequestForward(uri);
		if(StringUtils.isNotEmpty(forward)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
			return null;			
		}
		
		mav.setViewName(".page.not.found");
		return mav;
	}

	/**
	 * @return the requestService
	 */
	public RequestService getRequestService() {
		return requestService;
	}

	/**
	 * @param requestService the requestService to set
	 */
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}

}
