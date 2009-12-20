package org.myjerry.voyage.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Download {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long downloadID;
	
	@Persistent
	private Long projectID;
	
	@Persistent
	private String downloadFilename;
	
	@Persistent
	private String downloadUrl;
	
	@Persistent
	private String versionNumber;
	
	public void update(Download download) {
		if(download == null) {
			return;
		}
		
		this.projectID = download.projectID;
		this.downloadFilename = download.downloadFilename;
		this.downloadUrl = download.downloadUrl;
		this.versionNumber = download.versionNumber;
	}

	/**
	 * @return the downloadID
	 */
	public Long getDownloadID() {
		return downloadID;
	}

	/**
	 * @param downloadID the downloadID to set
	 */
	public void setDownloadID(Long downloadID) {
		this.downloadID = downloadID;
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
	 * @return the downloadFilename
	 */
	public String getDownloadFilename() {
		return downloadFilename;
	}

	/**
	 * @param downloadFilename the downloadFilename to set
	 */
	public void setDownloadFilename(String downloadFilename) {
		this.downloadFilename = downloadFilename;
	}

	/**
	 * @return the downloadUrl
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}

	/**
	 * @param downloadUrl the downloadUrl to set
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	/**
	 * @return the versionNumber
	 */
	public String getVersionNumber() {
		return versionNumber;
	}

	/**
	 * @param versionNumber the versionNumber to set
	 */
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

}
