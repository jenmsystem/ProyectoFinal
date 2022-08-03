package pe.com.project.bank.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Sale {
    private Long id;
    private Long idUser;
    private double amount;
    private String payMode;
    private double rate;
    private Long idTransaction;
}
