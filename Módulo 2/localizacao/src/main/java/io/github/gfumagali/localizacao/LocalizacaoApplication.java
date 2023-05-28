package io.github.gfumagali.localizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.gfumagali.localizacao.domain.repository.CidadeRepository;
import io.github.gfumagali.localizacao.service.CidadeService;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {
	
	@Autowired CidadeRepository cidadeRepository;

	@Autowired CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {
		cidadeRepository.findAll().forEach(System.out::println);
		cidadeService.listarCidadesPorNome("São Paulo").forEach(System.out::println);
		cidadeRepository.findByNomeLike("%Ão%").forEach(System.out::println);
		cidadeRepository.findByNomeStartingWith("São").forEach(System.out::println);
		cidadeRepository.findByNomeEndingWith("Paulo").forEach(System.out::println);
		cidadeRepository.findByNomeContaining("Janei").forEach(System.out::println);

		cidadeRepository.findByHabitantesGreaterThan(10000000L).forEach(System.out::println);
		cidadeService.listarCidadesByNomeSpecs();
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
 