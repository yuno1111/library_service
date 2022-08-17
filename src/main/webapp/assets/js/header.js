$(function(){
    $(".add_my_storage").click(function(){
        let rd_seq = $(this).attr("user-seq");
        if(rd_seq == ''){
            alert("로그인이 필요한 서비스 입니다.")
            return;
        }
        let bi_seq = $(this).attr("data-seq");
        
        $.ajax({
            url:"/api/reader/storage?bi_seq="+bi_seq,
            type:"get",
            success:function(r){
                if(!(r.status)){
                    alert(r.message);
                    return;
                }
                $(".storage_item_area").html("");
                $(".add_storage_popup_area").show();
                for(let i=0; i<r.list.length; i++){
                    let tag =
                    '<p class="storage_name" data-seq="'+bi_seq+'" sto-name="'+r.list[i].sto_name+'" sto-seq="'+r.list[i].sto_seq+'">'+r.list[i].sto_name+'</p>'
                    $(".storage_item_area").append(tag);
                }
                

            $(".storage_name").click(function(){
                let sto_name =  $(this).attr("sto-name");
                $.ajax({
                    url:"/api/reader/my_book/add?sto_seq="+$(this).attr("sto-seq")+"&bi_seq="+$(this).attr("data-seq"),
                    type:"put",
                    success:function(r){
                        if(!r.status){
                            alert(r.message);
                            return;
                        }
                        alert(sto_name+" 에 도서를 추가하였습니다.");
                        $(".add_storage_popup_area").hide();
                    }
                })
            })
            }
        })
    })
    $(".storage_popup_cancel").click(function(){
        $(".add_storage_popup_area").hide();
    })

    $(".subscribe_cancel").click(function(){
    let bi_seq = $(this).attr("data-seq");
        $.ajax({
            url:"/api/account/book_sub/delete?bi_seq="+bi_seq,
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
})