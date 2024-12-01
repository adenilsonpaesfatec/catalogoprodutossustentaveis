package br.com.catalogoprodutossustentaveis.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fornecedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "for_id")
    private Long id;

    @Column(name = "for_nome", nullable = false)
    private String nome;

    @Column(name = "for_email", nullable = false, unique = true)
    private String email;

    @Column(name = "for_telefone", length = 20)
    private String telefone;

    @Column(name = "for_endereco")
    private String endereco;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoModel> produtos;
}
