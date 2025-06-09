package br.csi.service;

import br.csi.dao.ClienteDAO;
import br.csi.model.Cliente;
import java.util.ArrayList;

public class ClienteService {
    private static ClienteDAO dao = new ClienteDAO();

    public String excluir(int id) {
        if (dao.excluir(id)) {
            return "Sucesso ao excluir cliente";
        } else {
            return "Erro ao excluir cliente";
        }
    }

    public ArrayList<Cliente> listar() { return dao.listar(); }

    public Cliente buscar(int clienteId) { return dao.buscar(clienteId); }

    public String alterar(Cliente cliente) { return dao.alterar(cliente); }

    public String inserir(Cliente cliente) { return dao.inserir(cliente); }
}
