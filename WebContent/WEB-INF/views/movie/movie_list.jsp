<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 개봉 리스트</title>
</head>
<body>
<table border="1">
	<tr>
		<th>번호</th>
		<th>영화명</th>
		<th>개봉 연도</th>
		<th>제작 국가</th>
		<th>제작사</th>
		<th>감독</th>
	</tr>
	<c:if test="${fn:length(list)==0}">
	<tr>
		<td colspan="6">등록된 영화 개봉리스트가 없습니다.</td>
	</tr>
	</c:if>
	<c:forEach items="${list}" var="movie">
	<tr style="cursor:pointer" onmouseover="this.style.backgroundColor='skyblue'" 
								onmouseout="this.style.backgroundColor=''"
								onclick="goPage('${movie.mi_num}')">
		<td>${movie.mi_num}</td>
		<td>${movie.mi_name}</td>
		<td>${movie.mi_year}</td>
		<td>${movie.mi_national}</td>
		<td>${movie.mi_vendor}</td>
		<td>${movie.mi_director}</td>
	</tr>
	</c:forEach>
</table>
	<c:if test="${sessionScope.user!=null}">
		<a href="/views/movie/insert">개봉 영화 등록</a>
	</c:if>
<script>
function goPage(miNum){
	location.href="/movie/"+miNum;
}
</script>	
</body>
</html>