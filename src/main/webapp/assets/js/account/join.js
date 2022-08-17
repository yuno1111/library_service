let dup_check = false;
let phone_check = false;
$(function(){
    $("#rd_birth").datepicker({
        dateFormat:"yy-mm-dd",
        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        monthNamesShort:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        dayNames:["일","월","화","수","목","금","토"],
        dayNamesShort:["일","월","화","수","목","금","토"],
        dayNamesrdn:["일","월","화","수","목","금","토"],
        yearSuffix:"년",
        showMonthAfterYear: true,
        changeYear:true,
        changeMonth:true,
    });

    $("#profile_img").change(function () {
        let form = $("#profile_img_form");
        let formData = new FormData(form[0]);
        if ($(this).val() == '' || $(this).val() == null) return;

        $.ajax({
            url: "/images/upload/profile",
            type: "put",
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                alert(result.message);
                if (!result.status) {
                    alert(result.message);
                    return;
                }
                let split = (result.file).split("\\");
                split = split[split.length - 1].split(".");
                let origin_file = split[0] + "." + split[1];
                let tag =
                    '<div class="profile_imgs"  filename="'+origin_file+'" style="background-image:url(/images/profile/'+origin_file+')">'+
                    '<button onclick=deleteImg("'+origin_file+'")>&times;</button>'+
                '</div>';
                $(".profile_img_area").append(tag);
                $("#profile_img_save").hide();
            }
        })
    })

    $("#rd_id").keyup(function(){
        dup_check = false;
    })
    $("#id_dup_chk").click(function(){
        if(isEmpty($("#rd_id").val())) {
            alert("아이디를 올바르게 입력해주세요.");
            return;
        }        

        $.ajax({
            url:"/api/account/id_chk?id="+$("#rd_id").val(),
            type:"get",
            success:function(duplicate) {
                if(!duplicate) {
                    dup_check = true;
                    alert($("#rd_id").val()+"은(는) 사용할 수 있습니다.")
                }
                else {
                    alert($("#rd_id").val()+"은(는) 사용중인 아이디 입니다.")
                }
            }
        })
    });
    
    $("#phone_dup_chk").click(function(){
        if(isEmpty($("#rd_phone").val())) {
            alert("전화번호를 올바르게 입력해주세요.");
            return;
        }        

        $.ajax({
            url:"/api/account/phone_chk?phone_no="+$("#rd_phone").val(),
            type:"get",
            success:function(duplicate) {
                if(!duplicate) {
                    phone_check = true;
                    alert($("#rd_phone").val()+"은(는) 사용할 수 있습니다.")
                }
                else {
                    alert($("#rd_phone").val()+"은(는) 사용중인 전화번호 입니다.")
                }
            }
        })
    });

    $("#join").click(function(){      
        if(!dup_check) {
            alert("아이디 중복체크를 해주세요.");
            return;
        }

        if(!phone_check) {
            alert("전화번호 중복체크를 해주세요.");
            return;
        }

        if(!confirm("회원가입하시겠습니까?")) return;
        
        if(isEmpty($("#rd_id").val())) {alert("아이디를 올바르게 입력해주세요."); return;}
        if(isEmpty($("#rd_pwd").val())) {alert("비밀번호를 올바르게 입력해주세요."); return;}
        if(isEmpty($("#rd_name").val())) {alert("이름을 올바르게 입력해주세요."); return;}
        if(!dateValidate($("#rd_birth").val())) {alert("생년월일을 올바르게 입력해주세요."); return;}
        if(isEmpty($("#rd_phone").val())) {alert("전화번호를 올바르게 입력해주세요."); return;}

        if($("#rd_pwd").val() != $("#rd_pwd_confirm").val()) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }
        if(!nameValidate($("#rd_name").val())) {
            alert("이름을 올바르게 입력해주세요.");
            return;
        }

        if(!phoneNumberValidate($("#rd_phone").val())) {
            alert("전화번호를 올바르게 입력해주세요.");
            return;
        }


        let data = {
            "rd_id":$("#rd_id").val(),
            "rd_pwd":$("#rd_pwd").val(),
            "rd_name":$("#rd_name").val(),
            "rd_nickname":$("#rd_nickname").val(),
            "rd_birth":$("#rd_birth").val()+"T00:00:00+09:00",
            "rd_gen":$("#rd_gen option:selected").val(),
            "rd_phone":$("#rd_phone").val(),
            "rd_profile":$(".profile_imgs").attr("filename"),
            "rd_status":1
        }

        $.ajax({
            url:"/api/account/join",
            type: "put",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(result) {
                alert(result.message)
                location.href="/"
            },
            error:function(err) {
                alert(err.responseJSON.message);
            }
        })
    })
})