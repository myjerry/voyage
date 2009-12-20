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
	<form name="voyageForm" method="POST" action="/admin/developers.html">
	
		<input id="actionParam" name="_action" value="submit" type="hidden" />
		<input name="developerID" value="${developerID}" type="hidden" />
		
		<div class="contain">
		
			<h2>Add a new developer!</h2>
		
			<div class="form-row">
				<label>Name</label>
				<div class="form-row-input">
					<input name="name" maxlength="100" size="50" value="${developer.name}" />
				</div>
			</div>
	
			<div class="form-row">
				<label>Alias</label>
				<div class="form-row-input">
					<input name="alias" maxlength="500" size="50" value="${developer.alias}"/>
				</div>
			</div>

			<div class="form-row">
				<label>Homepage URL</label>
				<div class="form-row-input">
					<input name="homepageUrl" maxlength="500" size="50" value="${developer.homepageUrl}"/>
				</div>
			</div>

			<div class="form-row">
				<div class="form-row-input">
					<label>Email Address</label>
					<input name="emailAddress" maxlength="500" size="50" value="${developer.emailAddress}"/>
				</div>
			</div>
	
			<div class="form-row">
				<label>Description</label>
				<div class="form-row-input">
					<textarea rows="20" cols="200" name="description" class="wideTextArea">${developer.description}</textarea>
				</div>
			</div>
			
			<div style="clear: both;"></div>
			
		</div>

		<div class="button-bar">
			<div class="region">
				<a href="javascript:submitForm('add');" class="form-control">Add</a>
				<a href="/admin/developers.html" class="form-control">Cancel</a>
			</div>
		</div>        
		
	</form>
</div>


