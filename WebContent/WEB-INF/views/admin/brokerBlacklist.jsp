<%@page import="broker.model.vo.Broker"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Broker> list = (List<Broker>)request.getAttribute("list");
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
				<th>등록번호</th>
				<th>이메일</th>
				<th>상호</th>
				<th>대표자</th>
				<th>전화번호</th>
				<th>등록날짜</th>
				<th>보험유무</th>
				<th>거래횟수</th>
				<th>가입날짜</th>
				<th>신고</th>
			</tr>
		</thead>
		<tbody>
		
		
			
		<% 
			for(Broker b : list){
		%>
			<%--조회된 회원이 있는 경우 --%>	
				<tr>
					<td><%= b.getBr_cp_id() %></td>
					<td><%=b.getEmail() %></td>
					<td><%=b.getBr_cp_name() %></td>
					<td><%=b.getBr_name() %></td>
					<td><%=b.getPhone() %></td>
					<td><%=b.getJoindate() %></td>
					<td><%=b.getInsurance() %></td>
					<td><%=b.getSellcount() %></td>
					<td><%=b.getEnrolldate() %></td>
					<td><%=b.getOutcount()  %></td>
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