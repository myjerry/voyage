package org.myjerry.voyage.service;

import java.util.Collection;

import org.myjerry.voyage.model.Project;

public interface ProjectService {
	
	public boolean addProject(Project project);
	
	public Project getProject(Long projectID);
	
	public Collection<Project> getProjects();
	
	public boolean deleteProject(Long projectID);
	
	public boolean updateProject(Project project);

}
