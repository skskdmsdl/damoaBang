<%@page import="board.model.vo.RoomImage"%>
<%@page import="board.model.vo.RoomBoard"%>
<%@page import="board.model.vo.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/brokerHeader.jsp" %>
<%
	Room room = (Room)request.getAttribute("room");
	RoomBoard roomBoard = (RoomBoard)request.getAttribute("roomBoard");
	Broker br = (Broker)request.getAttribute("broker");
	List<RoomImage> imgList = (List<RoomImage>)request.getAttribute("imgList");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />


	<div>
		<section class="roomDetail-info">
			<div id="roomDetailView1">
				<%= room.getRoom_val().contains("T")?"투룸":"원룸" %><br />
				<span id="roomDetailTxt1"><%= room.getTax_val().equals("M")?"월세":"전세" %> <%= room.getPrice() %></span>만원
			</div>
			<div id="roomDetailView2">
				전용면적<br />
				<span id="roomDetailTxt2"><%= room.getSize() %></span>평
			</div>
			<div id="roomDetailView3">
				관리비<br />
				<span id="roomDetailTxt1"><%= room.getTax_val().equals("M")?"월세":"전세" %> <%= room.getPrice() %></span>만원
			</div>
			<div id="roomDetailView4">
				<p><%= br.getBr_cp_name() %></p>
				<p><%= br.getBr_name() %></p>
			</div>
			<div id="roomDetailView5">
				<form name="dealFrm" action="<%= request.getContextPath() %>/brokerBoard/brokerDeal" method="POST" >
					<input type="hidden" name="br_cp_id" value="<%= br.getBr_cp_id() %>" />
					<input type="hidden" name="board_num" value="<%= room.getBoard_num() %>" />
				</form>
				
					<% if( brokerLoggedIn!=null && br.getBr_cp_id().equals(brokerLoggedIn.getBr_cp_id())){%>
					<input type="button" class="brokerPhoneBtn" value="거래완료" onclick="dealConfirm();">
					<% }else{ %>
					<input type="button" class="brokerPhoneBtn" value="거래완료" onclick="fakeBtn();">
					<% } %>
			</div>
		</section>
	</div>
	<form action="">
		<input type="hidden" name="br_cp_id" value="<%= br.getBr_cp_id() %>" />
		<input type="hidden" name="board_num" value="<%= room.getBoard_num() %>" />
	</form>
			
			<div id="roomDetail-info2">
				<%-- <img src="<%=request.getContextPath()%>/images/heart.png" alt="" />좋아요
				<img src="<%=request.getContextPath()%>/images/alert.png" onclick="roomReportBtn();" alt="" />허위매물 신고 --%>
				<% if( brokerLoggedIn!=null && br.getBr_cp_id().equals(brokerLoggedIn.getBr_cp_id())
					){ %>
				<input type="button" class="modifyBoard" value="수정하기" onclick="location.href='<%= request.getContextPath() %>/brokerBoard/boardUpdate?board_num=<%= roomBoard.getBoard_num() %>&br=<%= br.getBr_cp_id() %>';" /> 
				<input type="button" class="deleteBoard" value="삭제하기" onclick="deleteBoard();"/>
				<% } %>
			</div>
			
	  <!-- 본인 인증 팝업 -->   
	     <div id="certificationWrap"></div> 
	        <div id="certification">
	            <div class="certification-frmWrap">
	                    
	                <input type="button" id="closeBtn" value="x" onclick="roomReportcloseBtn();">
	                
	                <form action="<%= request.getContextPath() %>/board/report" id="cerFrm" method="POST" class="input-group">
		                <h3>허위매물 신고</h3>

		                <div class="social-icons">
		                    <img src="<%= request.getContextPath() %>/images/alert.png" alt="google">
		                </div>   
		                <p>허위매물을 신고해주세요!</p>
		                <p>허위매물 여부를 판정하여 목록에서 삭제등의 조치가 진행됩니다.</p>
		                
		                <p>본인인증을 진행해주세요.</p>
		                <p>허위매물 신고는 본인인증 후 작성하실 수 있습니다.</p>
		                <p id="cerTitle">이메일 인증</p>
		                <input type="hidden" name="br_cp_id" id="cerHidden" value="<%= br.getBr_cp_id() %>" />
		                <input type="hidden" name="board_num" id="cerHidden2" value="<%= roomBoard.getBoard_num() %>" />
		                <input type="email" id="cerEmail" name="cerEmail" class="cer-field" placeholder="abc@naver.com" required>
	                    <input type="button" class="sendMail" value="전송" />
	                    <br />
	                    <input type="text" id="cerNumber" name="cerNumber" class="cer-field" placeholder="인증번호를 입력해주세요" required>
	                    <input type="submit" class="submit" id="cerSubmit" value="인증" />
	                </form>
	            </div>
	        </div>  
	        
	    <form name="deleteBoardFrm" action="<%= request.getContextPath() %>/board/boardDelete" method="POST">
				<input type="hidden" name="board_num" value="<%= room.getBoard_num() %>" />
				<input type="hidden" name="rname" value="<%= roomBoard.getRenameName()%>" />
		</form>	
	<div class="roomDetail-info3">	
   		<section id="detail-infoTitle">
   			<p class="detail-info1"><%= roomBoard.getOk().equals("F")?"비 확인 매물":"확인 매물" %> <%= roomBoard.getEnrolldate() %></p>
   			<p><%= roomBoard.getOk().equals("F")?"관리자의 매매등록 승인처리가 필요합니다":"확인된 매물입니다" %></p>
   		</section>
   		<ul id="detail-list">
   			<li>
   				<p>해당층/건물층</p>
   				<div><%=room.getFloor() %></div>
   			</li>
   			<li>
   				<p>전용/공급면적</p>
   				<div><%= room.getSize() %>평</div>
   			</li>
   			<li>
   				<p>난방종류</p>
   				<div>개별난방</div>
   			</li>
   			<li>
   				<p>빌트인</p>
   				<div>빌트인/주방</div>
   			</li>
   			<li>
   				<p>엘리베이터</p>
   				<div>없음</div>
   			</li>
   			<li>
   				<p>반려동물</p>
   				<div>가능</div>
   			</li>
   			<li>
   				<p>베란다/발코니</p>
   				<div>있음</div>
   			</li>
   			<li>
   				<p>전세자금대출</p>
   				<div>불가능</div>
   			</li>
   			<li>
   				<p>냉장고</p>
   				<div>있음</div>
   			</li>
   			<li>
   				<p>세탁기</p>
   				<div>있음</div>
   			</li>
   			<li>
   				<p>에어컨</p>
   				<div>있음</div>
   			</li>
   			<li>
   				<p>기타</p>
   				<div></div>
   			</li>
   		</ul>
   	</div>
   
	<div class="roomDetail-info4">
		<div id="roomImg-number"><p>매물번호 : <%= room.getRoom_num() %></p></div>
		 <%
   	if(imgList != null ){ 
   		for(RoomImage ri : imgList){   %>
		<div >
   			<img class="roomDetailImg01" src="<%=request.getContextPath()%>/upload/board/<%=ri.getRenameName()%>"  alt="" onerror="this.src='<%= request.getContextPath() %>/images/roomImg01.jpg'" />
		</div>
   			
   <%		}
   	}
   
   %>
    </div>
		
