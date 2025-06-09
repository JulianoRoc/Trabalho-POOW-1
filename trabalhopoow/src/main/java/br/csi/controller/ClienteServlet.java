package br.csi.controller;

import br.csi.model.Cliente;
import br.csi.service.ClienteService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("cliente")
public class ClienteServlet extends HttpServlet {
    private static ClienteService service = new ClienteService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idcliente"));
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        String telefone = req.getParameter("telefone");
        String endereco = req.getParameter("endereco");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        String retorno;
        if (id > 0) {
            cliente.setId(id);
            retorno = new ClienteService().alterar(cliente);
        } else {
            retorno = new ClienteService().inserir(cliente);
        }
        req.setAttribute("retorno", retorno);
        req.setAttribute("clientes", new ClienteService().listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/clientes.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if (opcao != null) {
            if (opcao.equals("editar")) {
                Cliente cliente = service.buscar(Integer.parseInt(info));
                req.setAttribute("cliente", cliente);

                ArrayList<Cliente> clientes = new ClienteService().listar();
                req.setAttribute("clientes", clientes);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/clientes.jsp");
                rd.forward(req, resp);
            } else if (opcao.equals("excluir")) {
                String valor = service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", valor);

                ArrayList<Cliente> clientes = new ClienteService().listar();
                req.setAttribute("clientes", clientes);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/clientes.jsp");
                rd.forward(req, resp);
            }
        } else {
            ArrayList<Cliente> clientes = new ClienteService().listar();
            req.setAttribute("clientes", clientes);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/clientes.jsp");
            rd.forward(req, resp);
        }
    }
}