package br.com.catalogodebrinquedos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="categorias")
public class CategoriaModel {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cat_id")
    private Long id;

    @Column(name="cat_nome")
    private String nome;

    @Column(name="cat_descricao")
    private String descricao;
    
    @Column(name="cat_imagem")
    private String imagem;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<BrinquedoModel> brinquedos;

    // Construtores
    public CategoriaModel() {
    }

    public CategoriaModel(Long id, String nome, String descricao, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<BrinquedoModel> getBrinquedos() {
        return brinquedos;
    }

    public void setBrinquedos(List<BrinquedoModel> brinquedos) {
        this.brinquedos = brinquedos;
    }
}