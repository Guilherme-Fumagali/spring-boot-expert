package io.github.gfumagali;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.gfumagali.domain.entity.Cliente;
import io.github.gfumagali.domain.entity.Pedido;
import io.github.gfumagali.domain.repository.Clientes;
import io.github.gfumagali.domain.repository.Pedidos;

@SpringBootApplication
public class VendasApplication {

	@Bean
	CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos){
		return args -> {

			Cliente guilherme = new Cliente("Guilherme");
			clientes.save(guilherme);
			clientes.save(new Cliente("Júlia"));

			System.out.println("\nCriando e testando Pedido");
			Pedido p = new Pedido();
			p.setCliente(guilherme);
			p.setData(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			pedidos.save(p);

			Cliente c = clientes.findClienteFetchPedidos(guilherme.getId());
			System.out.println(c);
			System.out.println(c.getPedidos());

			pedidos.findByCliente(c).forEach(System.out::println);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
