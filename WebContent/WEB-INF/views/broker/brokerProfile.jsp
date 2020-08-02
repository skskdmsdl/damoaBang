<%@page import="board.model.vo.RoomBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="/WEB-INF/views/common/brokerHeader.jsp" %>
<%
	List<RoomBoard> list = (List<RoomBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	int cnt = (int)request.getAttribute("totalContents");
	Broker broker = (Broker)request.getAttribute("broker");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/brokermember.css" />
<script>
$(function(){
	
	$(".profile-nav").click(function(){
		$(".sub1").css("display", "block");
		$(".mypage-alldiv").css("display", "block");
		$(".mypage-Resultdiv").css("display", "none");
		$(".mypage-ForSaleManage").css("display", "none");
		$(".mypage-profile1_1").css("display", "inline-block");
		$(".mypage-profile1").css("display", "inline-block");
		$(".mypage-profile2_2").css("display", "none");
		$(".mypage-profile2").css("display", "none");
		$(".mypage-profile3_3").css("display", "none");
		$(".mypage-profile3").css("display", "none");
		$(".edit-info1").css("display", "none");
		$(".edit-info2").css("display", "none");
		$(".profile-nav").css("color", "rgb(255, 138, 61)");
		$(".setting-nav").css("color", "#000");
		$(".sub2").css("display", "none");
	});
	$(".setting-nav").click(function(){
		$(".sub1").css("display", "none");
		$(".mypage-alldiv").css("display", "none");
		$(".mypage-Resultdiv").css("display", "none");
		$(".mypage-ForSaleManage").css("display", "none");
		$(".mypage-profile1_1").css("display", "none");
		$(".mypage-profile1").css("display", "none");
		$(".mypage-profile2_2").css("display", "none");
		$(".mypage-profile2").css("display", "none");
		$(".mypage-profile3_3").css("display", "none");
		$(".mypage-profile3").css("display", "none");
		$(".sub2").css("display", "block");
		$(".edit-info1").css("display", "block");
		$(".edit-info2").css("display", "none");
		$(".setting-nav").css("color", "rgb(255, 138, 61)");
		$(".profile-nav").css("color", "#000");
	});
	
		$(".click1").click(function() {
			$(".mypage-alldiv").css("display", "block");
			$(".mypage-Resultdiv").css("display", "none");
			$(".mypage-ForSaleManage").css("display", "none");
			$(".mypage-profile1_1").css("display", "inline-block");
			$(".mypage-profile1").css("display", "inline-block");
			$(".mypage-profile2_2").css("display", "none");
			$(".mypage-profile2").css("display", "none");
			$(".mypage-profile3_3").css("display", "none");
			$(".mypage-profile3").css("display", "none");
		});
		$(".click2").click(function() {
			$(".mypage-alldiv").css("display", "none");
			$(".mypage-Resultdiv").css("display", "block");
			$(".mypage-ForSaleManage").css("display", "none");
			$(".mypage-profile1_1").css("display", "none");
			$(".mypage-profile1").css("display", "none");
			$(".mypage-profile2_2").css("display", "inline-block");
			$(".mypage-profile2").css("display", "inline-block");
			$(".mypage-profile3_3").css("display", "none");
			$(".mypage-profile3").css("display", "none");
		});
		$(".click3").click(function() {
			$(".mypage-alldiv").css("display", "none");
			$(".mypage-Resultdiv").css("display", "none");
			$(".mypage-ForSaleManage").css("display", "block");
			$(".mypage-profile1_1").css("display", "none");
			$(".mypage-profile1").css("display", "none");
			$(".mypage-profile2_2").css("display", "none");
			$(".mypage-profile2").css("display", "none");
			$(".mypage-profile3_3").css("display", "inline-block");
			$(".mypage-profile3").css("display", "inline-block");
		});
		$(".click4").click(function() {
			$(".mypage-alldiv").css("display", "none");
			$(".mypage-Resultdiv").css("display", "none");
			$(".mypage-ForSaleManage").css("display", "none");
			$(".edit-info1").css("display", "block");
			$(".edit-info2").css("display", "none");
		});
		$(".click5").click(function() {
			$(".mypage-alldiv").css("display", "none");
			$(".mypage-Resultdiv").css("display", "none");
			$(".mypage-ForSaleManage").css("display", "none");
			$(".edit-info1").css("display", "none");
			$(".edit-info2").css("display", "block");
		});
});
$(function(){
	$("#passwordUpdateFrm").submit(function(){
		
	var $newPwd = $("#newPassword");
	var $newPwdChk = $("#newPasswordCheck");
	
	if($newPwd.val() != $newPwdChk.val()){
		alert("입력한 비밀번호가 일치하지 않습니다.");
		$newPwd.select();
		return false;
	}
	return true;	
	});
});
//탈퇴
function deleteMember(){
	if(!confirm("정말 탈퇴하시겠습니까?")) return;
	$("[name=deleteMemberFrm]").submit();
}
$("#file").on('change',function(){
	  var fileName = $("#file").val();
	  $(".upload-name").val(fileName);
	});
</script>
	<div class="mypage-nav-bar">
		<a class="profile-nav">프로필&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		<a class="setting-nav">설정</a>
	</div>
	<div class="sub1">
		<a class="click1">모두보기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		<a class="click2">실적보기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="click3">매물관리</a>
	</div>
	<div class="sub2">
		<a class="click4">회원정보수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="click5">비밀번호 변경</a>
	</div>

	<div class="mypage-alldiv">
	<div class="mypage-profile1_1">
	<div class="mypage-profile1">
		<section class="mypage-allView">
			<form method="post" action="<%=request.getContextPath() %>/broker/brokerProfile" enctype="multipart/form-data">
			<table>
				<tr>
					<th></th>
					<td>
						<img id="profileImg" src="<%= request.getContextPath() %>/upload/broker/<%= brokerLoggedIn.getProfile() %>" onerror="this.src='<%= request.getContextPath() %>/upload/member/user.png'" alt="profile">
						
					</td>
				</tr>
				<tr>
					<th></th>
					<td >	
						<input type="text" name = "memberId" class="mypage-id" value="<%=brokerLoggedIn.getBr_name() %>" readonly />
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<div class="filebox"> 
							<label for="file" >사진 업로드</label> 
							<input type="file" id="file" name="profile"> 
								<!-- <input class="upload-name" value="파일선택">  -->
						</div>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>	
						<input type="submit" value="프로필 변경" />
					</td>
				</tr>
			</table>
			</form>	
		</section>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="allview-content" >
	<div>
		<p class="allview-result">나의 실적</p>
		<div class="myResultState-div">
		<p class="myStateResult"><%=broker.getSellcount() %>회의 거래를 성사시켰습니다! </p>
		</div>
	</div>
	<div>
		<p class="myForSalelistallview">내가 올린 매물 <span id="conLink1" class="click3">전체보기</span></p>
		 <% 
		 if(list == null || list.isEmpty()){ %>           
				<div>올린 매물이 없습니다.</div>
		<% 
			} 
		   	else {
				for(RoomBoard b : list){
		%> 
		<a class="noneHover" href="<%= request.getContextPath() %>/brokerBoard/boardView?board_num=<%= b.getBoard_num() %>&br=<%= b.getBr_cp_id() %>">
		<section id="br_itemInfo">
			<p class="SearchRoomForSaleparagraph"><%= b.getBoard_title() %><span class="SearchRoomclickLikeEnrollDate"><%= b.getEnrolldate() %></span></p>
			<p class="approveForSaleRoom"><%= b.getOk().equals("T")?"승인":"미승인" %></p>
		</section>
		</a>
	<% 		}
		
			} 
		%> 
		
	</div>
		<div class="allviewPageBar" id='pageBar'>
			<%= pageBar %>
		</div>
	</div>
	</div>
	</div>
	
	
	<div class="mypage-Resultdiv">
	<div class="mypage-profile2_2">
	<div class="mypage-profile2">
		<section class="mypage-allView">
			<form method="post" action="<%=request.getContextPath() %>/broker/brokerProfile" enctype="multipart/form-data">
			<table>
				<tr>
					<th></th>
					<td>
						<img id="profileImg" src="<%= request.getContextPath() %>/upload/broker/<%= brokerLoggedIn.getProfile() %>" onerror="this.src='<%= request.getContextPath() %>/upload/member/user.png'" alt="profile">
						
					</td>
				</tr>
				<tr>
					<th></th>
					<td >	
						<input type="text" name = "memberId" class="mypage-id" value="<%=brokerLoggedIn.getBr_name() %>" readonly />
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<div class="filebox"> 
							<label for="file" >사진 업로드</label> 
							<input type="file" id="file" name="profile"> 
								<!-- <input class="upload-name" value="파일선택">  -->
						</div>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>	
						<input type="submit" value="프로필 변경" />
					</td>
				</tr>
			</table>
			</form>	
		</section>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="brokerForSales">
			<p class="memberIdparagraph"><span class="countPercentMemberId"><%=brokerLoggedIn.getBr_name() %></span>님은</p>
			<p>누적 <span class="countPercentMemberId"><%=broker.getSellcount() %>회</span>의 거래를 성사시켰습니다!</p> 		
			<p>상위 <span class="countPercent" >7%</span>의 베스트 중개인입니다.</p>
		</div>		
	</div>
	</div>
	
	<div class="mypage-ForSaleManage">
	<div class="mypage-profile3_3">
	<div class="mypage-profile3">
		<section class="mypage-allView">
			<form method="post" action="<%=request.getContextPath() %>/broker/brokerProfile" enctype="multipart/form-data">
			<table>
				<tr>
					<th></th>
					<td>
						<img id="profileImg" src="<%= request.getContextPath() %>/upload/broker/<%= brokerLoggedIn.getProfile() %>" onerror="this.src='<%= request.getContextPath() %>/upload/member/user.png'" alt="profile">
						
					</td>
				</tr>
				<tr>
					<th></th>
					<td >	
						<input type="text" name = "memberId" class="mypage-id" value="<%=brokerLoggedIn.getBr_name() %>" readonly />
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<div class="filebox"> 
							<label for="file" >사진 업로드</label> 
							<input type="file" id="file" name="profile"> 
								<!-- <input class="upload-name" value="파일선택">  -->
						</div>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>	
						<input type="submit" value="프로필 변경" />
					</td>
				</tr>
			</table>
			</form>	
		</section>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <% if(list == null || list.isEmpty()){ %>           
	<div class="myForSaleListViewCount">
		<p class="myForSaleCountViewtotal">전체 : <%= cnt %>개</p>
				<div class="hasnotClickForSaleboard">올린 매물이 없습니다.</div>
		<% 
			} 
		   	else {
				for(RoomBoard b : list){
		%> 
		<section id="my_ForSaleroomImg">
			<a href="<%= request.getContextPath() %>/brokerBoard/boardView?board_num=<%= b.getBoard_num() %>&br=<%= b.getBr_cp_id() %>" class="image featured"><img src="<%=request.getContextPath()%>/upload/board/<%=b.getRenameName() %>" onerror="this.src='<%= request.getContextPath() %>/images/roomImg01.jpg'" alt=""></a>
			<p class="myForSaleRoomImgTitle"><%= b.getBoard_title() %></p>
			<p id="commuTxtId"><%= b.getContent().length()<19 ? b.getContent() : b.getContent().substring(0,20) %></p>
			<p id="commuRoomViews" style="text-align: center;"><%= b.getOk().equals("T")?"승인":"미승인" %></p>
		</section>
	<% 		}
		
			} 
		%> 
		
	</div>
		<div style="clear:both;text-align: center;margin:0 0 50px 210px;" id='pageBar'>
			<%= pageBar %>
		</div>
	</div>
	</div>
	
	
	<div class="edit-info1">
		<section class="edit-infoPage">
			<input type="button" id=deleteMem value="탈퇴하기" onclick="deleteMember();">
			<p>회원정보 수정</p>
			<form action="<%=request.getContextPath() %>/broker/deletebroker" name="deleteMemberFrm" method="POST">
				<input type="hidden" name="memberId" value="" />
			</form>
			<form name="memberUpdateFrm" 
				  action="<%= request.getContextPath() %>/broker/brokerUpdate" method="post">
				<table>
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" name="br_cp_id" id="memberId_" 
								   value="<%= brokerLoggedIn.getBr_cp_id() %>" readonly required>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>	
							<input type="email"  name="email" id="email"
								   value="<%= brokerLoggedIn.getEmail() %>"><br>
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>	
							<input type="tel"  id="userPhone" name="phone"
								   value="<%= brokerLoggedIn.getPhone() %>"><br>
						</td>
					</tr>
				</table>
				<div class="editBtn">
					<input type="submit" value="회원정보수정" >
					<input type="reset" value="초기화">
				
				</div>
			</form>
		</section>
	</div>
	
	<div class="edit-info2" >
		<section class="edit-passwordPage">
			<p>비밀번호 변경</p>
			
			<form name="passwordUpdateFrm" id="passwordUpdateFrm" 
				  action="<%= request.getContextPath() %>/broker/updatePassword" method="post">
				<table>
					<tr>
						<th>현재 비밀번호</th>
						<td>
							<input type="password" name="password" id="password" required>
						</td>
					</tr>
					<tr>
						<th>변경 비밀번호</th>
						<td>	
							<input type="password" name="newPassword" id="newPassword"><br>
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td>	
							<input type="password" id="newPasswordCheck" required><br>
						</td>
					</tr>
				</table>
				<div class="editBtn">
					<input type="hidden" name="br_cp_id" value="<%= brokerLoggedIn.getBr_cp_id() %>" />
					<input type="submit" value="비밀번호 변경"  >
					<input type="reset" value="초기화">
				
				</div>
			</form>
		</section>
	</div>
</body>
</html>