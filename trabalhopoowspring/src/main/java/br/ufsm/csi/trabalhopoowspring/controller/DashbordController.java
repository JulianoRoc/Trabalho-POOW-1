package br.ufsm.csi.trabalhopoowspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashbordController {

    @GetMapping("/dashbord")
    public String mostrarDashbord() {
        return "pages/dashbord";
    }
}