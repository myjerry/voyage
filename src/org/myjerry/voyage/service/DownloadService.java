package org.myjerry.voyage.service;

import java.util.Collection;

import org.myjerry.voyage.model.Download;

public interface DownloadService {
	
	public boolean addDownload(Download download);
	
	public Download getDownload(Long downloadID, Long projectID);
	
	public Collection<Download> getDownloads();

	public boolean deleteDownload(Long downloadID, Long projectID);
	
	public boolean updateDownload(Download download);
	
}
