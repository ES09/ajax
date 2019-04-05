<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paging Example</title>
</head>
<body>
<script>
	function changePageCount(obj){
		location.href="/addr/list?page=${page}&pageCount=" + obj.value;
	}
	function search(){
		var ad_dong = document.querySelector('#ad_dong').value;
		location.href="/addr/list?pageCount=${pageCount}&ad_dong=" + ad_dong;
	}
</script>
<label for="ad_dong">읍·면·동 : </label><input type="text" name="ad_dong" id="ad_dong">
<button onclick="search()">검색</button>
<select name="pageCount" onchange="changePageCount(this)">
	<option value="10" <c:if test="${pageCount==10}">selected</c:if> >10개씩 보기</option>
	<option value="20" <c:if test="${pageCount==20}">selected</c:if> >20개씩 보기</option>
	<option value="30" <c:if test="${pageCount==30}">selected</c:if> >30개씩 보기</option>
	<option value="40" <c:if test="${pageCount==40}">selected</c:if> >40개씩 보기</option>
	<option value="50" <c:if test="${pageCount==50}">selected</c:if> >50개씩 보기</option>
</select>

<table border="1">
	<tr>
		<th>번호</th>
		<th>시도</th>
		<th>구·군</th>
		<th>동</th>
		<th>리</th>
		<th>번지</th>
		<th>호</th>
	</tr>
	<c:forEach items="${list}" var="addr">
	<tr>
		<td>${addr.ad_num}</td>
		<td>${addr.ad_sido}</td>
		<td>${addr.ad_gugun}</td>
		<td>${addr.ad_dong}</td>
		<td>${addr.ad_lee}</td>
		<td>${addr.ad_bunji}</td>
		<td>${addr.ad_ho}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="7" align="center">
		<c:if test="${page!=1}">
			<a href="/addr/list?page=${1}&pageCount=${pageCount}">◀</a>
		</c:if>
		<c:if test="${page>10}">
			<a href="/addr/list?page=${page-10}&pageCount=${pageCount}">◁</a>
		</c:if>
		<c:forEach var="p" begin="${fBlock}" end="${lBLock}">
			<c:if test="${p==page}">
				<b>[${p}]</b>
			</c:if>
			<c:if test="${p!=page}">
				<a href="/addr/list?page=${p}&pageCount=${pageCount}">[${p}]</a>
			</c:if>			
		</c:forEach> 
			<c:if test="${(totalPageCnt-10)>=page}">
				<a href="/addr/list?page=${page+10}&pageCount=${pageCount}">▷</a>
			</c:if>
			<c:if test="${page!=totalPageCnt}">
				<a href="/addr/list?page=${totalPageCnt}&pageCount=${pageCount}">▶</a>
			</c:if>
		</td>				
	</tr>
		
	<tr>
		<td colspan="7" align="right">총 갯수 : ${totalCnt}</td>
	</tr>
	
</table>
</body>
</html>