package br.csi.dao;

import br.csi.model.Cliente;
import br.csi.model.Emprestimo;
import br.csi.model.Funcionario;
import br.csi.model.Livro;

import java.sql.*;
import java.util.ArrayList;

public class EmprestimoDAO {

    public String inserir(Emprestimo emprestimo) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO emprestimo (data_emprestimo, data_devolucao, id_funcionario, id_cliente, id_livro) values (?, NULL, ?, ?, ?);");
            stmt.setDate(1, new Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setInt(2, emprestimo.getFuncionario().getId());
            stmt.setInt(3, emprestimo.getCliente().getId());
            stmt.setInt(4, emprestimo.getLivro().getId());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir emprestimo!");
        }
        return "Emprestimo inserido com sucesso!";
    }

    public ArrayList<Emprestimo> listar() {
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emprestimo ORDER BY data_emprestimo DESC");
            while(rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setId(rs.getInt("id"));
                e.setDataEmprestimo(rs.getDate("data_emprestimo"));
                e.setDataDevolucao(rs.getDate("data_devolucao"));

                Funcionario f = new FuncionarioDAO().buscar(rs.getInt("id_funcionario"));
                e.setFuncionario(f);

                Cliente c = new ClienteDAO().buscar(rs.getInt("id_cliente"));
                e.setCliente(c);

                Livro l = new LivroDAO().buscar(rs.getInt("id_livro"));
                e.setLivro(l);

                emprestimos.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar emprestimos");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Drive não carregou");
            ex.printStackTrace();
        }
        return emprestimos;
    }

    public String alterar(Emprestimo emprestimo) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("UPDATE emprestimo SET data_emprestimo = ?, data_devolucao = ?, id_funcionario = ?, id_cliente = ?, id_livro = ? WHERE id = ?");
            stmt.setDate(1, new Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(2, new Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setInt(3, emprestimo.getFuncionario().getId());
            stmt.setInt(4, emprestimo.getCliente().getId());
            stmt.setInt(5, emprestimo.getLivro().getId());
            stmt.setInt(6, emprestimo.getId());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar emprestimo");
        }
        return "Emprestimo alterado com sucesso";
    }

    public boolean excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM emprestimo WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            if (stmt.getUpdateCount() <= 0) {
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir emprestimo");
        }
        return true;
    }

    public Emprestimo buscar(int id) {
        Emprestimo emprestimo = new Emprestimo();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM emprestimo WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataDevolucao(rs.getDate("data_devolucao"));
                emprestimo.setFuncionario(new FuncionarioDAO().buscar(rs.getInt("id_funcionario")));
                emprestimo.setCliente(new ClienteDAO().buscar(rs.getInt("id_cliente")));
                emprestimo.setLivro(new LivroDAO().buscar(rs.getInt("id_livro")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar emprestimo");
        }
        return emprestimo;
    }
}
