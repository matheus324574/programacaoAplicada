package dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;

import br.edu.unoesc.jdbcOO.dao.EnderecoDAO;
import br.edu.unoesc.jdbcOO.factory.DAOFactory;
import br.edu.unoesc.jdbcOO.model.Cidade;
import br.edu.unoesc.jdbcOO.model.Endereco;

public class EnderecoDAOTest {

	@Test
	public void deveInserirEnderecoNovo() {
		Endereco endereco = new Endereco();
		endereco.setBairro("Centro");
		endereco.setRua("Rua Principal");
		endereco.setCidade(new Cidade(1L));
		EnderecoDAO enderecoDAO = DAOFactory.get().enderecoDAO();
		enderecoDAO.inserir(endereco);
		assertNotNull(endereco.getCodigo());
		enderecoDAO.excluir(endereco.getCodigo());
	}

	@Test
	public void deveAlterarEndereco() {
		Endereco endereco = new Endereco();
		endereco.setBairro("Centro");
		endereco.setRua("Rua Principal");
		endereco.setCidade(new Cidade(1L));
		EnderecoDAO enderecoDAO = DAOFactory.get().enderecoDAO();
		enderecoDAO.inserir(endereco);
		endereco.setBairro("Jardins");
		enderecoDAO.alterar(endereco);
		Endereco enderecoBanco = enderecoDAO.get(endereco.getCodigo());
		assertEquals("Jardins", enderecoBanco.getBairro());
		enderecoDAO.excluir(enderecoBanco.getCodigo());
	}
	
	@Test
	public void deveListaEnderecosPorRua() {
		Endereco endereco = new Endereco();
		endereco.setBairro("Centro");
		endereco.setRua("Rua Principal");
		endereco.setCidade(new Cidade(1L));
		EnderecoDAO enderecoDAO = DAOFactory.get().enderecoDAO();
		enderecoDAO.inserir(endereco);
		Collection<Endereco> ruas = enderecoDAO.getPorRua("Rua Principal");
		assertEquals(1,ruas.size());
		enderecoDAO.excluir(endereco.getCodigo());
	}

}
