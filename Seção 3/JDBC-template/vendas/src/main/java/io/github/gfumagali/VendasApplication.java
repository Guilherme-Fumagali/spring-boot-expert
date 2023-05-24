package io.github.gfumagali;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.gfumagali.domain.entity.Cliente;
import io.github.gfumagali.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {

	@Bean
	CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {
			clientes.save(new Cliente("Guilherme"));
			clientes.save(new Cliente("Júlia"));

			System.out.println("Listando todos os clientes");
			clientes.getAll().forEach(System.out::println);

			clientes.getAll().forEach(c -> {
				c.setNome(c.getNome() + " atualizado");
				clientes.update(c);
			});

			System.out.println("\nBuscando clientes pelo nome");
			clientes.getByName("Jú").forEach(System.out::println);

			System.out.println("\nDeletando todos os clientes");
			clientes.getAll().forEach(c -> {
				clientes.delete(c);
			});
			
			if (clientes.getAll().isEmpty()) 
				System.out.println("Nenhum cliente cadastrado");
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
