package org.myjerry.voyage.service.impl.gae;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.myjerry.voyage.model.Download;
import org.myjerry.voyage.persistence.PersistenceManagerFactoryImpl;
import org.myjerry.voyage.service.DownloadService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DownloadServiceImpl implements DownloadService {

	@Override
	public boolean addDownload(Download download) {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			manager.makePersistent(download);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return false;
	}

	@Override
	public Download getDownload(Long downloadID, Long projectID) {
		if(downloadID == null || projectID == null) {
			return null;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Download.class.getSimpleName(), downloadID);
			Download download = manager.getObjectById(Download.class, key);
			if(projectID.equals(download.getProjectID())) {
				return manager.detachCopy(download);
			}
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
	public Collection<Download> getDownloads() {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			List<Download> downloads = (List<Download>) manager.newQuery("select from " + Download.class.getName()).execute();
			return manager.detachCopyAll(downloads);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return null;
	}

	@Override
	public boolean deleteDownload(Long downloadID, Long projectID) {
		if(downloadID == null || projectID == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Download.class.getSimpleName(), downloadID);
			Download download = manager.getObjectById(Download.class, key);
			if(projectID.equals(download.getProjectID())) {
				manager.deletePersistent(download);
				return true;
			}
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
	public boolean updateDownload(Download download) {
		if(download.getDownloadID() == null || download.getProjectID() == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Download.class.getSimpleName(), download.getDownloadID());
			Download dbDownload = manager.getObjectById(Download.class, key);
			if(download.getProjectID().equals(dbDownload.getProjectID())) {
				dbDownload.update(download);
				manager.makePersistent(dbDownload);
				return true;
			}
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
