<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/myjerry.tld" prefix="mj"%>
<%@ page isELIgnored="false" %>

<h2 class="workflow">Welcome!</h2>
 
<div style="clear:both;"></div>

<div class="form">
	<form name="templateForm" method="POST">
	
		<input id="actionParam" name="_action" type="hidden" />

		<div class="contain">
			
			<h2>Choose an action</h2>
			
			<div class="form-row">
				<label>Default Template</label>
				<div class="form-row-input">
					<c:if test="${not empty defaultPage}">
						${defaultTemplate.name} (<a href="/admin/setDefaultTemplate.html">Change</a>)
					</c:if>
					<c:if test="${empty defaultPage}">
						None (<a href="/admin/setDefaultTemplate.html">Set Now</a>)
					</c:if> 
				</div>
			</div>

			<div class="form-row">
				<label>Manage</label>
				<div class="form-widget-container">
					<a href="/admin/developers.html">Developers</a>
					<br clear="all" />
					
					<a href="/admin/projects.html">Project</a>
					<br clear="all" />

					<a href="/admin/pages.html">Pages</a>
					<br clear="all" />

					<a href="/admin/downloads.html">Downloads</a>
					<br clear="all" />

					<a href="/admin/templates.html">Templates</a>
					<br clear="all" />
				</div>
			</div>

			<div style="clear:both;"></div>

		</div>

		<div class="button-bar">
			<div class="region">
				<a href="" class="form-control orange">Sign Out</a>
			</div>
		</div>        
		
		
	</form>
</div>
