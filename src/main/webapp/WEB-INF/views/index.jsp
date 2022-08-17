<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/index.css">
    <script src="/assets/js/index.js"></script>
</head>
<body>
    ${userSub}
    <main>
        <div class="book_recommend_area">
            <h1>독자 분들이 가장 많이 구독한 도서</h1>
            <div class="swiper main_banner">
                <div class="swiper-wrapper">
                    <c:forEach items="${subBookList}" var="item">
                        <div class="swiper-slide">
                            <div class="book_recommend_item">
                                <div class="subscribe" data-seq="${item.bi_seq}" user-seq="${user.rd_seq}" data-sub="${item.subscribe}">
                                    <c:if test="${item.subscribe == null}">
                                        <i class="far fa-heart"></i>
                                    </c:if>
                                    <c:if test="${item.subscribe != null}">
                                        <i class="fas fa-heart"></i>
                                    </c:if>
                                </div>
                                <a href="/book/summary?seq=${item.bi_seq}">
                                    <div class="book_recommend_img_area" style="background-image: url('/images/book_cover/${item.bc_img_file}');">
                                    </div>
                                    <p>${item.bi_title}</p>
                                </a>
                                <p>${item.wri_name}</p>
                                <p>${item.gr_name}</p>
                                <p>구독 수 : ${item.subcnt}</p>
                                <button class="add_my_storage" data-seq="${item.bi_seq}" user-seq="${user.rd_seq}"><i class="fas fa-plus"></i></button>
                            </div>
                        </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                </div>
            </div>
    </main>
    <script>
        const swiper = new Swiper('.swiper', {
            loop: true,
            autoplay: {
                delay:7000,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev'
            }
        });
    </script>
</body>
</html>