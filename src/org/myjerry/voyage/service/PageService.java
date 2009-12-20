package org.myjerry.voyage.service;

import java.util.Collection;

import org.myjerry.voyage.model.Page;

public interface PageService {
	
	public boolean addPage(Page page);
	
	public Page getPage(Long pageID);
	
	public Collection<Page> getPages();
	
	public boolean deletePage(Long pageID);
	
	public Long getPageForUri(String uri);
	
	public boolean updatePage(Page page);
	
}
