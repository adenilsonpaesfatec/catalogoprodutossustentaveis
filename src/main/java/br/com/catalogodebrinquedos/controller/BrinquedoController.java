package br.com.catalogodebrinquedos.controller;

import br.com.catalogodebrinquedos.model.BrinquedoModel;
import br.com.catalogodebrinquedos.model.CategoriaModel;
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
    public List<BrinquedoModel> listarTodosBrinquedos() {
        return brinquedoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BrinquedoModel> buscarPorId(@PathVariable Long id) {
        Optional<BrinquedoModel> brinquedo = brinquedoRepository.findById(id);
        return brinquedo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BrinquedoModel> adicionarBrinquedo(@RequestBody BrinquedoModel brinquedo) {
        try {
            if (brinquedo.getCategoria() != null) {
                Optional<CategoriaModel> categoria = categoriaRepository.findById(brinquedo.getCategoria().getId());
                if (categoria.isPresent()) {
                    brinquedo.setCategoria(categoria.get());
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }
            BrinquedoModel brinquedoSalvo = brinquedoRepository.save(brinquedo);
            return ResponseEntity.ok(brinquedoSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrinquedoModel> atualizarBrinquedo(@PathVariable Long id, @RequestBody BrinquedoModel brinquedoAtualizado) {
        Optional<BrinquedoModel> brinquedoExistente = brinquedoRepository.findById(id);
        if (brinquedoExistente.isPresent()) {
            BrinquedoModel brinquedo = brinquedoExistente.get();
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
                Optional<CategoriaModel> categoria = categoriaRepository.findById(brinquedoAtualizado.getCategoria().getId());
                if (categoria.isPresent()) {
                    brinquedo.setCategoria(categoria.get());
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }
            BrinquedoModel brinquedoSalvo = brinquedoRepository.save(brinquedo);
            return ResponseEntity.ok(brinquedoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBrinquedo(@PathVariable Long id) {
        Optional<BrinquedoModel> brinquedoExistente = brinquedoRepository.findById(id);
        if (brinquedoExistente.isPresent()) {
            brinquedoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}