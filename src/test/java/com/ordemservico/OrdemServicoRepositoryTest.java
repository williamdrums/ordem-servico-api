//package com.ordemservico;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.ordemservico.domain.model.Cliente;
//import com.ordemservico.domain.repository.ClienteRepository;
//import com.ordemservico.domain.repository.OrdemServicoRepository;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class OrdemServicoRepositoryTest {
//
//	@Autowired
//	private ClienteRepository clienteRepository;
//
////	@Autowired
////	private OrdemServicoRepository ordemServicoRepository;
//
//
////	public Cliente montarDadosDeCliente() {
////		Cliente cliente = new Cliente(null,"Jão da Silva","679992797491","williandrums@gmail.com");
////		clienteRepository.save(cliente);
////		return cliente;
////	}
//
//	@Test
//	public void inserirDadosDeClientes(){
//		Cliente cliente = new Cliente(null,"Jão da Silva","679992797491","jj@gmail.com");
//		clienteRepository.save(cliente);
//		Integer countClientes = clienteRepository.findAll().size();
//
//		assertNotNull(countClientes);
//		assertEquals(1,countClientes);
//	}
//}
