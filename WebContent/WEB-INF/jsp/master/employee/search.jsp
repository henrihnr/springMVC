<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	
	<link href="<c:url value="/resources/css/pagination.css"/>" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.pagination.js"/>"></script>
	
	<script type="text/javascript">
	   	var totalRows;
		var pageSize;
		var currentPage;
	
		var isFirstLoad = true;
	
		function pageSelectCallback(page_index, jq){
		   	if (isFirstLoad) {
		   		isFirstLoad = false;
		   		return false;
		   	}
		   	
		   	$("input[name='offset']").val(page_index * pageSize);
		   	$("input[name='output']").val("");

	        $("#form").trigger("submit");
	    }
    
        $(document).ready(function() {

            totalRows = $("input[name='totalRows']").val();
			pageSize = $("input[name='pageSize']").val();
			currentPage = $("input[name='offset']").val() / pageSize;

			$(".pagination").pagination(totalRows, 
	                {callback:pageSelectCallback, 
	        		items_per_page:pageSize,
	        		current_page:currentPage,
	        		num_edge_entries:2});
        });
    </script>
</head>
<body>
<form id="form" action="<c:url value="/master/employee/"/>" method="post">
	<input type="hidden" name="offset" value="${pagingInfo.offset}"/>
	<input type="hidden" name="pageSize" value="${pagingInfo.pageSize}"/>
	<input type="hidden" name="totalRows" value="${pagingInfo.totalRows}"/>
	
	<div id="container-full">
		<h2>Daftar Karyawan</h2>
		
		<div id="main-full">
			<jsp:include page="../../message.jsp">
				<jsp:param value="employee" name="command"/>
			</jsp:include>
			
			<h3>Kriteria Pencarian</h3>
			<fieldset>
				<table width="100%">
					<tr>
						<td>
							<label>ID:</label>
							<input type="text" name="id" class="input" size="50" value="${employee.id}"/>
						</td>
						<td>
							<label>Email:</label>
							<input type="text" name="email" class="input" size="50" value="${employee.email}"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>Nama:</label>
							<input type="text" name="name" class="input" size="50" value="${employee.name}"/>
						</td>
						<td>
							<label>No. Telp:</label>
							<input type="text" name="phone" class="input" size="50" value="${employee.phone}"/>
						</td>
					</tr>
				</table>
				<br/><br/>
				<input type="submit" value="Search" class="button"/>
			</fieldset>
			
			<h3>Hasil Pencarian</h3>
			<fieldset>
				<div class="pagination"></div>
				<table class="second">
					<thead>
						<tr>
							<th>No</th>
							<th>ID</th>
							<th>Nama</th>
							<th>Jenis Kelamin</th>
							<th>DOB</th>
							<th>Email</th>
							<th>No. Telp</th>
							<th>Jam Kerja</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="employee" items="${employees}" varStatus="status">
						<tr>
							<td align="right">${status.index + pagingInfo.offset + 1}</td>
							<td><a class="view" href="<c:url value="/master/employee/detail?id=${employee.id}"/>">${employee.id}</a></td>
							<td>${employee.name}</td>
							<td>
								<c:if test="${employee.gender == 'm'}">Laki-Laki</c:if>
								<c:if test="${employee.gender == 'f'}">Perempuan</c:if>
							</td>
							<td align="center"><fmt:formatDate value="${employee.dob}" pattern="dd-MM-yyyy"/></td>
							<td>${employee.email}</td>
							<td>${employee.phone}</td>
							<td align="center"><fmt:formatDate value="${employee.workTime}" pattern="hh:mm"/></td>
							<td>
								<c:if test="${employee.status}">Aktif</c:if>
								<c:if test="${not empty employee.status && !employee.status}">Tidak Aktif</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty employees}">
               	 		<tr>
               	 			<td colspan="9" align="center">no record(s) found.</td>
               	 		</tr>
               	 	</c:if>
					</tbody>
				</table>
			</fieldset>
			
			<a class="button" href="<c:url value="/master/employee/detail"/>">Tambah Karyawan</a>
			<br/><br/>
			
		</div>
		<div class="clear"></div>
	</div>
</form>
</body>
</html>
