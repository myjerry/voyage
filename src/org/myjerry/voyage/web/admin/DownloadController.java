package org.myjerry.voyage.web.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.util.StringUtils;
import org.myjerry.voyage.model.Download;
import org.myjerry.voyage.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class DownloadController extends MultiActionController {
	
	@Autowired
	private DownloadService downloadService;
	
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Collection<Download> downloads = this.downloadService.getDownloads();
		mav.addObject("downloads", downloads);
		mav.addObject("totalDownloads", downloads.size());
		
		mav.setViewName(".admin.downloads");
		return mav;
	}

	public ModelAndView viewAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("editMode", false);
		mav.setViewName(".admin.downloads.add");
		return mav;
	}
	
	public ModelAndView editForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Long downloadID = StringUtils.getLong(request.getParameter("downloadID"));
		Long projectID = StringUtils.getLong(request.getParameter("projectID"));
		Download download = this.downloadService.getDownload(downloadID, projectID);
		mav.addObject("download", download);
		mav.addObject("editMode", true);
		mav.setViewName(".admin.downloads.add");
		return mav;
	}

	public ModelAndView removeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Long downloadID = StringUtils.getLong(request.getParameter("downloadID"));
		Long projectID = StringUtils.getLong(request.getParameter("projectID"));
		Download download = this.downloadService.getDownload(downloadID, projectID);
		mav.addObject("download", download);

		mav.setViewName(".admin.downloads.delete");
		return mav;
	}
	
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String projectID = request.getParameter("projectID");
		String downloadFilename = request.getParameter("downloadFilename");
		String downloadUrl = request.getParameter("downloadUrl");
		String versionNumber = request.getParameter("versionNumber");
		
		Download download = new Download();
		download.setProjectID(StringUtils.getLong(projectID));
		download.setDownloadFilename(downloadFilename);
		download.setDownloadUrl(downloadUrl);
		download.setVersionNumber(versionNumber);
		this.downloadService.addDownload(download);
		
		return view(request, response);
	}
	
	public ModelAndView confirmDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Long downloadID = StringUtils.getLong(request.getParameter("downloadID"));
		Long projectID = StringUtils.getLong(request.getParameter("projectID"));
		boolean result = this.downloadService.deleteDownload(downloadID, projectID);

		mav = view(request, response);
		if(result) {
			mav.addObject("operationResult", "Download successfully removed.");
		} else {
			mav.addObject("operationResult", "Unable to remove the download.");
		}
		
		return mav;
	}
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String projectID = request.getParameter("projectID");
		String downloadFilename = request.getParameter("downloadFilename");
		String downloadUrl = request.getParameter("downloadUrl");
		String versionNumber = request.getParameter("versionNumber");
		Long downloadID = StringUtils.getLong(request.getParameter("downloadID"));
		
		boolean result = false;
		
		if(downloadID != null) {
			Download download = new Download();
			download.setDownloadID(downloadID);
			download.setProjectID(StringUtils.getLong(projectID));
			download.setDownloadFilename(downloadFilename);
			download.setDownloadUrl(downloadUrl);
			download.setVersionNumber(versionNumber);
			
			result = this.downloadService.updateDownload(download);
		}
		
		ModelAndView mav = view(request, response);
		if(result) {
			mav.addObject("operationResult", "Download successfully updated.");
		} else {
			mav.addObject("operationResult", "Unable to update the download.");
		}
		
		return view(request, response);
	}

	/**
	 * @return the downloadService
	 */
	public DownloadService getDownloadService() {
		return downloadService;
	}

	/**
	 * @param downloadService the downloadService to set
	 */
	public void setDownloadService(DownloadService downloadService) {
		this.downloadService = downloadService;
	}

}
