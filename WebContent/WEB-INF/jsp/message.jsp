<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>

				  <c:if test="${not empty messages}">
               	  	<h4 class="message">Message</h4>
               		<ul>
               		  <c:forEach var="message" items="${messages}">
               			<li class="warning">${message}</li>
                	  </c:forEach>
                	</ul>
                  </c:if>
                  
                  <spring:hasBindErrors name="<%=request.getParameter("command")%>">
                   <spring:bind path="<%=request.getParameter("command") + ".*"%>">
               	  	<h4 class="message">Message</h4>
               		<ul>
               		  <c:forEach var="error" items="${status.errorMessages}">
               			<li class="warning"><c:out value="${error}"/></li>
                	  </c:forEach>
                	</ul>
                   </spring:bind>
                  </spring:hasBindErrors>
                  