package com.greenart.library_service.data;

import lombok.Data;

@Data
public class RecommendSummaryVO {
    private Integer ar_seq;
    private String ar_title;
    private String bc_img_file;

    private Integer cnt;
}
