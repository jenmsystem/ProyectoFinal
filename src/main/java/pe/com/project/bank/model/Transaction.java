package pe.com.project.bank.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Transaction {
    private Long id;
    private double amount;
    private String  accountNumber;
}
