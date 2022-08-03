package pe.com.project.bank.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private double purse;
    private Long idClient;
}
