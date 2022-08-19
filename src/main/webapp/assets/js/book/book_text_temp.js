let book_page = 1;
let book_text = new Array();
let font_size = 17;
let font=0;
let form=true;
let book_scroll =2;
$(function(){
    getBookText(seq,font,17);
    $(".book_text_form").click(function(){
        if(form)
        {
            $(".book_text_form").html("페이지 보기");
            $(".book_content_prev").prop("disabled",true);
            $(".book_content_next").prop("disabled",true);
            getBookTextScroll(seq,font,font_size,book_scroll)
        }
        else{
            $(".book_content_next").prop("disabled",false);
            getBookText(seq,font,font_size);
            $(".book_text_form").html("스크롤 보기");
        }
        form = !form;
    })
    $(".book_content_next").click(function(){
        $(".book_content_prev").prop("disabled",false);
        book_page++;
        $(".current_page").html(book_page);
        getBookText(seq,font,font_size);
    })

    $(".book_content_prev").click(function(){
        $(".book_content_next").prop("disabled",false);
        book_page--;
        $(".current_page").html(book_page);
        if(book_page <= 1){
            book_page = 1;
            $(".book_content_prev").prop("disabled",true);
        }
        getBookText(seq,font,font_size);
    })

    $(".font_next").click(function(){
        $(".font_current_size").html("");
        font_size+=1;
        if(font_size >=30){
            font_size = 30;
        }
        $(".font_current_size").html(font_size);
        font+=-50;
        if(font <= -650){
            font = -650;
        }
        if(form){
            getBookText(seq,font,font_size);
        }
        else{
            getBookTextScroll(seq,font,font_size,book_scroll);
        }
    })
    $(".font_prev").click(function(){
        $(".font_current_size").html("");
        font_size-=1;
        if(font_size <= 12){
            font_size = 12;
        }
        $(".font_current_size").html(font_size);
        font+=50;
        if(font >= 250){
            font = 250;
        }
        if(form){
            getBookText(seq,font,font_size);
        }
        else{
            getBookTextScroll(seq,font,font_size,book_scroll);
        }
    })
})

function getBookText(seq,font,font_size){
    $(".book_text_wrap").html("");
    $(".total_page").html("");
    $.ajax({
        url:"/api/book/text/temp?seq="+seq+"&fontSize="+font,
        type:"get",
        success:function(r){
            // if(r.havePayment == 0 || r.havePayment == null){
            //     alert("구독권이 만료되었거나 로그인 중이 아닙니다.");
            //     location.href="/"; 
            //     return;
            // }

                let tag = '';
                tag = 
                '<div class="book_text_area">'+
                    '<div class="book_text_item">'+r.content[book_page-1]+'</div>';
                '</div>';
                $(".book_text_wrap").append(tag);
                $(".total_page").html(r.content.length-1);
                $(".book_text_item").css("font-size",font_size);
        }
    })
}

function getBookTextScroll(seq,font,font_size,book_scroll){
    $(".book_text_wrap").html("");
    $(".total_page").html("");
    $.ajax({
        url:"/api/book/text/temp?seq="+seq+"&fontSize="+font,
        type:"get",
        success:function(r){
            // if(r.havePayment == 0 || r.havePayment == null){
            //     alert("구독권이 만료되었거나 로그인 중이 아닙니다.");
            //     location.href="/"; 
            //     return;
            // }
            for(let book_scroll=0; book_scroll<r.content.length-1; book_scroll++){
                let tag = '';
                tag = 
                '<div class="book_text_area">'+
                    '<div class="book_text_item">'+r.content[book_scroll]+'</div>';
                '</div>';
                $(".book_text_wrap").append(tag);
                $(".total_page").html(r.content.length-1);
                $(".book_text_item").css("font-size",font_size);
            }
        }
    })
}