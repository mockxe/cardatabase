<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="deletion">
	<c:if test="${empty errorMsg}">
	<p>Are you sure you want to delete <b>car ${index}</b></p>
	<a href="deleteYes?index=${index}&xsrftoken=${xsrftoken}"><button>Yes!</button></a>
	<a href="deleteNo"><button>No</button></a>
	</c:if> 
</div>