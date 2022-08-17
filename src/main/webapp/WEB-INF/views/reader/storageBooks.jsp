<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/reader/storageBooks.css">
    <script src="/assets/js/reader/storageBooks.js"></script>
</head>
<body>
    <main>
        <section class="storage_book_list">
            <c:forEach items="${list}" var="item">
                <div class="storage_book_detail">
                <a href="/book/summary?seq=${item.bi_seq}"  class="storage_book_item">
                    <div class="storage_book_img_area" style="background-image: url('/images/book_cover/${item.bc_img_file}');">
                    </div>
                    <p>${item.bi_title}</p>
                </a>
                        <p>${item.wri_name}</p>
                        <p>${item.gr_name}</p>
                        <button class="del_mybook" data-seq="${item.mb_seq}">삭제</button>
                </div>
                </c:forEach>
        </section>
    </main>
</body>
</html>