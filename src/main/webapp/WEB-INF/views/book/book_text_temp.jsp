<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/book/book_text_temp.css">
    <script>
        let seq = "${seq}";
    </script>
    <script src="/assets/js/book/book_text_temp.js"></script>
</head>
<body>
    <main>
        <div class="book_main">
            <div class="book_remote_controller">
                <button class="book_text_form">스크롤 보기</button>
                <p>글자 크기 변경</p>
                <button class="font_prev"><i class="fas fa-chevron-left"></i></button>
                <span class="font_current_size">17</span>
                <button class="font_next"><i class="fas fa-chevron-right"></i></button>
                <button class="book_content_prev" disabled>이전</button>
                <span class="current_page">1</span>
                <span>/</span>
                <span class="total_page"></span>
                <button class="book_content_next">다음</button>
            </div>
            <div class="book_text">
            <div class="book_text_wrap">

            </div>
        </div>
        </div>
    </main>
</body>
</html>