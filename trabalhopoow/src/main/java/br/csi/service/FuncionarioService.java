package br.csi.service;

import br.csi.dao.FuncionarioDAO;
import br.csi.model.Funcionario;
import java.util.ArrayList;

public class FuncionarioService {
    private static FuncionarioDAO dao = new FuncionarioDAO();

    public String excluir(int id){
        if(dao.excluir(id)){
            return "Sucesso ao excluir funcionario";
        }else{
            return "Erro ao excluir funcionario";
        }
    }

    public ArrayList<Funcionario> listar(){ return dao.listar(); }

    public Funcionario buscar(int funcionarioId) {
        return dao.buscar(funcionarioId);
    }

    public Funcionario buscar(String email) { return dao.buscar(email); }

    public String alterar(Funcionario funcionario) {
        return dao.alterar(funcionario);
    }

    public String inserir(Funcionario funcionario) {
        return dao.inserir(funcionario);
    }
}