package org.myjerry.voyage.service.impl.gae;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.myjerry.voyage.model.Template;
import org.myjerry.voyage.persistence.PersistenceManagerFactoryImpl;
import org.myjerry.voyage.service.TemplateService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TemplateServiceImpl implements TemplateService {

	@Override
	public boolean addTemplate(Template template) {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			manager.makePersistent(template);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return false;
	}

	@Override
	public Template getTemplate(Long templateID) {
		if(templateID == null) {
			return null;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Template.class.getSimpleName(), templateID);
			Template template = manager.getObjectById(Template.class, key);
			// hack for GAE to fetch text fields
			template.getContents();
			return manager.detachCopy(template);
		
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
	public Collection<Template> getTemplates() {
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			List<Template> templates = (List<Template>) manager.newQuery("select from " + Template.class.getName()).execute();
			return manager.detachCopyAll(templates);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return null;
	}

	@Override
	public boolean deteteTemplate(Long templateID) {
		if(templateID == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Template.class.getSimpleName(), templateID);
			Template template = manager.getObjectById(Template.class, key);
			// hack for GAE to fetch text fields
			template.getContents();
			manager.deletePersistent(template);
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
	public Template getDefaultTemplate() {
		Collection<Template> templates = this.getTemplates();
		Iterator<Template> iterator = templates.iterator();
		Template template = iterator.next();
		return this.getTemplate(template.getTemplateID());
	}

	@Override
	public boolean updateTemplate(Template template) {
		if(template.getTemplateID() == null) {
			return false;
		}
		
		PersistenceManager manager = PersistenceManagerFactoryImpl.getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(Template.class.getSimpleName(), template.getTemplateID());
			Template dbTemplate = manager.getObjectById(Template.class, key);
			
			dbTemplate.update(template);
			manager.makePersistent(dbTemplate);
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
