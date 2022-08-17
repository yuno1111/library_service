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
        <a href="/qna/request">문의하기</a>
        <div class="qna_list_wrap">
            <table>
                <tbody>
                    <c:forEach items="${list}" var="item">
                        <tr>
                            <td>${item.qi_seq}</td>
                            <td>
                                <a href="/myQna/qna_detail?seq=${item.qi_seq}">${item.qi_title}</a>
                            </td>
                            <td>
                                <c:if test="${item.qi_check == 0}">답변대기</c:if>
                                <c:if test="${item.qi_check == 1}">답변등록</c:if>
                            </td>
                            <td>
                                <fmt:formatDate value="${item.qi_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="pager_area">
            <c:forEach begin="1" end="${pageCnt}" var="i">
                <a href="/myQna/?page=${i}" class="pager">${i}</a>
            </c:forEach>
        </div>
    </main>
</body>
</html>