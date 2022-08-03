package pe.com.project.bank.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Payment {
    private Long id;
    private Long consumerId;
    private Long affiliateActiveId;
    private Long clientId;
    private int quota;
    private double payment;
    private Date date;

}
