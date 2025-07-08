package br.ufsm.csi.trabalhopoowspring.controller;

import br.ufsm.csi.trabalhopoowspring.model.Funcionario;
import br.ufsm.csi.trabalhopoowspring.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model, String email, String senha) {
        Funcionario funcionario = new LoginService().autenticar(email, senha);

        if (funcionario != null) {
            session.setAttribute("funcionarioLogado", funcionario);
            return "redirect:/dashbord";
        } else {
            model.addAttribute("msg", "Login ou senha incorreto!");
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "pages/dashbord";
    }
}