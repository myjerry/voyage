package org.myjerry.voyage.service.impl.gae;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.myjerry.voyage.model.Project;
import org.myjerry.voyage.persistence.PersistenceManagerFactoryImpl;
import org.myjerry.voyage.service.ProjectService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ProjectServiceImpl implements ProjectService {

	@Override
	public boolean addProject(Project project) {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			manager.makePersistent(project);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return false;
	}

	@Override
	public Project getProject(Long projectID) {
		if(projectID == null) {
			return null;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Project.class.getSimpleName(), projectID);
			Project project = manager.getObjectById(Project.class, key);
			// hack for GAE to fetch text fields
			project.getDescription();
			return manager.detachCopy(project);
		
		} catch(JDOObjectNotFoundException e) {
			// do nothing
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Project> getProjects() {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			List<Project> projects = (List<Project>) manager.newQuery("select from " + Project.class.getName()).execute();
			return manager.detachCopyAll(projects);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return null;
	}

	@Override
	public boolean deleteProject(Long projectID) {
		if(projectID == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Project.class.getSimpleName(), projectID);
			Project project = manager.getObjectById(Project.class, key);
			// hack for GAE to fetch text fields
			project.getDescription();
			manager.deletePersistent(project);
			return true;
		} catch(JDOObjectNotFoundException e) {
			// do nothing
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return false;
	}

	@Override
	public boolean updateProject(Project project) {
		if(project.getProjectID() == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Project.class.getSimpleName(), project.getProjectID());
			Project dbProject = manager.getObjectById(Project.class, key);
			dbProject.update(project);
			manager.makePersistent(dbProject);
			return true;
		} catch(JDOObjectNotFoundException e) {
			// do nothing
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		
		return false;
	}

}
