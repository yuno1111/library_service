$(function(){
    $(".buy_payment").click(function(){
        let dup = $(this).attr("buy-dub");
        let user_seq = $(this).attr("user-seq");
        let rp_name = $(this).attr("data-name");
        if(user_seq == ''){
            if(!confirm("로그인이 필요한 서비스 입니다\n로그인 페이지로 이동하시겠습니까?"))
                return;
                location.href="/login";
            return;
        }
        if(dup != 0){
            alert("이미 구독권을 사용중입니다.");
            return;
        }
        if(!confirm(rp_name+" 을 구매하시겠습니까?")) return;
        
        let data = {
            rpi_rp_seq:$(this).attr("data-seq"),
            rpi_rd_seq:user_seq,
            period:$(this).attr("period")
        }
        console.log(data.rpi_end_dt)
        $.ajax({
            url:"/api/reader/buy_payment",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/";
            }
        })
    })
})