let swit = true;
$(function(){
    $(".storage_popup_open").click(function(){$(".add_storage_popup").show()});
    $(".storage_popup_cancel").click(function(){$(".add_storage_popup").hide()});

    $(".add_storage").click(function(){
        let data = {
            sto_rd_seq:$(this).attr("user-seq"),
            sto_name:$(".sto_name_add").val(),
            sto_open:$(".sto_open_add option:selected").val()
        }

        $.ajax({
            url:"/api/reader/storage",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })

    $(".storage_del").click(function(){
        if(!confirm("보관함을 삭제할까요?")) return;

        $.ajax({
            url:"/api/reader/storage?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })

    $(".sto_open").change(function(){
        let seq = $(this).attr("data-seq");
        let open = $(".sto_open option:selected").val();
        let open_txt = $(".sto_open option:selected").html();
        
        $.ajax({
            url:"/api/reader/storage?open="+open+"&seq="+seq,
            type:"patch",
            success:function(r){
                alert(open_txt+" 로 변경 되었습니다");
            }
        })
    })

    $(".change_storage_name").click(function(){$(".storage_form_popup_area").show();
            let seq = $(this).attr("data-seq");
            $(".save_sto_name").click(function(){
                $.ajax({
                    url:"/api/reader/storage_name/modify?name="+$(".sto_name_mod").val()+"&seq="+seq,
                    type:"patch",
                    success:function(r){
                        alert(r.message);
                        location.reload();
                    }
                })
            })
        })
    $(".cancel_storage_form").click(function(){$(".storage_form_popup_area").hide();})

})