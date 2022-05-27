<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
<script src="../ckeditor/ckeditor.js"></script>
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<jsp:include page="../inc/side.jsp"></jsp:include>
<!-- 왼쪽메뉴 -->
<!-- 게시판 -->
<%
//세션값 가져오기
String id=(String)session.getAttribute("id");
// 세션값이 없으면 (id null 이면 ) login.jsp 이동
if(id==null){
	response.sendRedirect("../member/login.jsp");
}
// update.jsp?num=1
// request에 num파라미터값 가져오기
int num=Integer.parseInt(request.getParameter("num"));
// BoardDAO 객체생성
BoardDAO boardDAO=new BoardDAO();
// BoardDTO boardDTO = getBoard(num) 메서드 호출
BoardDTO boardDTO=boardDAO.getBoard(num);
%>
<article>
<h1>Notice Update</h1>
<form action="updatePro.jsp" method="post">
<input type="hidden" name="num" value="<%=num%>">
<table id="notice">
<tr><td>작성자</td><td><input type="text" name="name" value="<%=id %>" readonly></td></tr>
<tr><td>제목</td><td><input type="text" name="subject" value="<%=boardDTO.getSubject()%>"></td></tr>
<tr><td>내용</td>
    <td><textarea name="content" id="editor1" rows="10" cols="10" style="width: 90%; height: 500px;"><%=boardDTO.getContent() %></textarea>
    <script>
                CKEDITOR.replace( 'editor1' );
    </script>
	</td></tr>
</table>

<div id="table_search">
<input type="submit" value="글수정" class="btn"/>
</div>

</form>
<div class="clear"></div>

</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"></jsp:include>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>