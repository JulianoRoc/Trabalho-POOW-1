package br.csi.controller;

import br.csi.model.Cliente;
import br.csi.model.Emprestimo;
import br.csi.model.Funcionario;
import br.csi.model.Livro;
import br.csi.service.ClienteService;
import br.csi.service.EmprestimoService;
import br.csi.service.FuncionarioService;
import br.csi.service.LivroService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("emprestimos")
public class EmprestimoServlet extends HttpServlet {
    private  static EmprestimoService emprestimoService = new EmprestimoService();
    private  static FuncionarioService funcionarioService = new FuncionarioService();
    private  static ClienteService clienteService = new ClienteService();
    private  static LivroService livroService = new LivroService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idemprestimo"));
        String dataEmprestimo = req.getParameter("dataemprestimo");
        String dataDevolucao = req.getParameter("datadevolucao");
        System.out.println("dataDevolucao: " + dataDevolucao);
        Emprestimo emprestimo = new Emprestimo();

        int idFuncionario = Integer.parseInt(req.getParameter("idfuncionario"));
        Funcionario funcionario = funcionarioService.buscar(idFuncionario);
        emprestimo.setFuncionario(funcionario);

        int idCliente = Integer.parseInt(req.getParameter("idcliente"));
        Cliente cliente = clienteService.buscar(idCliente);
        emprestimo.setCliente(cliente);

        int idLivro = Integer.parseInt(req.getParameter("idlivro"));
        Livro livro = livroService.buscar(idLivro);
        emprestimo.setLivro(livro);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            emprestimo.setDataEmprestimo(format.parse(dataEmprestimo));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String retorno;
        if(id > 0){
            emprestimo.setId(id);
            try {
                emprestimo.setDataDevolucao(format.parse(dataDevolucao));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            retorno = new EmprestimoService().alterar(emprestimo);
        } else {
            retorno = new EmprestimoService().inserir(emprestimo);
        }
        req.setAttribute("retorno", retorno);
        req.setAttribute("emprestimos", new  EmprestimoService().listar());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/emprestimos.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if(opcao != null) {
            if(opcao.equals("editar")){
                Emprestimo e1 = emprestimoService.buscar(Integer.parseInt(info));
                req.setAttribute("emprestimo", e1);
            } else if(opcao.equals("excluir")){
                String valor = emprestimoService.excluir(Integer.parseInt(info));
                req.setAttribute("msg", valor);
            }
        }
        ArrayList<Emprestimo> emprestimos = new EmprestimoService().listar();
        req.setAttribute("emprestimos", emprestimos);
        ArrayList<Funcionario> funcionarios = new FuncionarioService().listar();
        req.setAttribute("funcionarios", funcionarios);
        ArrayList<Cliente> clientes = new ClienteService().listar();
        req.setAttribute("clientes", clientes);
        ArrayList<Livro> livros = new LivroService().listar();
        req.setAttribute("livros", livros);

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/emprestimos.jsp");
        rd.forward(req,resp);
    }
}
