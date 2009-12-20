<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/myjerry.tld" prefix="mj"%>
<%@ page isELIgnored="false" %>

<script type="text/javascript">
  function submitForm(name) {
    document.getElementById('actionParam').value = name;
    document.voyageForm.submit();
  }

	function performAction(action, pageID) {
	    document.getElementById('actionParam').value = action;
	    document.getElementById('pageID').value = pageID;
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
	<form name="voyageForm" method="POST" action="/admin/pages.html">
	
		<input id="actionParam" name="_action" type="hidden" />
		<input id="pageID" name="pageID" type="hidden" />
		
		<div class="contain">
			<h2 class="zero-space">Total Pages: ${totalPages}</h2>

			<c:if test="${not empty pages}">
				<div id="selectedPop" style="width: 826px;">
					<div class="nonsort-a">
					
						<table width="100%">
							<thead>
								<tr>
									<th>Title</th>
									<th>URL</th>
									<th>Project ID</th>
									<th>&nbsp;</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							
							<c:forEach items="${pages}" var="xPage">
								<tr>
									<td>${xPage.title}</td>
									<td>${xPage.url}</td>
									<td>${xPage.projectID}</td>
									<td><a href="javascript:performAction('editForm', '${xPage.pageID}');" ">Edit</a></td>
									<td><a href="javascript:performAction('removeForm', '${xPage.pageID}');" ">Remove</a></td>
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