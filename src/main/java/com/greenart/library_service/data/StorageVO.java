package com.greenart.library_service.data;

import lombok.Data;

@Data
public class StorageVO {
    private Integer sto_seq;
    private Integer sto_rd_seq;
    private String sto_name;
    private Integer sto_open;

    private String img;
    private Integer cnt;
}
