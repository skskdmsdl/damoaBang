<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/community.css" />
<script>
function boardValidate(){
	//내용을 작성하지 않은 경우에 대한 유효성 검사하세요.
	//공백만 작성한 경우도 폼이 제출되어서는 안됨.
	let $ctitle = $("[name=ctitle]");
	let $questionContent = $("[name=questionContent]");
	
	if($ctitle.val().trim().length == 0){
		alert("제목을 입력하세요.");
		return false;
	}

	if($questionContent.val().trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}
</script>

	<div class="communityNav-bar">
		<a href="<%=request.getContextPath()%>/community/communityQnA" class="community-nav">질문과 답변</a>
	</div>
	<br />

	<section id="questionSection">
		<form class="QuestionEnrollForm" action="<%=request.getContextPath() %>/community/communityInsert" method="post" enctype="multipart/form-data">
			<div class="questionHeader">
				<h2>질문하기<span class="QuestionWriterSpan"><input type="text" name="questionWriter" class="questionWriter" value="<%=memberLoggedIn.getMemberId() %>" readonly/></span></h2>
				<input type="text" name="ctitle" class="ctitle1"placeholder="제목을 적어주세요" required/>
			</div>
			<br />
			<div class="questionContent">
				<textarea class="contextArea" name="questionContent" style= "width: 852px;" rows="20" placeholder="내용을 적어주세요" required></textarea>
			</div>
			<div class="pictureadd">
				<input type="file" name="upfile" class="addPicture"/>
			</div>
			<div class="questionButton">
				<input type="submit" value="질문 저장하기" class="question-btn" onclick="return questionValidate();"/>
			</div>
		</form>
		</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
