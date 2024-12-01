package br.com.catalogoprodutossustentaveis.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long id;

    @Column(name = "pro_descricao", nullable = false, length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private CategoriaModel categoria;

    @Column(name = "pro_marca", nullable = false, length = 100)
    private String marca;

    @Column(name = "pro_imagem", columnDefinition = "MEDIUMBLOB")
    private byte[] imagem;

    @Column(name = "pro_valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "pro_detalhes", length = 500)
    private String detalhes;

    @ManyToOne
    @JoinColumn(name = "forn_id", nullable = true)
    private FornecedorModel fornecedor;
}
