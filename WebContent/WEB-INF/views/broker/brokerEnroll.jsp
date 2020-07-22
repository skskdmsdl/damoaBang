<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="/WEB-INF/views/common/brokerHeader.jsp" %>

	<div>
		<section id=enroll-container>
		<h2>중개인 회원가입</h2>
		
		<form name="brokerEnrollFrm" action="<%= request.getContextPath() %>/broker/enroll" method="post">
			<table>
				<tr>
					<th>등록번호</th>
					<td>
						<input type="text" class="input-field2"  name="br_cp_id" id="br_cp_id" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" class="input-field2" name="password" id="br_password" required><br>
					</td>
				</tr>
				<tr>
					<th>비밀번호확인</th>
					<td>	
						<input type="password" class="input-field2" id="br_password2" required><br>
					</td>
				</tr>  
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" class="input-field2" placeholder="abc@xyz.com" name="email" id="br_email"><br>
					</td>
				</tr>
				<tr>
					<th>상호</th>
					<td>	
					<input type="text" class="input-field2"  name="br_cp_name" id="br_cp_name" required><br>
					</td>
				</tr>
				<tr>
					<th>대표자</th>
					<td>	
					<input type="text" class="input-field2"  name="br_name" id="br_name" required><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>	
						<input type="tel" class="input-field2" placeholder="(-없이)01012345678" name="phone" id="br_phone" maxlength="11" required><br>
					</td>
				</tr>
				<tr>
					<th>중개사 가입일자</th>
					<td>	
					<input type="date" name="joindate" id="joindate" ><br />
					</td>
				</tr> 
				<tr>
					<th>보증보험 유/무 </th>
					<td>
						<input type="radio" name="insurance" id="insurance0" value="T" checked>
						<label for="insurance0">유</label>
						<input type="radio" name="insurance" id="insurance1" value="F">
						<label for="insurance1">무</label>
					</td>
				</tr>
					
			</table>
			<div id="br_enrollBtn">
				<input class="br_enrollBtn" type="submit" value="가입" >
				<input class="br_enrollBtn" type="reset" value="취소">
			</div>
		</form>
		</section>
	</div>

</body>
</html>