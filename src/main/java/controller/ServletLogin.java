package controller;

import java.io.IOException;

import dao.LoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

@WebServlet("/ServletOi")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginRepository loginRepository = new LoginRepository();

	public ServletLogin() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String modalidade = request.getParameter("modalidade");
		String url = request.getParameter("url");

		try {
			if (nome != null && !nome.isEmpty() && endereco != null && !endereco.isEmpty() && modalidade != null && !modalidade.isEmpty()) {
				Usuario user01 = new Usuario();
				user01.setNome(nome);
				user01.setEndereco(endereco);
				user01.setModalidade(modalidade);
				if (loginRepository.validarLogin(user01)) {
					request.getSession().setAttribute("nome", user01.getNome());
					if (url == null || url.equals("null")) {
						url = "painel/inicio.jsp";
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				} else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("login.jsp");
					request.setAttribute("msg", "Login incorreto!");
					redirecionar.forward(request, response);
				}
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("login.jsp");
				request.setAttribute("msg", "Informe o LOGIN corretamente");
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}