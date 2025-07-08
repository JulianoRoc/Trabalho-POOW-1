package br.ufsm.csi.trabalhopoowspring.service;

import br.ufsm.csi.trabalhopoowspring.model.Funcionario;

public class LoginService {
    public Funcionario autenticar(String email, String senha) {
        Funcionario funcionario = new FuncionarioService().buscar(email);
        if (funcionario !=null && funcionario.getSenha() != null && funcionario.getSenha().equals(senha)) {
            return funcionario;
        } else {
            return null;
        }
    }
}