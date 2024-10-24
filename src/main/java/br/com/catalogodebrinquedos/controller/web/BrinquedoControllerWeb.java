package br.com.catalogodebrinquedos.controller.web;

import br.com.catalogodebrinquedos.model.BrinquedoModel;
import br.com.catalogodebrinquedos.model.repository.BrinquedoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

@Controller
@RequestMapping("/web")
public class BrinquedoControllerWeb {

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    @GetMapping("/home")
    public String home(Model model) {
        List<BrinquedoModel> brinquedos = brinquedoRepository.findAll();
        Collections.shuffle(brinquedos);
        List<BrinquedoModel> brinquedosAleatorios = brinquedos.stream().limit(6).toList();
        model.addAttribute("brinquedos", brinquedosAleatorios);
        return "home";
    }
    
    @GetMapping("/administracao")
    public String administracao(Model model) {
        List<BrinquedoModel> brinquedos = brinquedoRepository.findAll();
        model.addAttribute("brinquedos", brinquedos);
        return "administracao";
    }
}
