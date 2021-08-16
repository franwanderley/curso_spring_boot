package com.cursospring.aulamc;

import java.util.Arrays;

import com.cursospring.aulamc.domain.Categoria;
import com.cursospring.aulamc.domain.Cidade;
import com.cursospring.aulamc.domain.Cliente;
import com.cursospring.aulamc.domain.Endereco;
import com.cursospring.aulamc.domain.Estado;
import com.cursospring.aulamc.domain.Produto;
import com.cursospring.aulamc.domain.enums.TipoCliente;
import com.cursospring.aulamc.repositories.CategoriaRepository;
import com.cursospring.aulamc.repositories.CidadeRepository;
import com.cursospring.aulamc.repositories.ClienteRepository;
import com.cursospring.aulamc.repositories.EnderecoRepository;
import com.cursospring.aulamc.repositories.EstadoRepository;
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
	@Autowired
	private EstadoRepository estRepo;
	@Autowired
	private CidadeRepository cidRepo;
	@Autowired
	private ClienteRepository cliRepo;
	@Autowired
	private EnderecoRepository endRepo;
	
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
 
     cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
     cat2.getProdutos().add(p2);
 
     p1.getCategorias().add(cat1);
     p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
     p3.getCategorias().add(cat1);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("996935721", "992133624"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "APTO 303", "Jardim", "38220834", cliente1, c1);
		Endereco end2 = new Endereco(null, "Rua Pintor Lemos", "369", "Rua do 96 até o fim", "Santa Casa", "62010720", cliente1, c2);

	  //Usando o repository para salvar no banco de dados
	  estRepo.saveAll(Arrays.asList(est1, est2));	 
	  cidRepo.saveAll(Arrays.asList(c1, c2,c3));	 
     catRepo.saveAll(Arrays.asList(cat1, cat2));
     proRepo.saveAll(Arrays.asList(p1,p2,p3));
	  cliRepo.save(cliente1);
	  endRepo.saveAll(Arrays.asList(end1, end2));
	}

}
