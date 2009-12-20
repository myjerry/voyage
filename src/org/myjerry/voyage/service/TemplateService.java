package org.myjerry.voyage.service;

import java.util.Collection;

import org.myjerry.voyage.model.Template;

public interface TemplateService {
	
	public boolean addTemplate(Template template);
	
	public Template getTemplate(Long templateID);
	
	public Collection<Template> getTemplates();
	
	public boolean deteteTemplate(Long templateID);
	
	public Template getDefaultTemplate();
	
	public boolean updateTemplate(Template template);

}
