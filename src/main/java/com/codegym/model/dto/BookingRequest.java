package com.codegym.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookingRequest {
    private Long customerId;
    private Date checkinDate;
    private Date checkoutDate;
    private List<Long> roomIds;
}
