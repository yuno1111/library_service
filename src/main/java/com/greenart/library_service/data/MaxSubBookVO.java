package com.greenart.library_service.data;

import lombok.Data;

@Data
public class MaxSubBookVO {
    private Integer bi_seq;
    private String bi_title;
    private String wri_name;
    private String gr_name;
    private String bc_img_file;
    private Integer subcnt;

    private Integer subscribe;
    private Integer mb_seq;
}
