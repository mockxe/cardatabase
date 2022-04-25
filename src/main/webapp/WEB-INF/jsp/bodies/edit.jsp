<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<form action="${method}" method="post">
<input name="index" type="hidden" value="${index}" />
<input name="xsrftoken" type="hidden" value="${xsrftoken}" />
<div class=toolBar>
	<c:if test="${empty errorMsg}">
		<a><button>save</button></a>
	</c:if>
</div>
<div class="table">
	<table>
		<tr>
			<th>attribute</th>
			<th>value</th>
		</tr>
		<tr>
			<td>Car brand</td>
			<td>
				<select name="brand" id="brand">
					<%-- Insert generated HTML for brand selection here here --%>
					${brands}
					<%-- End of generated selection --%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Engine type</td>
			<td>
				<select name="engine" id="engine">
					<%-- Insert generated HTML for engine selection here here --%>
					${engines}
					<%-- End of generated selection --%>	
				</select>
			</td>
		</tr>
		<tr>
			<td>Displacement</td>
			<td>
				<input name= "displacement" type="number" value="${displacement}" /> cm<sup>3</sup>
			</td>
		</tr>
		<tr>
			<td>Date of first registration</td>
			<td>
				<input name="date" type="date" value="${date}" />
			</td>
		</tr>
		<tr>
			<td>Number of doors</td>
			<td>
				<input name="doors" type="number" value="${doors}" />
			</td>
		</tr>
		<tr>
			<td>Color</td>
			<td>
				<input name="color" type="color" value="${color}" />
			</td>
		</tr>
		<tr>
			<td>Total weight</td>
			<td>
				<input name="weight" type="number" value="${weight}" /> kg
			</td>
		</tr>
		<tr>
			<td>Chassis number</td>
			<td>
				<input name="chassis" type="text" value="${chassis}">
			</td>
		</tr>
		<tr>
			<td>Pollutant class</td>
			<td>
				Euro
				<select name="pollutant" id="pollutant">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>
				<textarea name="description" rows="5" cols="60">${description}</textarea>
			</td>
		</tr>
	</table>
</div>
</form>
<script type="text/javascript">
	document.getElementById("brand").value = "${brandInt}";
	document.getElementById("engine").value = "${engineInt}";
	document.getElementById("pollutant").value = "${pollutant}";
</script>