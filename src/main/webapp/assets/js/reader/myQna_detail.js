let form = false;
$(function(){
    let origin_text =  $(".qna_question").val();
    let origin_title = $(".qna_title").val();
    $(".qna_mod_change").click(function(){
        if(!form){
            $(this).html("")
            $(this).html("취소")
            $(".qna_question").prop("disabled",false);
            $(".qna_title").prop("disabled",false);
        }
        else{
            $(this).html("")
            $(this).html("수정하기")
            $(".qna_question").prop("disabled",true);
            $(".qna_title").prop("disabled",true);
            $(".qna_question").val(origin_text);
            $(".qna_title").val(origin_title);
        }
        form = !form;
    })
    $(".qna_mod").click(function(){
        if($(".qna_question").val() == origin_text && $(".qna_title").val() == origin_title){
            return;
        }
        if(!confirm("문의를 수정하시겠습니까?")) return;

        let data = {
            qi_seq:$(this).attr("data-seq"),
            qi_title:$(".qna_title").val(),
            qi_text:$(".qna_question").val()
        }
        $.ajax({
            url:"/api/reader/qna_mod",
            type:"patch",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/myQna";
            }
        })
    })

    $(".qna_del").click(function(){
        if(!confirm("문의내역을 삭제하시겠습니까?")) return;

        $.ajax({
            url:"/api/reader/qna_del?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.href="/myQna";
            }
        })
    })
})