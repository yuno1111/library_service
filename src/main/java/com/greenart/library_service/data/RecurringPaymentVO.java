package com.greenart.library_service.data;


import lombok.Data;

@Data
public class RecurringPaymentVO {
    private Integer rp_seq;
    private String rp_name;
    private Integer rp_period;
    private Integer rp_role;
}
