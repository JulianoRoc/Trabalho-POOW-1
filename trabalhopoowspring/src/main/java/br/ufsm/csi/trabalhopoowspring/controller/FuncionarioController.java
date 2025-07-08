package br.ufsm.csi.trabalhopoowspring.controller;

import br.ufsm.csi.trabalhopoowspring.model.Funcionario;
import br.ufsm.csi.trabalhopoowspring.service.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService = new FuncionarioService();

    @GetMapping
    public String listaFuncionario(Model model) {
        model.addAttribute("funcionarios", new FuncionarioService().listar());
        model.addAttribute("funcionario", new Funcionario());

        return "pages/funcionarios";
    }

    @PostMapping
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario, RedirectAttributes redirectAttributes) {
        String retorno;

        if (funcionario.getId() != 0) {
            retorno = funcionarioService.alterar(funcionario);
        } else {
            retorno = funcionarioService.inserir(funcionario);
        }

        redirectAttributes.addFlashAttribute("msg", retorno);
        redirectAttributes.addFlashAttribute("tipoMsg", "success");
        return "redirect:/funcionario";
    }

    @GetMapping("/editar/{funcionarioId}")
    public String editarFuncionario(@PathVariable int funcionarioId, Model model) {
        Funcionario funcionario = funcionarioService.buscar(funcionarioId);

        if (funcionario != null) {
            model.addAttribute("funcionario", funcionario);
        } else {
            model.addAttribute("funcionario", new Funcionario());
            model.addAttribute("msg", "Funcionário não encontrado.");
        }

        model.addAttribute("funcionarios", funcionarioService.listar());
        return "pages/funcionarios";
    }

    @GetMapping("/excluir/{funcionarioId}")
    public String excluirFuncionario(@PathVariable int funcionarioId, RedirectAttributes redirectAttributes) {
        String retorno = new FuncionarioService().excluir(funcionarioId);
        redirectAttributes.addFlashAttribute("msg", retorno);
        redirectAttributes.addFlashAttribute("tipoMsg", "success");

        return "redirect:/funcionario";
    }
}