package com.cursospring.aulamc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.cursospring.aulamc.domain.*;
import com.cursospring.aulamc.domain.enums.*;
import com.cursospring.aulamc.repositories.*;

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
	@Autowired
	private PedidoRepository pedRepo;
	@Autowired
	private PagamentoRepository pgtoRepo;
	@Autowired
	private ItemPedidoRepository itemPRepo;
	
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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, end1); 
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cliente1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		ItemPedido itemP1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00); 
		ItemPedido itemP2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00); 
		ItemPedido itemP3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		p1.getItens().add(itemP1);
		p3.getItens().add(itemP2);
		p2.getItens().add(itemP3);

		ped1.getItens().addAll(Arrays.asList(itemP1, itemP2));
		ped2.getItens().add(itemP3);

	  //Usando o repository para salvar no banco de dados
	  estRepo.saveAll(Arrays.asList(est1, est2));	 
	  cidRepo.saveAll(Arrays.asList(c1, c2,c3));	 
     catRepo.saveAll(Arrays.asList(cat1, cat2));
     proRepo.saveAll(Arrays.asList(p1,p2,p3));
	  cliRepo.save(cliente1);
	  endRepo.saveAll(Arrays.asList(end1, end2));
     pedRepo.saveAll(Arrays.asList(ped1, ped2));
     pgtoRepo.saveAll(Arrays.asList(pagto1, pagto2));
	  itemPRepo.saveAll(Arrays.asList(itemP1, itemP2, itemP3));
	}

}
