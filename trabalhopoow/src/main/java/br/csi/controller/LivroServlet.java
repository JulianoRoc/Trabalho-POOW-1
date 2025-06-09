package br.csi.controller;

import br.csi.model.Livro;
import br.csi.service.LivroService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("livro")
public class LivroServlet extends HttpServlet {
    private static LivroService service = new LivroService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idlivro"));
        String titulo = req.getParameter("titulo");
        String autor = req.getParameter("autor");
        int anoPublicacao = Integer.parseInt(req.getParameter("anoPublicacao"));
        boolean disponivel = Boolean.parseBoolean(req.getParameter("disponivel"));

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPublicacao(anoPublicacao);
        livro.setDisponivel(disponivel);
        String retorno;
        if (id > 0) {
            livro.setId(id);
            retorno = new LivroService().alterar(livro);
        } else {
            retorno = new  LivroService().inserir(livro);
        }
        req.setAttribute("retorno", retorno);
        req.setAttribute("livros", new LivroService().listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/livros.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        System.out.println("opcao: " + opcao);
        String info = req.getParameter("info");
        System.out.println("info: " + info);

        if (opcao != null) {
            if (opcao.equals("editar")) {
                Livro l1 = service.buscar(Integer.parseInt(info));
                req.setAttribute("livro", l1);

                ArrayList<Livro> livros = new LivroService().listar();
                req.setAttribute("livros", livros);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/livros.jsp");
                rd.forward(req, resp);
            } else if (opcao.equals("excluir")) {
                String valor = service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", valor);

                ArrayList<Livro> livros = new LivroService().listar();
                req.setAttribute("livros", livros);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/livros.jsp");
                rd.forward(req, resp);
            }
        } else {
            ArrayList<Livro> livros = new LivroService().listar();
            req.setAttribute("livros", livros);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/livros.jsp");
            rd.forward(req, resp);
        }
    }
}