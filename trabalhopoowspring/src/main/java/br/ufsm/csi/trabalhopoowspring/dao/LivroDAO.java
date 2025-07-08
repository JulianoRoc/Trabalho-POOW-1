package br.ufsm.csi.trabalhopoowspring.dao;

import br.ufsm.csi.trabalhopoowspring.model.Livro;

import java.sql.*;
import java.util.ArrayList;

public class LivroDAO {

    public String inserir(Livro livro) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO livro(titulo, autor, ano_publicacao, disponivel) VALUES (?, ?, ?, ?)");
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir livro");
        }
        return "Livro inserido com sucesso!";
    }

    public ArrayList<Livro> listar() {
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM livro ORDER BY titulo");
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setDisponivel(rs.getBoolean("disponivel"));

                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Drive não carregou");
            ex.printStackTrace();
        }
        return livros;
    }

    public String alterar(Livro livro) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("UPDATE livro SET titulo = ?, autor = ?, ano_publicacao = ?, disponivel = ? WHERE id = ?");
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.setInt(5, livro.getId());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar livro");
        }
        return "Livro alterado com sucesso";
    }

    public boolean excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM livro WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            if (stmt.getUpdateCount() <= 0) {
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir livro");
        }
        return true;
    }

    public Livro buscar(int id) {
        Livro livro = new Livro();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM livro WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar livro");
        }
        return livro;
    }

    public void alterarDisponibilidade(Livro livro) throws SQLException, ClassNotFoundException {
        Connection conn = ConectarBancoDados.conectarBancoPostgres();
        PreparedStatement stmt = conn.prepareStatement("UPDATE livro SET disponivel = ? WHERE id = ?");

        stmt.setBoolean(1, livro.isDisponivel());
        stmt.setInt(2, livro.getId());
        stmt.executeUpdate();
    }
}