<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/brokerHeader.jsp" %>	
		<section id="search-banner">
			<p class="banner-text">
				<span style="font-weight: bold;">진실의 방</span>
				을
			</p>
			<p class="banner-text">구하고 계신가요?</p>
			<form action="<%= request.getContextPath() %>/board/searchLocationRoom" class="main-search-frm">
				<div class="main-search">
					<input type="text" name="localSearch" id="keyword" placeholder="지역을 입력해 주세요" />
					<button type="submit" class="searchBtn">방 찾기</button>
				</div>
			</form>
		</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>