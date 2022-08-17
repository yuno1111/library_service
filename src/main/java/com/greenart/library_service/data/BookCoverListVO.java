package com.greenart.library_service.data;

import lombok.Data;

@Data
public class BookCoverListVO {
    private Integer bc_seq;
    private Integer is_poster=0;
    private String book_covers;
    
    private Integer seq;
}
