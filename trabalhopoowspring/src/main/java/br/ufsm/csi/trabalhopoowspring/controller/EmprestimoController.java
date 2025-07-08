package br.ufsm.csi.trabalhopoowspring.controller;

import br.ufsm.csi.trabalhopoowspring.model.Emprestimo;
import br.ufsm.csi.trabalhopoowspring.service.EmprestimoService;
import br.ufsm.csi.trabalhopoowspring.service.ClienteService;
import br.ufsm.csi.trabalhopoowspring.service.FuncionarioService;
import br.ufsm.csi.trabalhopoowspring.service.LivroService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService = new EmprestimoService();
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final ClienteService clienteService = new ClienteService();
    private final LivroService livroService = new LivroService();

    @GetMapping
    public String listaEmprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listar());
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("funcionarios", funcionarioService.listar());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("livros", livroService.listar());

        return "pages/emprestimos";
    }

    @PostMapping
    public String salvarEmprestimo(@ModelAttribute Emprestimo emprestimo, RedirectAttributes redirectAttributes) {
        String retorno;

        if (emprestimo.getId() != 0) {
            retorno = emprestimoService.alterar(emprestimo);
        } else {
            emprestimo.setFuncionario(funcionarioService.buscar(emprestimo.getFuncionario().getId()));
            emprestimo.setCliente(clienteService.buscar(emprestimo.getCliente().getId()));
            emprestimo.setLivro(livroService.buscar(emprestimo.getLivro().getId()));

            retorno = emprestimoService.inserir(emprestimo);
        }

        redirectAttributes.addFlashAttribute("msg", retorno);
        redirectAttributes.addFlashAttribute("tipoMsg", "success");
        return "redirect:/emprestimos";
    }

    @GetMapping("/editar/{emprestimoId}")
    public String editarEmprestimo(@PathVariable int emprestimoId, Model model) {
        Emprestimo emprestimo = emprestimoService.buscar(emprestimoId);

        if (emprestimo != null) {
            model.addAttribute("emprestimo", emprestimo);
        } else {
            model.addAttribute("emprestimo", new Emprestimo());
            model.addAttribute("msg", "Empréstimo não encontrado.");
        }
        model.addAttribute("emprestimos", emprestimoService.listar());
        model.addAttribute("funcionarios", funcionarioService.listar());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("livros", livroService.listar());

        return "pages/emprestimos";
    }

    @GetMapping("/excluir/{emprestimoId}")
    public String excluirEmprestimo(@PathVariable int emprestimoId, RedirectAttributes redirectAttributes) {
        String retorno = emprestimoService.excluir(emprestimoId);
        redirectAttributes.addFlashAttribute("msg", retorno);
        redirectAttributes.addFlashAttribute("tipoMsg", "success");

        return "redirect:/emprestimos";
    }
}