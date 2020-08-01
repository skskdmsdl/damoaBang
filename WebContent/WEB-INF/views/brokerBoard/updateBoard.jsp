<%@page import="board.model.vo.RoomImage"%>
<%@page import="board.model.vo.RoomBoard"%>
<%@page import="board.model.vo.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/brokerHeader.jsp" %>
<%
	Room r = (Room)request.getAttribute("r");
	RoomBoard rb = (RoomBoard)request.getAttribute("rb");
	Broker br = (Broker)request.getAttribute("broker");
	List<RoomImage> imgList = (List<RoomImage>)request.getAttribute("imgList");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />

	<div class="roomUpdatePage">
        <h1 >매매 올리기</h1>
        <form action="<%= request.getContextPath() %>/brokerBoard/boardUpdate" method="post" enctype="multipart/form-data">
			<input type="hidden" name="board_num" value="<%=r.getBoard_num() %>" />
		    <section>
		        <input type="text" name="board_title" id="boardTitle" placeholder="제목을 적어주세요" value="<%= rb.getBoard_title()  %>" >
			</section>
			<section id="roomUpSection">
	            <select name="room_val" class="selectOption"  >
	                <option value="OO" <%= r.getRoom_val().equals("OO") ? "selected" : "" %> >오픈형 원룸</option> 
	                <option value="SO" <%= r.getRoom_val().equals("SO") ? "selected" : "" %>>분리형 원룸</option>
	                <option value="OT" <%= r.getRoom_val().equals("OT") ? "selected" : "" %>>오픈형 투룸</option>
	                <option value="ST" <%= r.getRoom_val().equals("ST") ? "selected" : "" %>>분리형 투룸</option>
	            </select>
	
	            <select name="tax_val" class="selectOption" >
	                <option value="M" <%= r.getTax_val().equals("M") ? "selected" : "" %> >월세</option>
	                <option value="Y" <%= r.getTax_val().equals("Y") ? "selected" : "" %>>전세</option>
	            </select>
	
	            <select name="price" class="selectOption" >
	                <option value=45 <%=  r.getPrice()==45? "selected" : "" %>>월 ~ 45</option>
	                <option value=100 <%=  r.getPrice()==100? "selected" : "" %>>45 ~ 100</option>
	            </select>
	
	            <select name="fee" class="selectOption" >
	                <option value=5 <%=  r.getFee()==5? "selected" : "" %>>관리비 ~5만</option>
	                <option value=10 <%=  r.getFee()==10? "selected" : "" %>>5~10만 </option>
	                <option value=20 <%=  r.getFee()==20? "selected" : "" %>>10~20만</option>
	            </select>
	            <select name="size" class="selectOption" >
	                <option value=14 <%=  r.getSize()==14? "selected" : "" %>>14평</option>
	                <option value=24 <%=  r.getSize()==24? "selected" : "" %>>24평</option>
	                <option value=34 <%=  r.getSize()==34? "selected" : "" %>>34평</option>
	            </select>
	            <span>
	            	입주가능일 : <input type="date" name="movedate" id="movedate" value="<%= r.getMovedate() %>">
	            </span>
	            <span>
	            	층 : <input type="text" name="floor" id="floor" value="<%= r.getFloor() %>">
	            </span>
			</section>
			
			<section>
		        <div id="roomUploadFile">
		            <textarea rows="5" cols="40" name="boardContent" id="roomUpdateTxt"  ><%= rb.getContent() %></textarea>
		            <!-- method="post" enctype="multipart/form-data" 사진 여러개를 같이 올릴수 있게 해줌. -->
		            <br />
		            <!-- 위치 검색 -->
		            <div style="width:75%;height:450px; margin-top:40px; border-bottom: 1px solid #999;" >
			            <div id="roomUpSearch">
			            	<p id="roomUpSearchTxt">방 위치 선택</p>
				            <input type="text" name="location" id="localSearch" placeholder="방 위치를 입력해주세요" value="<%= r.getLocation() %>" required>
				            <button onclick="searchPlaces(); return false;" class="room-searchBtn">확인</button>
			            	<br /><br />
			            	<p>주의사항</p><br />
			            	<p>상세한 주소를 검색해주세요!</p>
			            	<p>예시) 서울시 강남구 (X)</p>
			            	<p>예시) 서울특별시 강남구 테헤란로10길 9 (O)</p>
			            	<p>예시) 테헤란로10길 9 (O)</p>
			            </div>
			     		<div id="roomUpMap">
							<div id="map" style="width:400px;height:400px;" ></div>
						</div>
		            </div>
					<div  id="roomUpFile"></div>
				</div>
		    </section>
		        	<input type="submit" value="글등록" id="boardBtn1">
        </form>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f812560fa3200866e643713203eb962f&libraries=services"></script>
<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
//var infowindow = new kakao.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(); 

/* // 키워드로 장소를 검색합니다
searchPlaces(); */

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

function searchPlaces() {
	

    var keyword = document.getElementById('localSearch').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('지역을 입력해주세요!');
        return false;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB); 
geocoder.addressSearch(keyword, function(result, status) {

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
}


 // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}  

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i=0; i<data.length; i++) {
            //displayMarker(data[i]);    
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }       

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    } 
}         

 //지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
 
 // 마커를 생성하고 지도에 표시합니다
 var marker = new kakao.maps.Marker({
     map: map,
     position: new kakao.maps.LatLng(place.y, place.x) 
 });

 // 마커에 클릭이벤트를 등록합니다
 kakao.maps.event.addListener(marker, 'click', function() {
     // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
     infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
     infowindow.open(map, marker);
 });
} 
var idx = 0;
function addFile(){
    $("#roomUpFileInput").last().after("<input type='file' name='f"+ ++idx +"'/><br>");
}
function delFile(){
    //input:file의 최소개수는 1개로 제한.
    if($("input:file").length>1)
        $("#roomUpFile>input").last().remove();
}
//지도 위치 잡아주기
$(document).ready(function(){
	$(".room-searchBtn").trigger("click");
})

</script>


<br />
<br />
<br />
<br />
<br />
<br />
