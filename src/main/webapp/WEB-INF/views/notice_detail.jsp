<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <main>
        <div class="notice_mod_wrap">
            <div class="notice_mod_item">
                <p>제목</p>
                <p>${list.ni_title}</p>
                <span>작성자 아이디</span>
                <span>|</span>
                <span>${list.aai_id}</span>
                <span>|</span>
                <span>
                    <fmt:formatDate value="${list.ni_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </span>
                <p>내용</p>
                <p class="ni_text">${list.ni_text}</p>
            </div>
        </div>
    </main>
</body>
</html>