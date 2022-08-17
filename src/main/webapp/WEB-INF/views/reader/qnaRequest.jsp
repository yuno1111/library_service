<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/reader/qnaRequest.js"></script>
</head>
<body>
    <main>
        <div class="qna_question_area">
            <div class="qna_question_item">
                <p>문의 제목</p>
                <input type="text" class="qi_title" placeholder="문의 제목을 입력하세요">
                <span>|</span>
                <span>${user.rd_id}</span>
                <p>문의 내용</p>
                <textarea  cols="30" rows="10" class="qi_text"></textarea>
                <button class="qi_add" user-seq="${user.rd_seq}">문의하기</button>
            </div>
        </div>
    </main>
</body>
</html>