package br.com.catalogodebrinquedos.controller;

import br.com.catalogodebrinquedos.model.Brinquedo;
import br.com.catalogodebrinquedos.model.Categoria;
import br.com.catalogodebrinquedos.model.repository.BrinquedoRepository;
import br.com.catalogodebrinquedos.model.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Brinquedo> listarTodosBrinquedos() {
        return brinquedoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Brinquedo> buscarPorId(@PathVariable int id) {
        Optional<Brinquedo> brinquedo = brinquedoRepository.findById(id);
        return brinquedo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Brinquedo> adicionarBrinquedo(@RequestBody Brinquedo brinquedo) {
        try {
            if (brinquedo.getCategoria() != null) {
                Optional<Categoria> categoria = categoriaRepository.findById(brinquedo.getCategoria().getId());
                if (categoria.isPresent()) {
                    brinquedo.setCategoria(categoria.get());
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }
            Brinquedo brinquedoSalvo = brinquedoRepository.save(brinquedo);
            return ResponseEntity.ok(brinquedoSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brinquedo> atualizarBrinquedo(@PathVariable int id, @RequestBody Brinquedo brinquedoAtualizado) {
        Optional<Brinquedo> brinquedoExistente = brinquedoRepository.findById(id);
        if (brinquedoExistente.isPresent()) {
            Brinquedo brinquedo = brinquedoExistente.get();
            if (brinquedoAtualizado.getDescricao() != null) {
                brinquedo.setDescricao(brinquedoAtualizado.getDescricao());
            }
            if (brinquedoAtualizado.getMarca() != null) {
                brinquedo.setMarca(brinquedoAtualizado.getMarca());
            }
            if (brinquedoAtualizado.getImagem() != null) {
                brinquedo.setImagem(brinquedoAtualizado.getImagem());
            }
            if (brinquedoAtualizado.getValor() != null) {
                brinquedo.setValor(brinquedoAtualizado.getValor());
            }
            if (brinquedoAtualizado.getDetalhes() != null) {
                brinquedo.setDetalhes(brinquedoAtualizado.getDetalhes());
            }

            if (brinquedoAtualizado.getCategoria() != null) {
                Optional<Categoria> categoria = categoriaRepository.findById(brinquedoAtualizado.getCategoria().getId());
                if (categoria.isPresent()) {
                    brinquedo.setCategoria(categoria.get());
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }
            Brinquedo brinquedoSalvo = brinquedoRepository.save(brinquedo);
            return ResponseEntity.ok(brinquedoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBrinquedo(@PathVariable int id) {
        Optional<Brinquedo> brinquedoExistente = brinquedoRepository.findById(id);
        if (brinquedoExistente.isPresent()) {
            brinquedoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}