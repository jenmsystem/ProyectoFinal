package pe.com.project.bank.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AffiliateActive {
    private Long id;
    private Long clientId;
    private String cardNumber;
    private double credit;
    private double balance;
    private Date date;
}
