package org.myjerry.voyage.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.myjerry.util.StringUtils;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Developer {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long developerID;
	
	@Persistent
	private String name;
	
	@Persistent
	private String tagLine;
	
	@Persistent
	private String alias;
	
	@Persistent
	private String homepageUrl;
	
	@Persistent
	private String emailAddress;
	
	@Persistent
	private Text description;
	
	public void update(Developer developer) {
		if(developer == null) {
			return;
		}
		
		this.name = developer.name;
		this.tagLine = developer.tagLine;
		this.alias = developer.alias;
		this.homepageUrl = developer.homepageUrl;
		this.emailAddress = developer.emailAddress;
		this.description = developer.description;
	}
	
	public String getDeveloperUrl() {
		if(StringUtils.isNotEmpty(this.homepageUrl)) {
			return this.homepageUrl;
		}
		
		return "/myfriends/" + this.alias + ".html";
	}

	/**
	 * @return the developerID
	 */
	public Long getDeveloperID() {
		return developerID;
	}

	/**
	 * @param developerID the developerID to set
	 */
	public void setDeveloperID(Long developerID) {
		this.developerID = developerID;
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
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
	 * @return the homepageUrl
	 */
	public String getHomepageUrl() {
		return homepageUrl;
	}

	/**
	 * @param homepageUrl the homepageUrl to set
	 */
	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}

	/**
	 * @return the tagLine
	 */
	public String getTagLine() {
		return tagLine;
	}

	/**
	 * @param tagLine the tagLine to set
	 */
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
	
}
