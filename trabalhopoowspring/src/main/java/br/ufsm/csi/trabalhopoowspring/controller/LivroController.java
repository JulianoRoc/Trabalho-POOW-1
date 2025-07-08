package br.ufsm.csi.trabalhopoowspring.controller;

import br.ufsm.csi.trabalhopoowspring.model.Livro;
import br.ufsm.csi.trabalhopoowspring.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService = new LivroService();

    @GetMapping
    public String listaLivro(Model model) {
        model.addAttribute("livros", livroService.listar());
        model.addAttribute("livro", new Livro());

        return "pages/livros";
    }

    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro, RedirectAttributes redirectAttributes) {
        String retorno;

        if (livro.getId() != 0) {
            retorno = livroService.alterar(livro);
        } else {
            retorno = livroService.inserir(livro);
        }

        redirectAttributes.addFlashAttribute("msg", retorno);
        redirectAttributes.addFlashAttribute("tipoMsg", "success");
        return "redirect:/livro";
    }

    @GetMapping("/editar/{livroId}")
    public String editarLivro(@PathVariable int livroId, Model model) {
        Livro livro = livroService.buscar(livroId);

        if (livro != null) {
            model.addAttribute("livro", livro);
        } else {
            model.addAttribute("livro", new Livro());
            model.addAttribute("msg", "Livro não encontrado.");
        }

        model.addAttribute("livros", livroService.listar());
        return "pages/livros";
    }

    @GetMapping("/excluir/{livroId}")
    public String excluirLivro(@PathVariable int livroId, RedirectAttributes redirectAttributes) {
        String retorno = livroService.excluir(livroId);
        redirectAttributes.addFlashAttribute("msg", retorno);
        redirectAttributes.addFlashAttribute("tipoMsg", "success");

        return "redirect:/livro";
    }
}
