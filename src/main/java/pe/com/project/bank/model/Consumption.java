package pe.com.project.bank.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Consumption {
    private Long id;
    private Long affiliateActiveId;
    private Long clientId;
    private double consumption;
    private int quota;
    private double valueQuota;
}
