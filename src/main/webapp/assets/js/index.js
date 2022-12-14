
$(function () {
    getMain();
    
})

function getMain() {
    $.ajax({
        url: "/api/",
        type: "get",
        success: function (r) {
            $(".books_summary_list").html("");
            $(".book_recommend_area").html("");
            let item = r.subBookList;
            let tag = '';
            tag =
            '<h1>가장 많이 구독한 도서</h1>'+
            '<div class="swiper main_banner">' +
            '<div class="swiper-wrapper">';
            for(let i=0; i<item.length; i++){
                tag +=
                '<div class="swiper-slide">' +
                '<div class="book_recommend_item">';
                if(item[i].subscribe == null){
                    tag +=
                    '<div class="subscribe" data-seq="'+item[i].bi_seq+'" user-seq="'+r.user.rd_seq+'" data-sub="'+item[i].subscribe+'">'+
                    '<i class="far fa-heart"></i>';
                }
                if(item[i].subscribe != null){
                    tag +=
                    '<div class="subscribe" data-seq="'+item[i].bi_seq+'" user-seq="'+r.user.rd_seq+'" data-sub="'+item[i].subscribe+'">'+
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
            '<p>구독 수 : '+(item[i].subcnt==null?0:+item[i].subcnt)+'</p>' +
            '<button class="add_my_storage" onClick=addStorage("'+item[i].bi_seq+'","'+r.user.rd_seq+'")><i class="fas fa-plus"></i></button>' +
            '</div>' +
            '</div>';
        }
            tag +=
            '</div>' +
            '<div class="swiper-button-next"></div>'+
            '<div class="swiper-button-prev"></div>'+
            '</div>' ;
            $(".book_recommend_area").append(tag);

            const swiper = new Swiper('.swiper', {
                loop: true,
                autoplay: {
                    delay:7000,
                },
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev'
                }
            });
            let nbook = r.newBookList;
            let new_book = '';
            for(let a=0; a<r.newBookList.length; a++){
                    new_book +=
                    '<div class="book_summary_area">'+
                    '<a href="/books/book/summary?seq='+nbook[a].bi_seq+'" class="books_summary_item">'+
                    '<div class="books_summary_detail">'+
                        '<div class="books_summary_img"'+
                            'style="background-image: url(\'/images/book_cover/'+nbook[a].bc_img_file+'\')">'+
                        '</div>'+
                        '<div class="books_summary_txt">'+
                            '<p class="books_title_name">'+nbook[a].bi_title+'</p>'+
                            '</a>'+
                            '<p class="books_writer_name">'+nbook[a].wri_name+'</p>';
                            if(nbook[a].subscribe == null){
                                new_book +=
                                '<div class="subscribe" data-seq="'+nbook[a].bi_seq+'" user-seq="'+r.user.rd_seq+'" data-sub="'+nbook[a].subscribe+'">'+
                                '<i class="far fa-heart"></i>';
                            }
                            if(nbook[a].subscribe != null){
                                new_book +=
                                '<div class="subscribe" data-seq="'+nbook[a].bi_seq+'" user-seq="'+r.user.rd_seq+'" data-sub="'+nbook[a].subscribe+'">'+
                                '<i class="fas fa-heart"></i>';
                            }
                            new_book +=
                            '</div>'+
                            '<button class="add_my_storage" onClick=addStorage("'+nbook[a].bi_seq+'","'+r.user.rd_seq+'")><i class="fas fa-plus"></i></button>'+
                        '</div>'+
                    '</div>'+
                '</div>';
            }
            $(".books_summary_list").append(new_book);
                
            
            $(".subscribe").click(function () {
                let rd_seq = $(this).attr("user-seq");
                let bi_seq = $(this).attr("data-seq");
                let sub = $(this).attr("data-sub");
                if (rd_seq == '') {
                    alert("로그인 후 이용해주세요");
                    return;
                }
                if (sub == 'null') {
                    $.ajax({
                        url: "/api/account/book_sub?bi_seq=" + bi_seq,
                        type: "put",
                        success: function (r) {
                            alert(r.message);
                            getMain();
                        }
                    })
                } 
                else if(sub != 'null'){
                    $.ajax({
                        url: "/api/account/book_sub/delete?bi_seq=" + bi_seq,
                        type: "delete",
                        success: function (r) {
                            alert(r.message);
                            getMain();
                        }
                    })
                }
            })
        }
    })
}


