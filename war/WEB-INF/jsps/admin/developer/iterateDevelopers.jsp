<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/myjerry.tld" prefix="mj"%>
<%@ page isELIgnored="false" %>

<script type="text/javascript">
	function submitForm(action) {
		document.getElementById('actionParam').value = action;
		document.voyageForm.submit();
	}

  	function performAction(action, developerID) {
	    document.getElementById('actionParam').value = action;
	    document.getElementById('developerID').value = developerID;
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
	<form name="voyageForm" method="POST" action="/admin/developers.html">
	
		<input id="actionParam" name="_action" type="hidden" />
		<input id="developerID" name="developerID" type="hidden" />
		
		<div class="contain">
			<h2 class="zero-space">Total Developers: ${totalDevelopers}</h2>

			<c:if test="${not empty developers}">
				<div id="selectedPop" style="width: 826px;">
					<div class="nonsort-a">
					
						<table width="100%">
							<thead>
								<tr>
									<th>Name</th>
									<th>Alias</th>
									<th>Home Page</th>
									<th>Email Address</th>
									<th>&nbsp;</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							
							<c:forEach items="${developers}" var="developer">
								<tr>
									<td>${developer.name}</td>
									<td>${developer.alias}</td>
									<td>${developer.homepageUrl}</td>
									<td>${developer.emailAddress}</td>
									<td><a href="javascript:performAction('editForm', '${developer.developerID}');" ">Edit</a></td>
									<td><a href="javascript:performAction('removeForm', '${developer.developerID}');" ">Remove</a></td>
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

