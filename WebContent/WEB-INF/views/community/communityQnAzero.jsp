<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="community.model.vo.ComBoard"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community.css" />
<%
	List<ComBoard> comlist = (List<ComBoard>)request.getAttribute("comlist");
	int[] replycount = (int [])request.getAttribute("replycount");
%>

<div class="nav-bar">
	<a href="<%=request.getContextPath()%>/community/communityQnA" class="community-nav">질문과 답변</a>
</div>

<!-- content -->
<div class="commu-info-container">
	<section id="features">
		<div class="container">
			<div class="row aln-center">
				<div class="col-4 col-6-medium col-12-small">
					<!-- Feature -->
					<div class="questions-filter__content container">
						<div class="questions-filter__filters">
							<div class="filter-select" id="questions-filter-sort" data-modal-id="questions-filter-sort-modal">
								<dl class="filter-select__header" role="button">
									<dt class="filter-select__header__name">
										정렬 ▼<span class="icon icon-pointer-angle-down-dark-sm"></span>
									</dt>
									<dd class="filter-select__header__value">최신순</dd>
								</dl>
							</div>
						</div>
						<div class="questions-filter__actions">
							<a class="set-reply btn btn-normal btn-sm" href="<%= request.getContextPath() %>/community/communityQnA"> 모든 질문 보기</a> <a class="questions-filter__actions__new-question btn btn-priority btn-sm" href="<%=request.getContextPath()%>/community/communityQuestion">질문하기</a>
						</div>
					</div>
				</div>

				<%int i = 0; %>
				<% for(ComBoard cb : comlist){ %>
				<div id="info-questionList">
					<!-- 질문 목록1 -->
					<article class="questions-item" onclick="location.href='<%= request.getContextPath() %>/community/communityQnADetail?boardnum=<%= cb.getboardnum() %>'">
						<div class="questions-item__image">
							<div class="image-wrap square">
								<img src="https://image.ohou.se/image/central_crop/bucketplace-v2-development/uploads-cards-snapshots-1548687459_vMyG4GyO.jpeg/320/320">
							</div>
						</div>
						<h1 class="questions-item__title text-heading-5 bold text-black"><%= cb.getTitle() %></h1>
						<p class="questions-item__content" id="text-caption-1"><%= cb.getContent() %></p>
						<div class="ProfileIdDateReplyCheck">
								<span class="Question-ProfileImge"><img class="MemberProfileImg" src="<%=request.getContextPath()%>/images/user.png"></span>
							<div class="QuestionEnrolldateReplyCheck"> 
								<span class="questions-list-MemberId"><%=cb.getMemberid() %></span>
							 	<time datetime="2019-01-28T23:57:51+09:00" class="QuestionEnrolldate"><%= cb.getEnrolldate() %></time> 
							 	<span class="QuestionReply"> 댓글 <span class="questions-item__footer__comments__content active"> <%=replycount[i] %></span></span> 
							</div> 
						</div>
					</article>
				</div>
				<% i++; } %>
				</div>
			</div>
	</section>
</div>
<script>

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>