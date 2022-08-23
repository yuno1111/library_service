const url = new URL(window.location.href);
const urlParam = url.searchParams;
let title = urlParam.get("title");
$(function(){
    getMain();
})

function getMain(){
    $.ajax({
        url:"/api/book/recommend/list?title="+title,
        type:"get",
        success:function(r){
            $(".books_summary_list").html("");
            let tag = '';
            let item = r.list;
            for(let i=0; i<item.length; i++){
                tag +=
                '<div class="books_summary_item">';
                if(item[i].subscribe == null){
                    tag +=
                    '<div class="subscribe" onClick=addSubscribe("'+item[i].bi_seq+'","'+r.user.rd_seq+'","'+item[i].subscribe+'")>'+
                    '<i class="far fa-heart"></i>';
                }
                if(item[i].subscribe != null){
                    tag +=
                    '<div class="subscribe" onClick=addSubscribe("'+item[i].bi_seq+'","'+r.user.rd_seq+'","'+item[i].subscribe+'")>'+
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
            '<button class="add_my_storage" onClick=addStorage("'+item[i].bi_seq+'","'+r.user.rd_seq+'")><i class="fas fa-plus"></i></button>' +
            '</div>' +
            '</div>';
        }
        $(".books_summary_list").append(tag);
        }
    })
}