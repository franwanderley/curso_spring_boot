package com.cursospring.aulamc;

import java.util.Arrays;

import com.cursospring.aulamc.domain.Categoria;
import com.cursospring.aulamc.domain.Produto;
import com.cursospring.aulamc.repositories.CategoriaRepository;
import com.cursospring.aulamc.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AulamcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository proRepo;
	public static void main(String[] args) {
		SpringApplication.run(AulamcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Salvar na tabela para testar
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		catRepo.saveAll(Arrays.asList(cat1, cat2));
		proRepo.saveAll(Arrays.asList(p1,p2,p3));
     catRepo.flush();
     proRepo.flush();
 
     cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
     cat2.getProdutos().add(p2);
 
     p1.getCategorias().add(cat1);
     p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
     p3.getCategorias().add(cat1);
     catRepo.saveAll(Arrays.asList(cat1, cat2));
     proRepo.saveAll(Arrays.asList(p1,p2,p3));
	}

}
