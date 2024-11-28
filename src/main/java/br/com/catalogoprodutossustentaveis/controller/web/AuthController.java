package br.com.catalogoprodutossustentaveis.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.catalogoprodutossustentaveis.service.BrinquedoService;
import br.com.catalogoprodutossustentaveis.service.CategoriaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/web/administracao")
public class AuthController {
	
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private BrinquedoService brinquedoService;
	
    @GetMapping("/login")
    public String login() {
        return "loginform"; // Retorna o template login.html
    }
    
    @GetMapping("/paineladministrativo")
    public String painelAdministrativo(Model model) {
        long totalCategorias = categoriaService.contarCategorias();
        long totalBrinquedos = brinquedoService.contarBrinquedos();

        model.addAttribute("totalCategorias", totalCategorias);
        model.addAttribute("totalBrinquedos", totalBrinquedos);

        return "paineladministrativo";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Remove cookies ou invalida a sessão explicitamente
        request.getSession().invalidate();
        return "redirect:/web/home";
    }
}
