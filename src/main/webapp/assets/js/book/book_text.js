let book_page = 1;
let book_content_list = new Array();
let form = false;
let font_size = 17;
$(function(){
    $.ajax({
        url:"/api/book/text",
        type:"get",
        success:function(r){
            if(r.havePayment == 0 || r.havePayment == null){
                alert("구독권이 만료되었거나 로그인 중이 아닙니다.");
                location.href="/"; 
                return;
            }
        }
    })

    $(".book_text_item").append(book_content_list[book_page-1].content);
    $(".total").html(book_content_list.length);

    $(".font_prev").click(function(){
        font_size --;
        if(font_size <= 10){
            font_size = 10;
        }
        $(".font_current_size").html(font_size);
        $(".book_text_item").css("font-size",font_size);
    })
    $(".font_next").click(function(){
        font_size ++;
        if(font_size >= 30){
            font_size = 30;
        }
        $(".font_current_size").html(font_size);
        $(".book_text_item").css("font-size",font_size);
    })

    $(".book_content_next").click(function () {
        $(".book_content_prev").prop("disabled", false);
        $(".book_text_item").html("");
        book_page++;
        if(book_page == book_content_list.length)
        {
            $(".book_content_next").prop("disabled", true);
        }
        total_page = book_content_list.length;
        $(".book_text_item").html(book_content_list[book_page-1].content);
        $(".book_page").html(book_page);
        $(".total").html(total_page);
    })
    
    $(".book_content_prev").click(function () {
        book_page--;
        $(".book_content_next").prop("disabled", false);
        if(book_page == 1) $(this).prop("disabled", true);
        $(".book_text_item").html(book_content_list[book_page-1].content);
        $(".book_page").html(book_page);
    })

    $(".book_text_form").click(function(){
        $(".book_text_wrap").html("");
        $(".book_function_area").html("");
        $(".book_text_form").html("");
        if(!form){
            for(let i=0; i<book_content_list.length; i++){
                let tag = '<div class="book_text_area">'+
                            '<div class="book_text_item">'+book_content_list[i].content+'</div>'+
                            '</div>'+
                            '<div class="book_function_area">'+
                            '<p></p>'+
                            '<span class="book_page">'+(i+1)+'</span>'+
                            '<span class="sep">/</span>'+
                            '<span class="total">'+book_content_list.length+'</span>'+
                        '</div>';
                $(".book_text_wrap").append(tag);
            }
            $(".book_text_item").css("font-size",font_size);
            $(".book_text_form").html("페이지 보기");
        }
        else{
            $(".book_text").html("");
            $(".book_function_area").html("");
            $(".book_text_form").html("");
            let tag = 
            '<div class="book_text_wrap">'+
            '<div class="book_text_area">'+
                '<div class="book_text_item"></div>'+
            '</div>'+
        '</div>'+
        '<div class="book_function_area">'+
            '<button class="book_content_prev" disabled>이전</button>'+
            '<button class="book_content_next">다음</button>'+
            '<p></p>'+
            '<span class="book_page">1</span>'+
            '<span class="sep">/</span>'+
            '<span class="total"></span>'+
        '</div>';
        $(".book_text").append(tag);
        $(".book_text_item").css("font-size",font_size);
        $(".book_text_form").html("스크롤 보기");
        $(".book_text_item").append(book_content_list[book_page-1].content);
        $(".total").html(book_content_list.length);
        }
        form = !form;
        $(".book_content_next").click(function () {
            $(".book_content_prev").prop("disabled", false);
            $(".book_text_item").html("");
            book_page++;
            if(book_page == book_content_list.length)
            {
                $(".book_content_next").prop("disabled", true);
            }
            total_page = book_content_list.length;
            $(".book_text_item").html(book_content_list[book_page-1].content);
            $(".book_page").html(book_page);
            $(".total").html(total_page);
        })
        
        $(".book_content_prev").click(function () {
            book_page--;
            $(".book_content_next").prop("disabled", false);
            if(book_page == 1) $(this).prop("disabled", true);
            $(".book_text_item").html(book_content_list[book_page-1].content);
            $(".book_page").html(book_page);
        })
    })
})