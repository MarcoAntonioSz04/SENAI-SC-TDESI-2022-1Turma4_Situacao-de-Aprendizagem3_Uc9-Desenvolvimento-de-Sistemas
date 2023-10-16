package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoBanco;
import model.Usuario;

public class UsuarioRepository {
	private Connection conn;

	public UsuarioRepository() {
		conn = ConexaoBanco.getConnection();
	}

	public Usuario insereUsuario(Usuario objeto) throws Exception {
		if (objeto.ehNovo()) {
			String sql = "INSERT INTO cliente(nome, endereco, modalidade) VALUES(?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, objeto.getNome());
			stmt.setString(2, objeto.getEndereco());
			stmt.setString(3, objeto.getModalidade());

			stmt.execute();

			conn.commit();
		} else {
			String sql = "UPDATE cliente SET nome = ?, endereco =?, modalidade = ? WHERE id = " + objeto.getId() + ";";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, objeto.getNome());
			stmt.setString(2, objeto.getEndereco());
			stmt.setString(3, objeto.getModalidade());

			stmt.executeUpdate();

			conn.commit();
		}
		return this.consultarUsuario(objeto.getNome());
	}

	// Consulta Usuario após o Insert e Update
	public Usuario consultarUsuario(String usuario) throws Exception {
		Usuario user01 = new Usuario();

		String sql = "SELECT * FROM cliente WHERE nome = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, usuario);


		ResultSet rst = stmt.executeQuery();

		while (rst.next()) {
			user01.setId(rst.getLong("id"));
			user01.setNome(rst.getString("nome"));
			user01.setEndereco(rst.getString("endereco"));
			user01.setModalidade(rst.getString("modalidade"));
		}

		return user01;
	}

	// Verficiar usuário e senha para Login
	public boolean vericaUsuario(String usuario) throws Exception {
		String sql = "SELECT COUNT(1) > 0 AS EXISTE FROM cliente where nome = '" + usuario + "';";

		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet res = stmt.executeQuery();

		res.next();
		return res.getBoolean("existe");
	}

	// Deletar o Usuário JS
	public void deletarUsuario(String userId) throws Exception {
		String sql = "DELETE FROM cliente WHERE id = ?;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, Long.parseLong(userId));
		stmt.executeUpdate();
		conn.commit();

	}

	public List<Usuario> consultarUsuarioLista(String nome) throws Exception {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		String sql = "SELECT * FROM cliente WHERE nome like ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + nome + "%");
		ResultSet rst = stmt.executeQuery();
		while (rst.next()) {
			Usuario user01 = new Usuario();
			user01.setId(rst.getLong("id"));
			user01.setNome(rst.getString("nome"));
			user01.setEndereco(rst.getString("endereco"));
			user01.setModalidade(rst.getString("modalidade"));

			listaUsuario.add(user01);
		}
		return listaUsuario;
	}

	public Usuario consultarUsuarioID(String id) throws Exception {
		Usuario user01 = new Usuario();
		String sql = "SELECT * FROM cliente WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, Long.parseLong(id));
		ResultSet rst = stmt.executeQuery();
		while (rst.next()) {
			user01.setId(rst.getLong("id"));
			user01.setNome(rst.getString("nome"));
			user01.setEndereco(rst.getString("endereco"));
			user01.setModalidade(rst.getString("modalidade"));
		}
		return user01;
	}
}