package org.myjerry.voyage.service.impl.gae;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.myjerry.voyage.model.Developer;
import org.myjerry.voyage.persistence.PersistenceManagerFactoryImpl;
import org.myjerry.voyage.service.DeveloperService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DeveloperServiceImpl implements DeveloperService {

	@Override
	public boolean addDeveloper(Developer developer) {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			manager.makePersistent(developer);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return false;
	}

	@Override
	public Developer getDeveloper(Long developerID) {
		if(developerID == null) {
			return null;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Developer.class.getSimpleName(), developerID);
			Developer developer = manager.getObjectById(Developer.class, key);
			// hack for GAE to fetch text fields
			developer.getDescription();
			return manager.detachCopy(developer);
		
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
	public Collection<Developer> getDevelopers() {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			List<Developer> developers = (List<Developer>) manager.newQuery("select from " + Developer.class.getName()).execute();
			return manager.detachCopyAll(developers);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return null;
	}

	@Override
	public boolean deleteDeveloper(Long developerID) {
		if(developerID == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Developer.class.getSimpleName(), developerID);
			Developer developer = manager.getObjectById(Developer.class, key);
			// hack for GAE to fetch text fields
			developer.getDescription();
			manager.deletePersistent(developer);
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
	public boolean updateDeveloper(Developer developer) {
		if(developer.getDeveloperID() == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Developer.class.getSimpleName(), developer.getDeveloperID());
			Developer dev = manager.getObjectById(Developer.class, key);

			dev.update(developer);
			
			manager.makePersistent(dev);
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
