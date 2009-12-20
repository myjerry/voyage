<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/myjerry.tld" prefix="mj"%>
<%@ page isELIgnored="false" %>

<script type="text/javascript">
  function submitForm(name) {
    document.getElementById('actionParam').value = name;
    document.voyageForm.submit();
  }
</script>

<h2 class="workflow">Welcome!</h2>
 
<div style="clear:both;"></div>

<div class="form">
	<form name="voyageForm" method="POST" action="/admin/projects.html">
	
		<input id="actionParam" name="_action" value="submit" type="hidden" />
		<input name="projectID" value="${project.projectID}" type="hidden" />
		
		<div class="contain">
		
			<h2>Add a new project!</h2>
		
			<div class="form-row">
				<label>Name</label>
				<div class="form-row-input">
					<input name="name" maxlength="100" size="50" value="${project.name}" />
				</div>
			</div>
	
			<div class="form-row">
				<label>URL Alias</label>
				<div class="form-row-input">
					<input name="urlAlias" maxlength="500" size="50" value="${project.urlAlias}"/>
				</div>
			</div>

			<div class="form-row">
				<label>Template ID</label>
				<div class="form-row-input">
					<input name="templateID" maxlength="500" size="50" value="${xPage.templateID}"/>
				</div>
			</div>

			<div class="form-row">
				<label>Short Description</label>
				<div class="form-row-input">
					<textarea rows="20" cols="200" name="shortDescription" class="mediumTextArea">${project.shortDescription}</textarea>
				</div>
			</div>

			<div class="form-row">
				<div class="form-row-input">
					<label>Repository Path</label>
					<input name="repositoryPath" maxlength="500" size="50" value="${project.repositoryPath}"/>
				</div>
			</div>
	
			<div class="form-row">
				<div class="form-row-input">
					<label>Documenation Path</label>
					<input name="documentationPath" maxlength="500" size="50" value="${project.documentationPath}"/>
				</div>
			</div>
	
			<div class="form-row">
				<div class="form-row-input">
					<label>Download Path</label>
					<input name="downloadPath" maxlength="500" size="50" value="${project.downloadPath}"/>
				</div>
			</div>
	
			<div class="form-row">
				<div class="form-row-input">
					<label>Issue Tracker</label>
					<input name="issueTracker" maxlength="500" size="50" value="${project.issueTracker}"/>
				</div>
			</div>
	
			<div class="form-row">
				<label>Description</label>
				<div class="form-row-input">
					<textarea rows="20" cols="200" name="description" class="wideTextArea">${project.description}</textarea>
				</div>
			</div>
			
			<div style="clear: both;"></div>
			
		</div>

		<div class="button-bar">
			<div class="region">
				<c:if test="${(empty editMode) or (editMode eq false)}">
					<a href="javascript:submitForm('add');" class="form-control">Add</a>
				</c:if>
				<c:if test="${(editMode eq true)}">
					<a href="javascript:submitForm('update');" class="form-control">Update</a>
				</c:if>
				<a href="/admin/projects.html" class="form-control">Cancel</a>
			</div>
		</div>        
		
	</form>
</div>


