package br.com.catalogoprodutossustentaveis.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;

    @Column(name = "cat_nome", nullable = false)
    private String nome;

    @Column(name = "cat_descricao", nullable = false)
    private String descricao;

    @Lob
    @Column(name = "cat_imagem", columnDefinition = "MEDIUMBLOB")
    private byte[] imagem;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ProdutoModel> produtos;
}
