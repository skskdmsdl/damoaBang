<%@page import="member.model.service.MemberService"%>
<%@page import="community.model.vo.ComBoardReply"%>
<%@page import="community.model.vo.ComBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="community.model.vo.ComBoard"%>
<%
	ComBoard cb = (ComBoard)request.getAttribute("ComBoard");
	List<ComBoardReply> commentList = (List<ComBoardReply>)request.getAttribute("replylist");
	boolean like = (boolean)request.getAttribute("like");
	int replycount = (int)request.getAttribute("replycount");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community.css" />
<script>
function replyBtn() {	
	<%if( memberLoggedIn != null && !memberLoggedIn.getMemberId().isEmpty()) { %>
		let $content = $("#replycontent");
		if(!/^.{1,}$/.test($content.val()))
			alert("댓글 내용을 작성해 주세요.");
		else
			reply.submit();
	<% }  else {%>
		alert("로그인을 해주세요!");
	<%} %>
}
function replydelete() {
	replydelete.submit();
}

function deleteBoard() {
	deleteBoardFrm.submit();
}
</script>
<div class="nav-bar">
	<a href="<%=request.getContextPath()%>/community/communityQnA" class="community-nav">질문과 답변</a>
</div>

	<form action="<%= request.getContextPath() %>/community/like?comboardNo=<%= cb.getboardnum() %>" method="post">
		<div class="qnaDetail">
			<h3 class="QuestionAndAnswer" >질문과 답변</h3>
			<input type="hidden" name="boardno" value="<%= cb.getboardnum() %>" />
			<% if(memberLoggedIn != null) { %>
			<input type="hidden" name="memberId" value="<%= memberLoggedIn.getMemberId() %>" />
			<% } %>
			<h1 class="QnaDetailTitle"><%=cb.getTitle() %></h1>
			<p id="qnaDetailCon"><%= cb.getContent() %></p>
			<img style="width:500px; margin-top:50px;" src="<%=request.getContextPath()%>/upload/community/<%=cb.getRenamename() %>" onerror="this.src='<%= request.getContextPath() %>/images/roomImg01.jpg'" />
	
			<div class="ProfileIdDateReplyCheck">
				<span class="questions-item__footer__author">
					<time datetime="2019-01-28T23:57:51+09:00" class="QnAContentEnrolldate"><%= cb.getEnrolldate() %></time>
					<span class="QnAContentAlarm" onclick="reportBtn();">신고</span> 
					<span class="questions_itme__footer__views text-gray" onclick="likeBtn();">
						<form name="likeFrm" action="<%= request.getContextPath()%>/community/communityQnADetail" method="POST">
							<input type="hidden" id="likeMemId" name="memberId" value="<%= memberLoggedIn.getMemberId() %>" /> 
							<input type="hidden" id=likeBoardNum name="board_num" value="<%= cb.getboardnum() %>" />
						</form>
						<div class="roomDetail-info2">
							<img class="LikeImgButton" src=<%= like ? "../images/colorHeart.png" : "../images/heart.png" %> id="likeBtn" alt="" />
						</div>
					<span>
					<%if(memberLoggedIn != null && (cb.getMemberid().equals(memberLoggedIn.getMemberId()) || memberLoggedIn.getMemberRole().equals(MemberService.MEMBER_ROLE_ADMIN))) { %>        
		              <span onclick="deleteBoard();" class="delete"> 삭제 </span><% } %>
		             </span>
					</span>
				</span>
			</div>
	
		</div>
	<!-- 작성자 -->
	<div id="qnaDetailProfile">
		<span class="qnaWriterView"> <img class="MemberProfileImg" src="<%=request.getContextPath()%>/images/user.png"> <span class="pnaWriter"><%=cb.getMemberid() %></span>
		</span>
		<p>궁금한 것들 물어보세요!</p>
		<a class="questions-filter__actions__new-question btn btn-priority btn-sm" href="<%=request.getContextPath()%>/community/communityQuestion">질문하러 가기</a>
	</div>


	<!-- 댓글 -->
	<div id="qnaRepleWrap">
		<p class="replyMainTitle" >댓글</p>
		<form name=reply action="<%= request.getContextPath() %>/community/insertreply" method="post">
			<div id="qnaReple">
				<input id="replycontent" type="text" name="replycontent" placeholder="댓글을 입력해주세요" />
				<% if(memberLoggedIn != null && !memberLoggedIn.getMemberId().isEmpty()) { %>
				<input type="hidden" name="writer" value="<%= memberLoggedIn.getMemberId() %>" />
				<% } %>
				<input type="hidden" name="boardno" value="<%= cb.getboardnum() %>" /> <input type="hidden" name="level" value="1" />
				<p id="replybtn" onclick="replyBtn()">등록</p>
			</div>
		</form>
	</div>
	<table id="tbl-comment">
		<% 
			if(commentList != null && !commentList.isEmpty()){ 
				for (ComBoardReply cbr : commentList) {
					if(cbr.getLevel() == 1) {
					//댓글
		%>
		<tr class="level1">
			<td class="replyMemProfileIdDate">
				<sub class="coveredMemberProfileWriterDate">
				<sub class="comment-memberProfile"> <img class="ComentMemberProfileImg" src="<%=request.getContextPath()%>/images/user.png"></sub>
				<sub class="comment-writer"> <%= cbr.getMemberid() %></sub> 
				<sub class="comment-date"> <%= cbr.getEnrolldate() %></sub>
				</sub>
				<br /> 
			</td>
			<td class="replyContentAndDelete">
				<sub class="ReplyMainContent">
				<%= cbr.getContent() %>
							<%if(memberLoggedIn != null && (cbr.getMemberid().equals(memberLoggedIn.getMemberId()) || memberLoggedIn.getMemberRole().equals(MemberService.MEMBER_ROLE_ADMIN))) { %>
							<form class="replyDeleteButtonForm" action = "<%= request.getContextPath()%>/community/replydelete" method = "POST" name="replydelete"> 
							<button class="Replybtn-delete" value="<%= cbr.getBoardnum() %>" onclick = "replydelete();" class="delete">삭제</button>
							<input type="hidden" name="replyno" value=<%=cbr.getRepnum() %> />
							<input type="hidden" name="boardno" value=<%=cbr.getBoardnum() %> />
							</form>
							<% } %>
				</sub>
			</td>
		</tr>
		<% 
					} else {
					//대댓글
		%>
		<tr class="level2">
			<td>
				<sub class="comment-writer"> <%= cbr.getMemberid() %>
				</sub> <sub class="comment-date"> <%= cbr.getEnrolldate() %>
				</sub> <br />
				<%= cbr.getContent() %>
			</td>
			<td>
				<%if(memberLoggedIn != null &&
								(cbr.getMemberid().equals(memberLoggedIn.getMemberId())
								|| memberLoggedIn.getMemberRole().equals(MemberService.MEMBER_ROLE_ADMIN))) 
							{ %>
				<button class="btn-delete" value="<%= cbr.getBoardnum() %>">삭제</button>
				<% } %>
			</td>
		</tr>
		<% 		
					}
				}
			} 
		%>
	</table>

	<!-- 신고하기  -->
	<div id="reportBack"></div>
	<div id="reportWrap">
		<div class="report-wrap">
			<div class="button-wrap">
				<p>신고사유를 선택해주세요</p>
			</div>
			<input type="button" class="closeAlarmBtn" id="closeBtn" value="x" onclick="reportCloseBtn();">

			<form action="<%= request.getContextPath() %>/community/qnadecl" id="login" method="post" class="report-group">
				<input type="hidden" name="boardno" value="<%=cb.getboardnum() %>" /> <input type="hidden" name="memberId" value="<%=cb.getMemberid() %>" /> <input type="radio" name="chk_info" value="offTopic" checked="checked" />주제와 맞지 않음 <br /> <input type="radio" name="chk_info" value="inaccuracy" />정보가 부정확함<br /> <input type="radio" name="chk_info" value="dvertising" />지나친 광고성 게시글<br /> <input type="radio" name="chk_info" value="overlap" />도배 및 중복 게시물<br /> <input type="radio" name="chk_info" value="copyright" />저작권 침해가 우려됨<br /> <input type="radio" name="chk_info" value="abuse" />욕설/비방이 심함<br /> <input type="radio" name="chk_info" value="obscene" />외설적인 게시물<br /> <input type="radio" name="chk_info" value="privacy" />개인정보노출<br /> <input type="submit" class="submit" id="reportSubmit" value="신고하기" />
			</form>
		</div>
	</div>
	<form name="deleteBoardFrm" action="<%=request.getContextPath()%>/community/comboarddelete" method="POST">
			<input type="hidden" name="boardNo" value="<%=cb.getboardnum()%>" />
	</form>

	<script>
function filterBtn(){
	$(".filter-select__list").css('visibility', 'visible');
}
function reportBtn() {
	reportWrap.style.display = "block";
    reportBack.style.display = "block";
}
function reportCloseBtn() {
	reportWrap.style.display = "none";
    reportBack.style.display = "none";
}
//like
$(document).ready(function(){
    $("#likeBtn").click(function(){
		$.ajax({
			url: "<%= request.getContextPath() %>/community/communityQnADetail",
			method: "POST", 
			dataType: "text", //html, text, json, xml 리턴된 데이터에 따라 자동설정됨
			data:  {"memberId": $("#likeMemId").val(),
					"board_num" : $("#likeBoardNum").val()
				}, //사용자 입력값전달
			success: function(data){
				history.go(0);
			},
			error: function(xhr, textStatus, errorThrown){
				alert("인증번호가 일치하지 않습니다.")
				console.log("ajax 요청 실패!");
				console.log(xhr, textStatus, errorThrown);
			}
		});
    });
});

</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>