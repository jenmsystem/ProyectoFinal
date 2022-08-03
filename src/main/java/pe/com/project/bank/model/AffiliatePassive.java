package pe.com.project.bank.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AffiliatePassive {
    private Long id;
    private Long clientId;
    private String clientType;
    private String accountType;
    private double balance;
    private String accountNumber;
    private String debitCard;
    private String mainAccount;
    private String order;
    private String headLine;
    private String signature;
    private boolean commission;
    private double movementQuantity;
    private Date date;

}
