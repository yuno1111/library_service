<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/reader/myStorage.css">
    <script src="/assets/js/reader/myStorage.js"></script>
</head>
<body>
    <main>
        <button class="storage_popup_open">보관함 추가</button>
        <div class="add_storage_popup" hidden>
            <button class="storage_popup_cancel">&times;</button>
            <input type="text" class="sto_name_add" placeholder="제목">
            <select class="sto_open_add">
                <option value="0">비공개</option>
                <option value="1">공개</option>
            </select>
            <button class="add_storage" user-seq="${user.rd_seq}">만들기</button>
        </div>
        <section class="storage_detail_list">
            <c:forEach items="${list}" var="item">
                <a href="/storage/books?seq=${item.sto_seq}" class="storage_detail_item">
                    <div class="storage_detail_detail">
                        <div class="storage_detail_img"
                            style="background-image: url('/images/book_cover/${item.img}');">
                        </div>
                    </div>
                </a>
                <div class="storage_detail_txt">
                    <span class="storage_title_name">${item.sto_name}</span>
                    <span class="change_storage_name" data-seq="${item.sto_seq}"><i class="fas fa-edit"></i></span>
                    <p>${item.cnt}권</p>
                    <select class="sto_open" data-seq="${item.sto_seq}">
                        <option value="0" <c:if test="${item.sto_open == 0}">selected</c:if>>비공개</option>
                        <option value="1" <c:if test="${item.sto_open == 1}">selected</c:if>>공개</option>
                    </select>
                </div>
                <button class="storage_del" data-seq="${item.sto_seq}">삭제</button>
            </c:forEach>
        </section>
        <div class="storage_form_popup_area" hidden>
            <button class="cancel_storage_form">&times;</button>
            <input type="text" class="sto_name_mod" placeholder="제목">
            <button class="save_sto_name">저장</button>
        </div>
    </main>
</body>
</html>