package br.csi.service;

import br.csi.model.Funcionario;

public class LoginService {
    public boolean autenticar(String email, String senha) {
        FuncionarioService funcionarioService = new FuncionarioService();
        Funcionario funcionario = funcionarioService.buscar(email);

        return senha.equals(funcionario.getSenha());
    }
}