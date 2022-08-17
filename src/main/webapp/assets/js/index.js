// let sub = false;
let subscribe = '';
$(function(){
    $(".subscribe").click(function(){
        let rd_seq = $(this).attr("user-seq");
        let bi_seq = $(this).attr("data-seq");
        let sub = $(this).attr("data-sub");
        if(rd_seq ==''){
            alert("로그인 후 이용해주세요");
            return;
        }
        if(sub == ''){
            $(this).html("")
            subscribe = '<i class="fas fa-heart"></i>'
            $(this).html(subscribe);
                $.ajax({
                    url:"/api/account/book_sub?bi_seq="+bi_seq,
                    type:"put",
                    success:function(r){
                        alert(r.message);
                        location.reload();
                    }
                })
            
        }
        else{
            $(this).html("")
            subscribe = '<i class="far fa-heart"></i>'
            $(this).html(subscribe);
                $.ajax({
                    url:"/api/account/book_sub/delete?bi_seq="+bi_seq,
                    type:"delete",
                    success:function(r){
                        alert(r.message);
                        location.reload();
                    }
                })
        }
    })
})