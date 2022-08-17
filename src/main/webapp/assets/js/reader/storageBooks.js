$(function(){
    $(".del_mybook").click(function(){
        if(!confirm("삭제 하시겠습니까?")) return;

        $.ajax({
            url:"/api/reader/book_delete?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
})