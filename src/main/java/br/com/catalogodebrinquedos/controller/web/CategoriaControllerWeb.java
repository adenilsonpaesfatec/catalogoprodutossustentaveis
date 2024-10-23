package br.com.catalogodebrinquedos.controller.web;

import br.com.catalogodebrinquedos.model.Categoria;
import br.com.catalogodebrinquedos.model.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web")
public class CategoriaControllerWeb {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/catalogodebrinquedos")
    public String catalogoDeBrinquedos(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "catalogodebrinquedos";
    }
    
}
