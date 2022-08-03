package pe.com.project.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.com.project.bank.repository.IClientRepository;

@SpringBootApplication
public class BankApplication {
	@Autowired
	private IClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
