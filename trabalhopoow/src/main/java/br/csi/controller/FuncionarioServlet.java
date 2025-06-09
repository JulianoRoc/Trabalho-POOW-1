package br.csi.controller;

import br.csi.model.Funcionario;
import br.csi.service.FuncionarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("funcionario")
public class FuncionarioServlet extends HttpServlet  {
    private static FuncionarioService service = new FuncionarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idfuncionario"));
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        boolean ativo = Boolean.parseBoolean(req.getParameter("ativo"));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setEmail(email);
        funcionario.setSenha(senha);
        funcionario.setAtivo(ativo);
        String retorno;
        if(id > 0){
            funcionario.setId(id);
            retorno = new FuncionarioService().alterar(funcionario);
        } else {
            retorno = new FuncionarioService().inserir(funcionario);
        }
        req.setAttribute("retorno", retorno);
        req.setAttribute("funcionarios", new FuncionarioService().listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/funcionarios.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if(opcao != null){
            if(opcao.equals("editar")) {
                Funcionario f1 = service.buscar(Integer.parseInt(info));
                req.setAttribute("funcionario", f1);

                ArrayList<Funcionario> funcionarios = new FuncionarioService().listar();
                req.setAttribute("funcionarios", funcionarios);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/funcionarios.jsp");
                rd.forward(req, resp);
            }else if(opcao.equals("excluir")) {
                String valor =  service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", valor);

                ArrayList<Funcionario> funcionarios = new FuncionarioService().listar();
                req.setAttribute("funcionarios", funcionarios);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/funcionarios.jsp");
                rd.forward(req, resp);
            }
        }else{
            ArrayList<Funcionario> funcionarios = new FuncionarioService().listar();
            req.setAttribute("funcionarios", funcionarios);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/funcionarios.jsp");
            rd.forward(req, resp);
        }
    }
}