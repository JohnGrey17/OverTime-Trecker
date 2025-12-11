package com.example.OvertimeTracker.dto.bonus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
public class BonusResponseDto {

    private Long id;

    private LocalDate date;

    private String reason;

    private BigDecimal sum;


}
