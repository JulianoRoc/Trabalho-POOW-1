package br.csi.service;

import br.csi.dao.EmprestimoDAO;
import br.csi.model.Emprestimo;

import java.util.ArrayList;

public class EmprestimoService {
    private EmprestimoDAO dao = new EmprestimoDAO();

    public String excluir(int id){
        if(dao.excluir(id)){
            return "Sucesso ao excluir funcionario";
        } else {
            return "Erro ao excluir funcionario";
        }
    }

    public ArrayList<Emprestimo> listar() { return dao.listar(); }

    public Emprestimo buscar(int id) { return dao.buscar(id); }

    public String alterar(Emprestimo emprestimo) { return dao.alterar(emprestimo); }

    public String inserir(Emprestimo emprestimo) { return dao.inserir(emprestimo); }
}