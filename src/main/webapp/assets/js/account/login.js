$(function(){
    $(".login").click(function(){

        if(isEmpty($(".rd_id").val())) {
            $(".rd_id+.error").css("display", "block");
        }
        else {
            $(".rd_id+.error").css("display", "");
        }
        if(isEmpty($(".rd_pwd").val())) {
            $(".rd_pwd+.error").css("display", "block");
        }
        else {
            $(".rd_pwd+.error").css("display", "");
        }

        let data = {
            rd_id:$(".rd_id").val(),
            rd_pwd:$(".rd_pwd").val(),
        }

        $.ajax({
            url:"/api/account/login",
            type:"post",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                if(r.status){
                    location.href="/";
                }
            }
        })
    })
})