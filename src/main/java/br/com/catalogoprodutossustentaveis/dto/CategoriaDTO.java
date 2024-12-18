package br.com.catalogoprodutossustentaveis.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private MultipartFile imagem;
}
