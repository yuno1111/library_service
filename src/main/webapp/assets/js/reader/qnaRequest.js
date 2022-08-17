$(function(){
    $(".qi_add").click(function(){
        if(!confirm("문의를 등록하시겠습니까?"))return;

        let data = {
            qi_title:$(".qi_title").val(),
            qi_rd_seq:$(this).attr("user-seq"),
            qi_text:$(".qi_text").val()
        }

        $.ajax({
            url:"/api/reader/qna_add",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/myQna";
            }
        })
    })
})