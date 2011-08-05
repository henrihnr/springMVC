<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	
	<script type="text/javascript">
	// validation
	 
	    $(document).ready(function() {
	    	
	    });
	</script>
	
</head>

<body>
<form action="<c:url value="/master/employee/detail"/>" method="post">
	<input type="hidden" name="mode" value="${mode}"/>
	
	<div id="container-full">
		<h2>Karyawan</h2>
		
		<div id="main-full">
			<jsp:include page="../../message.jsp">
				<jsp:param value="employee" name="command"/>
			</jsp:include>
                	
			<h3>Detail Karyawan</h3>
				<fieldset>
					<table width="100%">
					<tr>
						<td>
							<label>ID:</label>
       	 					<c:if test="${mode == 0}">
       	 						<input type="text" class="input" name="id" value="${employee.id}"/>
       	 					</c:if>
       	 					<c:if test="${mode == 1}">
       	 						<input type="text" class=input-disabled name="id" value="${employee.id}" readonly="readonly"/>
       	 					</c:if>
                	 	</td>
                	 	<td>
                	 		<label>Email:</label>
                	 		<input type="text" class="input" name="email" size="40" value="${employee.email}"/>
                	 	</td>
                	 </tr>
                	 <tr>
           	 			<td>
           	 				<label>Nama:</label>
           	 				<input type="text" class="input" name="name" size="40" value="${employee.name}"/>
           	 			</td>
           	 			<td>
           	 				<label>No. Telp:</label>
           	 				<input type="text" class="input" name="phone" size="40" value="${employee.phone}"/>
           	 			</td>
                	 </tr>
                	 <tr>
                	 	<td>
           	 				<label>DOB:</label>
           	 				<input type="text" class="input" name="dob" value="${employee.dob}"/>
           	 			</td>
           	 			<td>
           	 				<label>Work Time:</label>
           	 				<input type="text" class="input" name="workTime" value="${employee.workTime}"/>
           	 			</td>
                	 </tr>
                	 <tr>
                	 	<td>
           	 				<label>Jenis Kelamin:</label>
           	 				<input type="radio" name="gender" value="m" <c:if test="${employee.gender == 'm'}">checked="checked"</c:if>/> Laki-Laki
							<input type="radio" name="gender" value="f" <c:if test="${employee.gender == 'f'}">checked="checked"</c:if>/> Perempuan
           	 			</td>
						<td>
							<label>Status:</label>
							<input type="radio" name="status" value="1" <c:if test="${employee.status}">checked="checked"</c:if>/> Aktif
							<input type="radio" name="status" value="0" <c:if test="${not empty employee.status && !employee.status}">checked="checked"</c:if>/> Tidak Aktif
						</td>
					</tr>
				</table>
				<br/><br/>
				<input type="submit" value="Simpan" class="button"/>
			</fieldset>
			
			<a class="back" href="<c:url value="/master/employee/"/>">Kembali ke Daftar Karyawan</a>
			<br/><br/>
		</div>
		<div class="clear"></div>
	</div>
</form>
</body>
</html>
