package br.ufsm.csi.trabalhopoowspring.service;

import br.ufsm.csi.trabalhopoowspring.dao.EmprestimoDAO;
import br.ufsm.csi.trabalhopoowspring.dao.LivroDAO;
import br.ufsm.csi.trabalhopoowspring.model.Emprestimo;
import br.ufsm.csi.trabalhopoowspring.model.Livro;

import java.util.ArrayList;

public class EmprestimoService {
    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private LivroDAO livroDAO = new LivroDAO();

    public String excluir(int id) {
        if (emprestimoDAO.excluir(id)) {
            return "Sucesso ao excluir funcionario";
        } else {
            return "Erro ao excluir funcionario";
        }
    }

    public ArrayList<Emprestimo> listar() {
        return emprestimoDAO.listar();
    }

    public Emprestimo buscar(int id) {
        return emprestimoDAO.buscar(id);
    }

    public String alterar(Emprestimo emprestimo) {
        try {
            emprestimoDAO.alterar(emprestimo);

            if (emprestimo.getDataDevolucao() != null) {
                Livro livro = emprestimo.getLivro();
                livro.setDisponivel(true);
                livroDAO.alterarDisponibilidade(livro);
            }

            return "Empréstimo alterado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao alterar o empréstimo.";
        }
    }

    public String inserir(Emprestimo emprestimo) {
        try {
            if (!emprestimo.getLivro().isDisponivel()) {
                return "Erro: O livro selecionado não está disponível para empréstimo.";
            }
            emprestimoDAO.inserir(emprestimo);
            Livro livro = emprestimo.getLivro();
            livro.setDisponivel(false);
            livroDAO.alterarDisponibilidade(livro);

            return "Empréstimo cadastrado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar o empréstimo.";
        }
    }
}