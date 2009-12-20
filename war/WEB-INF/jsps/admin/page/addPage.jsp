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
	<form name="voyageForm" method="POST" action="/admin/pages.html">
	
		<input id="actionParam" name="_action" value="submit" type="hidden" />
		<input name="pageID" value="${xPage.pageID}" type="hidden" />
		
		<div class="contain">
		
			<h2>Add a new page!</h2>
		
			<div class="form-row">
				<label>Title</label>
				<div class="form-row-input">
					<input name="title" maxlength="100" size="50" value="${xPage.title}" />
				</div>
			</div>
	
			<div class="form-row">
				<label>URL</label>
				<div class="form-row-input">
					<input name="url" maxlength="500" size="50" value="${xPage.url}"/>
				</div>
			</div>

			<div class="form-row">
				<label>Project ID</label>
				<div class="form-row-input">
					<input name="projectID" maxlength="500" size="50" value="${xPage.projectID}"/>
				</div>
			</div>

			<div class="form-row">
				<label>Description</label>
				<div class="form-row-input">
					<textarea rows="20" cols="200" name="contents" class="wideTextArea">${xPage.contents}</textarea>
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
				<a href="/admin/pages.html" class="form-control">Cancel</a>
			</div>
		</div>        
		
	</form>
</div>


