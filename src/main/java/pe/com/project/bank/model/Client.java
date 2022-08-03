package pe.com.project.bank.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Client {
    private Long id;
    private String name;
    private String documentType;
    private String document;
    private String cel;
    private String mail;
    private boolean status;
}
