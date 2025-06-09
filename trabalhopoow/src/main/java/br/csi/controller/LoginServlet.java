package br.csi.controller;

import br.csi.service.LoginService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        System.out.println("Email: "+email+" - senha: "+senha);

        RequestDispatcher dispatcher;

        if(new LoginService().autenticar(email, senha)) {
            dispatcher = req.getRequestDispatcher("WEB-INF/pages/dashbord.jsp");
            dispatcher.forward(req, resp);
        }else {
            dispatcher = req.getRequestDispatcher("index.jsp");
            req.setAttribute("msg","Login ou senha incorreto!");
            dispatcher.forward(req, resp);
        }
    }
}
