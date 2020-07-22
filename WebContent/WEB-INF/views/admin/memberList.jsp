<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
%>    
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- 관리자용 admin.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />

<style>
div#search-container {
	margin: 0 0 10px 0;
	padding: 3px;
	width: 100%;
}
</style>
<script>
$(function(){
	
	$("#searchType").change(function(){
		$("#search-memberId, #search-email, #search-memberRole").hide();
		console.log($(this).val());//memberId -> #search-memberId
		$("#search-" + $(this).val()).css("display", "inline-block");
	});
	
});

</script>
<section id="memberList-container">
	<h2>회원관리</h2>
	
	<div id="search-container">
		
		<div id="search-User">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberRole"/>
				<input type="hidden" name="searchKeyword" value="U"  />
				<button type="submit">일반회원 조회</button>			
			</form>	
		</div>
		<div id="search-broker">
			<form action="<%=request.getContextPath()%>/admin/brokerList">
				<button type="submit"> 중개인회원 조회</button>			
			</form>	
		</div>
		<div id="search-brokerBlacklist">
			<form action="<%=request.getContextPath()%>/admin/brokerBlacklist">
				<button type="submit"> 중개인 블랙리스트</button>			
			</form>	
		</div>
	
	
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이메일</th>
				<th>회원관리</th>
				<th>전화번호</th>
				<th>가입날짜</th>
				<th>신고</th>
			</tr>
		</thead>
		<tbody>
		
			
		<% 
			for(Member m : list){
		%>
			<%--조회된 회원이 있는 경우 --%>	
				<tr>
					<td><%= m.getMemberId() %></td>
					<td><%=m.getEmail() != null ? m.getEmail() : "" %></td>
					<td><%=m.getMemberRole() %></td>
					<td><%=m.getPhone() %></td>
					<td><%=m.getEnrollDate() %></td>
					<td><%=m.getOutCount()  %></td>
				</tr>
		<% 		
		   } 
		%>
		</tbody>
	</table>
</div>
	<div id="pageBar">
		<%= request.getAttribute("pageBar") %>
	</div>
	
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>