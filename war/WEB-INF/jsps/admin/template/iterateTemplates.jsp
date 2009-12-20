<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/myjerry.tld" prefix="mj"%>
<%@ page isELIgnored="false" %>

<script type="text/javascript">
  function submitForm(name) {
    document.getElementById('actionParam').value = name;
    document.voyageForm.submit();
  }

	function performAction(action, templateID) {
	    document.getElementById('actionParam').value = action;
	    document.getElementById('templateID').value = templateID;
	    document.voyageForm.submit();
	}
</script>

<h2 class="workflow">Welcome!</h2>
 
<div style="clear:both;"></div>

<c:if test="${not empty operationResult}">
	<div class="message">
		${operationResult}
	</div>
</c:if>

<div class="form">
	<form name="voyageForm" method="POST" action="/admin/templates.html">
	
		<input id="actionParam" name="_action" value="submit" type="hidden" />
		<input id="templateID" name="templateID" type="hidden" />
		
		<div class="contain">
			<h2 class="zero-space">Total Templates: ${totalTemplates}</h2>

			<c:if test="${not empty templates}">
				<div id="selectedPop" style="width: 826px;">
					<div class="nonsort-a">
					
						<table width="100%">
							<thead>
								<tr>
									<th>Name</th>
									<th>&nbsp;</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							
							<c:forEach items="${templates}" var="template">
								<tr>
									<td>${template.name}</td>
									<td><a href="javascript:performAction('editForm', '${template.templateID}');" ">Edit</a></td>
									<td><a href="javascript:performAction('removeForm', '${template.templateID}');" ">Remove</a></td>
								</tr>
							</c:forEach>
							
						</table>
					
					</div>
				</div>
			</c:if>
		
			<div style="clear:both;"></div>
	
		</div>
	
		<div class="button-bar">
			<div class="region">
				<a href="javascript:submitForm('viewAddForm');" class="form-control orange">Add</a>
				<a href="/admin.html" class="form-control">Cancel</a>
			</div>
		</div>

	</form>
</div>