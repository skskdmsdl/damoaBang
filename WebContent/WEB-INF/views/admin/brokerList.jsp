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
function clear(){
    $("table *").removeAttr("style");
}
$(document).ready(function(){
    $("tbody tr").not(".br_enrollInput").mouseenter(function(){
        clear();
        $(this).css("background", "#ff934c9e");

    });
});
let tdArray = "";
$(document).ready(function(){
    $("tbody tr").not(".br_enrollInput").click(function(){
        clear();
        $(this).css("background", "#ff934c9e");

        let tr = $(this); 
        let td = tr.children();
       
        tdArray = new Array(); // 배열에 값 담기
        td.each(function(i){
            tdArray.push(td.eq(i).text());
        });
        
        //alert(tdArray[0]);
        setTimeout(deleteMember, 300);
    });
}); 
function deleteMember(){
	if(!confirm("이 회원을 블랙리스트로 이동")) return;
	
	$.ajax({
		url: "<%= request.getContextPath() %>/admin/brokerBalcklistUpdate",
		method: "POST", 
		dataType: "text", //html, text, json, xml 리턴된 데이터에 따라 자동설정됨
		data:  {"br_cp_id": tdArray[0]}, //사용자 입력값전달
		success: function(data){
			//요청성공시 호출되는 함수
			console.log(data);
			location.href="<%=request.getContextPath()%>/admin/brokerBlacklist";
		},
		error: function(xhr, textStatus, errorThrown){
			console.log("ajax 요청 실패!");
			console.log(xhr, textStatus, errorThrown);
		}
	});
	
}
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
		<div id="search-memberEnoll">
			<form name="adminBrokerEnrollFrm" action="<%= request.getContextPath() %>/admin/brokerEnroll" method="post">
		    	
	
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
		<tr class="br_enrollInput">
			<th>
				<input type="text" class="input-field2"  name="br_cp_id" id="br_cp_id" placeholder="번호입력" required>
			</th>
			<td>	
				<input type="email" class="input-field2" placeholder="abc@xyz.com" name="email" id="br_email"><br>
			</td>
			<td>	
				<input type="text" class="input-field2" placeholder="우리중개사" name="br_cp_name" id="br_cp_name" required><br>
			</td>
			<td>	
				<input type="text" class="input-field2" placeholder="권정열" name="br_name" id="br_name" required><br>
			</td>
			<td>	
				<input type="tel" class="input-field2" placeholder="(-없이)01012345678" name="phone" id="br_phone" maxlength="11" required><br>
			</td>
			<td>	
				<input type="date" name="joindate" id="joindate" ><br />
			</td>
			<td>	
				<input type="text" name="insurance" id="insurance" placeholder="T or F" ><br />
			</td>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		</tbody>
	</table>
	<input class="sumitBtn" type="submit" value="회원등록" >
		    </form>
		</div>
	</div>
	
	<div id="pageBar">
		<%= request.getAttribute("pageBar") %>
	</div>
	
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
