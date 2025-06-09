package br.csi.service;

import br.csi.dao.LivroDAO;
import br.csi.model.Livro;
import java.util.ArrayList;

public class LivroService {
    private static LivroDAO dao = new LivroDAO();

    public String excluir(int id) {
        if (dao.excluir(id)) {
            return "Sucesso ao excluir livro";
        } else {
            return "Erro ao excluir livro";
        }
    }

    public ArrayList<Livro> listar() { return dao.listar(); }

    public Livro buscar(int livroId) { return dao.buscar(livroId); }

    public String alterar(Livro livro) { return dao.alterar(livro); }

    public String inserir(Livro livro) { return dao.inserir(livro); }
}
