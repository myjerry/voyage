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
	<form name="voyageForm" method="POST" action="/admin/downloads.html">
	
		<input id="actionParam" name="_action" value="submit" type="hidden" />
		<input name="downloadID" value="${download.downloadID}" type="hidden" />
		
		<div class="contain">
		
			<h2>Add a new download!</h2>
		
			<div class="form-row">
				<label>Project ID</label>
				<div class="form-row-input">
					<input name="projectID" maxlength="100" size="50" value="${download.projectID}" />
				</div>
			</div>
	
			<div class="form-row">
				<label>Download Filename</label>
				<div class="form-row-input">
					<input name="downloadFilename" maxlength="500" size="50" value="${download.downloadFilename}"/>
				</div>
			</div>

			<div class="form-row">
				<label>Download URL</label>
				<div class="form-row-input">
					<input name="downloadUrl" maxlength="500" size="50" value="${download.downloadUrl}"/>
				</div>
			</div>

			<div class="form-row">
				<div class="form-row-input">
					<label>Version Number</label>
					<input name="versionNumber" maxlength="500" size="50" value="${download.versionNumber}"/>
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
				<a href="/admin/downloads.html" class="form-control">Cancel</a>
			</div>
		</div>        
		
	</form>
</div>


