package br.ufsm.csi.trabalhopoowspring.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Emprestimo {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataEmprestimo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDevolucao;
    private Funcionario funcionario;
    private Cliente cliente;
    private Livro livro;

    public Emprestimo() {
    }

    public Emprestimo(int id, Date dataEmprestimo, Date dataDevolucao, Funcionario funcionario, Cliente cliente, Livro livro) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.livro = livro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() { return livro; }

    public void setLivro(Livro livro) { this.livro = livro; }
}