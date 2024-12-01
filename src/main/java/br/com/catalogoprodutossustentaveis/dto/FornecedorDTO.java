package br.com.catalogoprodutossustentaveis.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
}
