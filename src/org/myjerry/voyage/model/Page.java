package org.myjerry.voyage.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Page {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long pageID;
	
	@Persistent
	private String title;
	
	@Persistent
	private String url;
	
	@Persistent
	private Long projectID;

	@Persistent
	private Text contents;
	
	public void update(Page page) {
		if(page == null) {
			return;
		}
		
		this.title = page.title;
		this.url = page.url;
		this.projectID = page.projectID;
		this.contents = page.contents;
	}

	/**
	 * @return the pageID
	 */
	public Long getPageID() {
		return pageID;
	}

	/**
	 * @param pageID the pageID to set
	 */
	public void setPageID(Long pageID) {
		this.pageID = pageID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the projectID
	 */
	public Long getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		if(this.contents != null) {
			return this.contents.getValue();
		}
		return null;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = new Text(contents);
	}

}
