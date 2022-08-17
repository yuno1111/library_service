<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/header.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous"/>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
    <script src="/assets/js/common/util.js"></script>
    <script src="/assets/js/header.js"></script>
    <title>Document</title>
</head>
<body>
    <header>
        <ul>
            <li>
                <a href="/">메인페이지</a>
            </li>
            <c:if test="${user == null}">
                <li>
                    <a href="/join">회원가입</a>
                </li>
                <li>
                    <a href="/login">로그인</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li>
                    ${user.rd_nickname == null ?user.rd_name:user.rd_nickname}(${user.rd_id}) 님
                </li>
                <li>
                    <a href="/logout">로그 아웃</a>
                </li>
                <li>
                    <a href="/mySubscribe">내 구독 목록</a>
                </li>
                <li>
                    <a href="/myStorage">내 보관함</a>
                </li>
                <li>
                    <a href="/myQna">내 문의</a>
                </li>
            </c:if>
            <li>
                <a href="/payment">구독권</a>
            </li>
            <li>
                <a href="/notice/list">공지사항</a>
            </li>
        </ul>
        <div class="add_storage_popup_area" hidden>
            <div class="storage_list_popup">
                <button class="storage_popup_cancel">&times;</button>
                <div class="storage_item_area">
                    <p>보관함 이름</p>
                    <p>보관함 이름</p>
                    <p>보관함 이름</p>
                    <p>보관함 이름</p>
                </div>
            </div>
        </div>
    </header>
</body>
</html>