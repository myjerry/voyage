package org.myjerry.voyage.service;

import java.util.Collection;

import org.myjerry.voyage.model.Developer;

public interface DeveloperService {
	
	public boolean addDeveloper(Developer developer);
	
	public Developer getDeveloper(Long developerID);
	
	public Collection<Developer> getDevelopers();
	
	public boolean deleteDeveloper(Long developerID);
	
	public boolean updateDeveloper(Developer developer);

}
