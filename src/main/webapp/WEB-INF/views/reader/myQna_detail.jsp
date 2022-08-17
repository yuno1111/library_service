<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/reader/myQna_detail.js"></script>
</head>
<body>
    <main>
        <div class="qna_detail_wrap">
            <div class="qna_user_area">
                <input type="text" class="qna_title" value="${list.qi_title}" disabled></input>
                <span>|</span>
                <span>
                    <fmt:formatDate value="${list.qi_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </span>
                <p></p>
                <textarea cols="30" rows="10" class="qna_question" disabled>${list.qi_text}</textarea>
                <c:if test="${list.an_text == null}">
                    <button class="qna_mod_change">수정하기</button>
                    <button class="qna_mod" data-seq="${list.qi_seq}">저장</button>
                </c:if>
                <button class="qna_del" data-seq="${list.qi_seq}">삭제</button>
            </div>
            <c:if test="${list.an_text != null}">
            <div class="qna_answer_area">
                <span>관리자 아이디</span>
                <span>|</span>
                <span>${list.aai_id}</span>
                <p>문의 답변</p>
                <p>${list.an_text}</p>
            </div>
            </c:if>
            <c:if test="${list.an_text == null}">
                <p>답변 대기중 입니다.</p>
            </c:if>
        </div>
    </main>
</body>
</html>