<section class="space" style="width: 100%;clear:both;height: 80px;"></section>
	<div class="roomDetail-info5">
		<p><%= roomBoard.getBoard_title() %> </p>
		<div id="roomDetail-info5-content">
			<%= roomBoard.getContent() %>
		</div>
	</div>
	<hr />
	<div class="roomDetail-info6">
		<p class="map-title">위치 및 주변시설</p>
		<input type="text" id="roomLocation" value="<%=room.getLocation() %>" readonly/>
		<div id="roomMap" style="width:800px;height:500px;"></div>
	</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f812560fa3200866e643713203eb962f&libraries=services"></script>	
<script>
var mapContainer = document.getElementById('roomMap'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//주소로 좌표를 검색합니다
geocoder.addressSearch('<%=room.getLocation() %>', function(result, status) {

// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });


    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
} 
});

function brokerPhone() {
	connectWrap.style.display = "block";
	connectBack.style.display = "block";
}
function brokerCloseBtn() {
	connectWrap.style.display = "none";
	connectBack.style.display = "none";
}
function roomReportBtn() {
	certification.style.display = "block";
	certificationWrap.style.display = "block";
}
function roomReportcloseBtn() {
	certification.style.display = "none";
	certificationWrap.style.display = "none";
}
function deleteBoard(){
	   if(!confirm("정말 삭제하시겠습니까?")) return;
	   
	   $("[name=deleteBoardFrm]").submit();   
	}
function dealConfirm(){
		if(!confirm("거래를 완료하시겠습니까?")) return;
		if(!confirm("게시글 삭제와 함께 거래가 완료됩니다!")) return;
		$("[name=dealFrm]").submit();  
	}
function fakeBtn(){
		alert('본인이 올린글만 거래완료가 가능합니다');
	}

</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>
