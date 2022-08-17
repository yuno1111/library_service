<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/reader/payment_list.js"></script>
</head>
<body>
    <main>
        <div class="payment_list_wrap">
            <div class="payment_list_item">
                <c:forEach items="${list}" var="item">
                    <p>구독권 이름</p>
                    <p>${item.rp_name}</p>
                    <p>구독권 기간</p>
                    <p>${item.rp_period} 분</p>
                    <button class="buy_payment"
                    buy-dub="${dub}"
                    data-name="${item.rp_name}" user-seq="${user.rd_seq}" 
                    data-seq="${item.rp_seq}" period="${item.rp_period}">구매</button>
                </c:forEach>
            </div>
        </div>
    </main>
</body>
</html>