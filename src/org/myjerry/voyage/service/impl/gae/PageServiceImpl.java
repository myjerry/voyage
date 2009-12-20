package org.myjerry.voyage.service.impl.gae;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.myjerry.voyage.model.Page;
import org.myjerry.voyage.persistence.PersistenceManagerFactoryImpl;
import org.myjerry.voyage.service.PageService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PageServiceImpl implements PageService {

	@Override
	public boolean addPage(Page page) {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			manager.makePersistent(page);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Page> getPages() {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			List<Page> pages = (List<Page>) manager.newQuery("select from " + Page.class.getName()).execute();
			return manager.detachCopyAll(pages);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return null;
	}

	@Override
	public Page getPage(Long pageID) {
		if(pageID == null) {
			return null;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Page.class.getSimpleName(), pageID);
			Page page = manager.getObjectById(Page.class, key);
			// hack for GAE to fetch text fields
			page.getContents();
			return manager.detachCopy(page);
		
		} catch(JDOObjectNotFoundException e) {
			// do nothing
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public boolean deletePage(Long pageID) {
		if(pageID == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Page.class.getSimpleName(), pageID);
			Page page = manager.getObjectById(Page.class, key);
			// hack for GAE to fetch text fields
			page.getContents();
			manager.deletePersistent(page);
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

	@SuppressWarnings("unchecked")
	@Override
	public Long getPageForUri(String uri) {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		
		Query query = manager.newQuery(Page.class);
	    query.setFilter("url == urlParam");
	    query.declareParameters("String urlParam");

	    try {
			List<Page> results = (List<Page>) query.execute(uri);
			if(results != null && results.size() == 1) {
				return results.get(0).getPageID();
			}
	    } finally {
	        query.closeAll();
	        manager.close();
	    }
		
		return null;
	}

	@Override
	public boolean updatePage(Page page) {
		if(page.getPageID() == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Page.class.getSimpleName(), page.getPageID());
			Page dbPage = manager.getObjectById(Page.class, key);
			
			dbPage.update(page);
			manager.makePersistent(dbPage);
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
