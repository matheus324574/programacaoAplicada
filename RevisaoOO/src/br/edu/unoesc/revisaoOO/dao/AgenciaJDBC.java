package br.edu.unoesc.revisaoOO.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.revisaoOO.modelo.Agencia;

public class AgenciaJDBC implements AgenciaDAO {

	@Override
	public List<Agencia> listar() {
		List<Agencia> agencias = new ArrayList<>();
		try {
			Connection con = ConexaoUtil.getCon();
			// Classe que sabe executar comandos SQL
			Statement stmt = con.createStatement();
			// Comando sql que desejo executar
			String sql = "select * from agencia";
			// Execução do comando e o resultado é
			// armazenado no ResultSet
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Agencia agencia = new Agencia();
				agencia.setCodigo(rs.getLong("codigo"));
				agencia.setNome(rs.getString("nome"));
				agencia.setNumero(rs.getInt("numero"));
				agencias.add(agencia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agencias;
	}

	@Override
	public void inserir(Agencia agencia) {
		try {
			Connection con = ConexaoUtil.getCon();
			String insert = "insert into agencia values(codigo,?,?)";
			PreparedStatement insertStmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, agencia.getNome());
			insertStmt.setInt(2, agencia.getNumero());
			insertStmt.executeUpdate();
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			// popula o código da agência com o código gerado no banco.
			agencia.setCodigo(resultSet.getLong(1));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void alterar(Agencia agencia) {
		try {
			Connection con = ConexaoUtil.getCon();
			String update = "update agencia set nome = ?," + " numero = ? where codigo = ? ";
			PreparedStatement updateStmt = con.prepareStatement(update);
			updateStmt.setString(1, agencia.getNome());
			updateStmt.setInt(2, agencia.getNumero());
			updateStmt.setLong(3, agencia.getCodigo());
			updateStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void excluir(Long codigo) {
		try {
			Connection con = ConexaoUtil.getCon();
			String update = "delete from agencia where codigo = ? ";
			PreparedStatement updateStmt = con.prepareStatement(update);
			updateStmt.setLong(1, codigo);
			updateStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Agencia get(Long codigo) {
		Agencia agencia = null;
		try {
			Connection con = ConexaoUtil.getCon();
			// Classe que sabe executar comandos SQL
			Statement stmt = con.createStatement();
			// Comando sql que desejo executar
			String sql = "select * from agencia where codigo = " + codigo;
			// Execução do comando e o resultado é
			// armazenado no ResultSet
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			agencia = new Agencia();
			agencia.setCodigo(rs.getLong("codigo"));
			agencia.setNome(rs.getString("nome"));
			agencia.setNumero(rs.getInt("numero"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agencia;
	}
}
