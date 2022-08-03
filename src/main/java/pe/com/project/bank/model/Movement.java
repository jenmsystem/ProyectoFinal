package pe.com.project.bank.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Movement {
    private Long id;
    private Long affiliatePassiveId;
    private Long clientId;
    private String accountType;
    private String movementType;
    private double quantity;
}
