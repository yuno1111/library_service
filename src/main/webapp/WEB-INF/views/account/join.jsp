<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/account/join.css">
    <script src="/assets/js/common/util.js"></script>
    <script src="/assets/js/account/join.js"></script>
</head>
<body>
    <main>
        <div class="join_area">
            <h1>회원가입</h1>
                <div class="member_join_box">
                    <table>
                        <tbody>
                            <tr>
                                <td>아이디</td>
                                <td>
                                    <input type="text" id="rd_id" placeholder="아이디(16자 이내)">
                                </td>
                                <td>
                                    <button id="id_dup_chk">중복체크</button>
                                </td>
                            </tr>
                            <tr>
                                <td>비밀번호</td>
                                <td>
                                    <input type="password" id="rd_pwd" placeholder="비밀번호(16자이내)">
                                </td>
                            </tr>
                            <tr>
                                <td>비밀번호 확인</td>
                                <td>
                                    <input type="password" id="rd_pwd_confirm" placeholder="비밀번호 확인">
                                </td>
                            </tr>
                            <tr>
                                <td>이름</td>
                                <td>
                                    <input type="text" id="rd_name" placeholder="이름(50자 이내)">
                                </td>
                            </tr>
                            <tr>
                                <td>닉네임</td>
                                <td>
                                    <input type="text" id="rd_nickname" placeholder="닉네임(16자 이내)">
                                </td>
                            </tr>
                            <tr>
                                <td>성별</td>
                                <td>
                                    <select id="rd_gen">
                                        <option value="1">남자</option>
                                        <option value="2">여자</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>생년월일</td>
                                <td>
                                    <input type="text" id="rd_birth" placeholder="생년월일 (yyyy-MM-dd)" autocomplete="off">
                                </td>
                            </tr>
                            <tr>
                                <td>전화번호</td>
                                <td>
                                    <input type="text" id="rd_phone" placeholder="-를 뺴고 입력하세요.">
                                </td>
                                <td>
                                    <button id="phone_dup_chk">전화인증</button>
                                </td>
                            </tr>
                            <tr>
                                    <form id="profile_img_form">
                                        <input type="file" name="file" id="profile_img"  hidden accept = "image/gif, image/jpeg, image/png">
                                    </form>
                                <button id="profile_img_save" onclick="document.getElementById('profile_img').click()">프로필
                                    이미지 업로드</button>
                            </tr>
                        </tbody>
                    </table>
                    <div class="profile_explain">프로필 이미지</div>
                    <div class="profile_img_area" filename="${rd_profile_img}">
                    </div>
                    <div class="button_area">
                        <button id="join">회원가입
                            <a href="/login"></a>
                        </button>
                        <button id="cancel">들어가기</button>
                    </div>
                </div>
            </div>
    </main>
</body>
</html>