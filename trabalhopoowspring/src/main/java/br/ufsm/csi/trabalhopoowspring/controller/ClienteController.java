package br.ufsm.csi.trabalhopoowspring.controller;

import br.ufsm.csi.trabalhopoowspring.model.Cliente;
import br.ufsm.csi.trabalhopoowspring.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService = new ClienteService();

    @GetMapping
    public String listaCliente(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("cliente", new Cliente());

        return "pages/clientes";
    }

    @PostMapping
    public String salvarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        String retorno;

        if (cliente.getId() != 0) {
            retorno = clienteService.alterar(cliente);
        } else {
            retorno = clienteService.inserir(cliente);
        }

        redirectAttributes.addFlashAttribute("msg", retorno);
        redirectAttributes.addFlashAttribute("tipoMsg", "success");
        return "redirect:/cliente";
    }

    @GetMapping("/editar/{clienteId}")
    public String editarCliente(@PathVariable int clienteId, Model model) {
        Cliente cliente = clienteService.buscar(clienteId);

        if (cliente != null) {
            model.addAttribute("cliente", cliente);
        } else {
            model.addAttribute("cliente", new Cliente());
            model.addAttribute("msg", "Cliente não encontrado.");
        }

        model.addAttribute("clientes", clienteService.listar());
        return "pages/clientes";
    }

    @GetMapping("/excluir/{clienteId}")
    public String excluirCliente(@PathVariable int clienteId, RedirectAttributes redirectAttributes) {
        String retorno = clienteService.excluir(clienteId);
        redirectAttributes.addFlashAttribute("msg", retorno);
        redirectAttributes.addFlashAttribute("tipoMsg", "success"); // ou "danger"
        return "redirect:/cliente";
    }
}