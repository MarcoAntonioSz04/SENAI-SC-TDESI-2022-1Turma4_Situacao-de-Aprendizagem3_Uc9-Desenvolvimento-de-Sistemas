package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexao.ConexaoBanco;
import model.Usuario;

public class LoginRepository {
	private Connection conn;

	public LoginRepository() {
		conn = ConexaoBanco.getConnection();
	}

	public boolean validarLogin(Usuario usuario01) throws Exception {
		String sql = "SELECT * FROM cliente WHERE nome = ? and endereco = ? and modalidade = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, usuario01.getNome());
		stmt.setString(2, usuario01.getEndereco());
		stmt.setString(3, usuario01.getModalidade());

		ResultSet rst = stmt.executeQuery();
		if (rst.next()) {
			return true;
		}
		return false;
	}
}