package org.myjerry.voyage.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Project {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long projectID;
	
	@Persistent
	private String name;
	
	@Persistent
	private String urlAlias;
	
	@Persistent
	private String shortDescription;
	
	@Persistent
	private String repositoryPath;
	
	@Persistent
	private String documentationPath;
	
	@Persistent
	private String downloadPath;
	
	@Persistent
	private String issueTracker;

	@Persistent
	private Text description;
	
	@Persistent
	private Long templateID;
	
	public void update(Project project) {
		if(project == null) {
			return;
		}
		
		this.name = project.name;
		this.urlAlias = project.urlAlias;
		this.shortDescription = project.shortDescription;
		this.repositoryPath = project.repositoryPath;
		this.documentationPath = project.documentationPath;
		this.downloadPath = project.downloadPath;
		this.issueTracker = project.issueTracker;
		this.description = project.description;
		this.templateID = project.templateID;
	}
	
	public String getProjectUrl() {
		String uri = "/" + urlAlias + ".html";
		return uri;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the urlAlias
	 */
	public String getUrlAlias() {
		return urlAlias;
	}

	/**
	 * @param urlAlias the urlAlias to set
	 */
	public void setUrlAlias(String urlAlias) {
		this.urlAlias = urlAlias;
	}

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the repositoryPath
	 */
	public String getRepositoryPath() {
		return repositoryPath;
	}

	/**
	 * @param repositoryPath the repositoryPath to set
	 */
	public void setRepositoryPath(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}

	/**
	 * @return the documentationPath
	 */
	public String getDocumentationPath() {
		return documentationPath;
	}

	/**
	 * @param documentationPath the documentationPath to set
	 */
	public void setDocumentationPath(String documentationPath) {
		this.documentationPath = documentationPath;
	}

	/**
	 * @return the downloadPath
	 */
	public String getDownloadPath() {
		return downloadPath;
	}

	/**
	 * @param downloadPath the downloadPath to set
	 */
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	/**
	 * @return the issueTracker
	 */
	public String getIssueTracker() {
		return issueTracker;
	}

	/**
	 * @param issueTracker the issueTracker to set
	 */
	public void setIssueTracker(String issueTracker) {
		this.issueTracker = issueTracker;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		if(this.description != null) {
			return this.description.getValue();
		}
		return null;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = new Text(description);
	}

	/**
	 * @return the templateID
	 */
	public Long getTemplateID() {
		return templateID;
	}

	/**
	 * @param templateID the templateID to set
	 */
	public void setTemplateID(Long templateID) {
		this.templateID = templateID;
	}
	
}
