package br.com.catalogodebrinquedos.controller.web;

import br.com.catalogodebrinquedos.model.BrinquedoModel;
import br.com.catalogodebrinquedos.model.CategoriaModel;
import br.com.catalogodebrinquedos.model.repository.BrinquedoRepository;
import br.com.catalogodebrinquedos.model.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web")
public class CategoriaControllerWeb {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private BrinquedoRepository brinquedoRepository;

    @GetMapping("/catalogodebrinquedos")
    public String catalogoDeBrinquedos(Model model) {
        List<CategoriaModel> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "catalogodebrinquedos";
    }
    
    @GetMapping("/catalogodebrinquedos/categoria/{id}")
    public String exibirBrinquedosPorCategoria(@PathVariable Long id, Model model) {
        CategoriaModel categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        List<BrinquedoModel> brinquedos = brinquedoRepository.findByCategoriaId(id);
        model.addAttribute("brinquedos", brinquedos);
        model.addAttribute("categoriaNome", categoria.getNome());
        return "brinquedosporcategoria";
    }

}
