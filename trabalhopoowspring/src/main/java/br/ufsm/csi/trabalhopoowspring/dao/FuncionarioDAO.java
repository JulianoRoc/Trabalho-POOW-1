package br.ufsm.csi.trabalhopoowspring.dao;

import br.ufsm.csi.trabalhopoowspring.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;

public class FuncionarioDAO {

    public String inserir(Funcionario funcionario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO funcionario(nome, email, senha, ativo) VALUES (?, ?, ?, ?)");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());
            stmt.setBoolean(4, funcionario.isAtivo());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir funcionario!");
        }
        return "Funcionario inserido com sucesso!";
    }

    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM funcionario ORDER BY nome");
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setEmail(rs.getString("email"));
                f.setSenha(rs.getString("senha"));
                f.setAtivo(rs.getBoolean("ativo"));

                funcionarios.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionario");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Drive não carregou");
            ex.printStackTrace();
        }
        return funcionarios;
    }

    public String alterar(Funcionario funcionario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("UPDATE funcionario SET nome = ?, email = ?, senha = ?, ativo = ? WHERE id = ?");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());
            stmt.setBoolean(4, funcionario.isAtivo());
            stmt.setInt(5, funcionario.getId());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar funcionario");
        }
        return "Funcionario alterado com sucesso";
    }

    public boolean excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            if (stmt.getUpdateCount() <= 0) {
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir funcionario");
        }
        return true;
    }

    public Funcionario buscar(int id) {
        Funcionario funcionario = new Funcionario();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setAtivo(rs.getBoolean("ativo"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar funcionario");
        }
        return funcionario;
    }

    public Funcionario buscar(String email) {
        Funcionario funcionario = new Funcionario();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM funcionario WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setAtivo(rs.getBoolean("ativo"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar funcionario");
        }

        return funcionario;
    }
}