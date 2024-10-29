package br.com.catalogodebrinquedos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.catalogodebrinquedos.model.BrinquedoModel;
import br.com.catalogodebrinquedos.model.repository.BrinquedoRepository;
import org.springframework.util.Assert;

@Service
public class BrinquedoService {

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    @Autowired
    private MessageSource messageSource; // Injeção do MessageSource

    // Método para salvar um novo brinquedo
    @Transactional
    public BrinquedoModel salvarBrinquedo(BrinquedoModel brinquedo) {
        Assert.notNull(brinquedo, messageSource.getMessage("brinquedoService.brinquedo.notnull", null, Locale.getDefault()));
        Assert.notNull(brinquedo.getDescricao(), messageSource.getMessage("brinquedoService.descricao.notnull", null, Locale.getDefault()));
        Assert.notNull(brinquedo.getCategoria(), messageSource.getMessage("brinquedoService.categoria.notnull", null, Locale.getDefault()));
        Assert.isTrue(brinquedo.getValor() != null && brinquedo.getValor().compareTo(BigDecimal.ZERO) > 0, 
                      messageSource.getMessage("brinquedoService.valor.positive", null, Locale.getDefault()));
        
        return brinquedoRepository.save(brinquedo);
    }

    // Método para buscar um brinquedo por ID
    public Optional<BrinquedoModel> buscarBrinquedoPorId(Long id) {
        Assert.notNull(id, messageSource.getMessage("brinquedoService.id.notnull", null, Locale.getDefault()));
        return brinquedoRepository.findById(id);
    }
    
 // Método para buscar todos os brinquedos de uma determinada categoria
    public List<BrinquedoModel> buscarBrinquedosPorCategoria(Long categoriaId) {
        Assert.notNull(categoriaId, messageSource.getMessage("brinquedoService.categoria.id.notnull", null, Locale.getDefault()));
        return brinquedoRepository.findByCategoriaId(categoriaId);
    }


    // Método para listar todos os brinquedos
    public List<BrinquedoModel> listarBrinquedos() {
        return brinquedoRepository.findAll();
    }

    // Método para deletar um brinquedo por ID
    @Transactional
    public void deletarBrinquedo(Long id) {
        Assert.notNull(id, messageSource.getMessage("brinquedoService.id.notnull", null, Locale.getDefault()));
        brinquedoRepository.deleteById(id);
    }
}
