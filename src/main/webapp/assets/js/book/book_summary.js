$(function () {
    getBookComments();
    makePager();
    
    $(".read_book").click(function(e){
        let seq = $(this).attr("book-seq");
        e.preventDefault();
        $.ajax({
            url:"/api/book/text",
            type:"get",
            success:function(r){
                if(r.havePayment == 0 || r.havePayment == null){
                    alert("구독권이 만료되었거나 로그인 중이 아닙니다.");
                    location.reload(); return;
                }
            }
        })
        location.href="/book/text?seq="+seq;
    })


    $(".user_comment_add").click(function(){
        let user_seq = $(this).attr("user-seq");
        if(user_seq == ''){
            alert("로그인이 필요한 서비스입니다");
            return;
        }
        if(!confirm("댓글을 등록하시겠습니까?")) return;
        let text = $(".comment_form").val();
        if(text == ''){
            alert("내용을 입력해주세요.");
            return;
        }

        let data = {
            bre_bi_seq:$(this).attr("book-seq"),
            bre_rd_seq:user_seq,
            bre_text:text
        }
        $.ajax({
            url:"/api/book/comment_add",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
    $(".mod_comment").click(function(){
        let user_seq = $(this).attr("user-seq");
        if(user_seq == ''){
            alert("로그인이 필요한 서비스입니다");
            return;
        }
        if(!confirm("댓글을 수정하시겠습니까?")) return;
        let text = $(".comment_form").val();
        if(text == ''){
            alert("내용을 입력해주세요.");
            return;
        }

        let data = {
            bre_seq:$(this).attr("data-seq"),
            bre_text:text
        }
        $.ajax({
            url:"/api/book/comment_modify",
            type:"patch",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
    $(".del_comment").click(function(){
        if(!confirm("댓글을 삭제 하시겠습니까?"))return;
        $.ajax({
            url:"/api/book/comment/delete?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })

})

function getBookComments(page) {
    if (page == null) page = 1;
    $(".book_comment_area").html("");
    $.ajax({
        url: "/api/book/comment/list?seq=" + bi_seq + "&page=" + page,
        type: "get",
        success: function (r) {
            if (r.list.length == 0) {
                let tag =
                    '<div class="book_comment_item">' +
                    '<h1>등록된 댓글 및 평점이 없습니다.</h1>' +
                    '</div>'
                $(".book_comment_area").append(tag);
            }
            for (let i = 0; i < r.list.length; i++) {
                let list = r.list[i];
                let user = (list.rd_nickname == null ? list.rd_id : list.rd_nickname) + '(' + list.rd_id + ')';
                let tag =
                    '<div class="book_comment_item">' +
                    '<div class="comment_content">' +
                    '<p class="comment">' + list.bre_text + '</p>' +
                    '<p class="user">' +
                    '<span class="user">' + user + '</span>' +
                    '<span class="sep">|</span>' +
                    '<span class="reg_dt">' + makeDateString(new Date(list.bre_reg_dt)) + '</span>' +
                    '<span class="sep">|</span>' +
                    '<span class="mod_dt">' + makeDateString(new Date(list.bre_mod_dt)) + '</span>' +
                    '</p>' +
                    '</div>' +
                    '</div>';
                    $(".book_comment_area").append(tag);
            }
        }
    })
}

function makePager(){
    $.ajax({
        url:"/api/book/comment/list?seq="+bi_seq,
        type:"get",
        success:function(r){
            $(".book_comment_page_area").html("");
            for(let i = 0; i<r.pageCnt; i++){
                let tag = '<button class="pager">'+(i+1)+'</button>';
                $(".book_comment_page_area").append(tag);
            }

            $(".pager").eq(0).addClass("current");

            $(".pager").click(function(){
                $(".pager").removeClass("current");
                $(this).addClass("current");
                getBookComments($(this).html());
            })
        }
    })
}