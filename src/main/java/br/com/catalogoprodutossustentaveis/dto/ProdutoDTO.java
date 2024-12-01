package br.com.catalogoprodutossustentaveis.dto;

import java.math.BigDecimal;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String descricao;
    private Long categoriaId;
    private Long fornecedorId;
    private String marca;
    private MultipartFile imagem;
    private BigDecimal valor;
    private String detalhes;
}
