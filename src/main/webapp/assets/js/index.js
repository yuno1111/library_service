// let sub = false;
let subscribe = '';
// const swiper = new Swiper('.swiper', {
//     loop: true,
//     autoplay: {
//         delay:7000,
//     },
//     navigation: {
//         nextEl: '.swiper-button-next',
//         prevEl: '.swiper-button-prev'
//     }
// });
$(function () {
    getMain();
    
})

function getMain() {
    $.ajax({
        url: "/api/",
        type: "get",
        success: function (r) {
            $(".book_recommend_area").html("");
            let item = r.subBookList;
            let tag = '';
            tag =
            '<div class="swiper main_banner">' +
            '<div class="swiper-wrapper">';
            for(let i=0; i<item.length; i++){
                tag +=
                '<div class="swiper-slide">' +
                '<div class="book_recommend_item">';
                if(item.subscribe == null){
                    tag +=
                    '<div class="subscribe" data-seq="'+item[i].bi_seq+'" user-seq="'+r.user.rd_seq+'" data-sub="'+item[i].subscribe+'">' ;
                    '<i class="far fa-heart"></i>';
                }
                if(item.subscribe != null){
                    tag +=
                    '<div class="subscribe" data-seq="'+item[i].bi_seq+'" user-seq="'+r.user.rd_seq+'" data-sub="'+item[i].subscribe+'">' ;
                    '<i class="fas fa-heart"></i>';
                }
                tag +=
                '</div>' +
                '<a href="/book/summary?seq='+item[i].bi_seq+'">' +
                '<div class="book_recommend_img_area" style="background-image: url(\'/images/book_cover/'+item[i].bc_img_file+'\')">' +
            '</div>' +
            '<p>'+item[i].bi_title+'</p>' +
            '</a>' +
            '<p>'+item[i].wri_name+'</p>' +
            '<p>'+item[i].gr_name+'</p>' +
            '<p>구독 수 : '+item[i].subcnt+'</p>' +
            '<button class="add_my_storage" onClick=addStorage("'+item[i].bi_seq+'","'+r.user.rd_seq+'")><i class="fas fa-plus"></i></button>' +
            '</div>' +
            '</div>';
        }
            tag +=
            '</div>' +
            '<div class="swiper-button-next"></div>'+
            '<div class="swiper-button-prev"></div>'+
            '</div>' +
            '</div>' ;
            
            
            $(".book_recommend_area").append(tag);

            $(".subscribe").click(function () {
                let rd_seq = $(this).attr("user-seq");
                let bi_seq = $(this).attr("data-seq");
                let sub = $(this).attr("data-sub");
                if (rd_seq == '') {
                    alert("로그인 후 이용해주세요");
                    return;
                }
                if (sub == '') {
                    $(this).html("")
                    subscribe = '<i class="fas fa-heart"></i>'
                    $(this).html(subscribe);
                    $.ajax({
                        url: "/api/account/book_sub?bi_seq=" + bi_seq,
                        type: "put",
                        success: function (r) {
                            alert(r.message);
                            getMain()
                        }
                    })
        
                } else {
                    $(this).html("")
                    subscribe = '<i class="far fa-heart"></i>'
                    $(this).html(subscribe);
                    $.ajax({
                        url: "/api/account/book_sub/delete?bi_seq=" + bi_seq,
                        type: "delete",
                        success: function (r) {
                            alert(r.message);
                            getMain()
                        }
                    })
                }
            })
        }
    })
}
