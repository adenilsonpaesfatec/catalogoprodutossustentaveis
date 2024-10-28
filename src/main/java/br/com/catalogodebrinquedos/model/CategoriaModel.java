package br.com.catalogodebrinquedos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="categorias")
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cat_id")
    private Long id;

    @Column(name="cat_nome")
    private String nome;

    @Column(name="cat_descricao")
    private String descricao;
    
    @Lob
    @Column(name="cat_imagem", columnDefinition = "MEDIUMBLOB")
    private byte[] imagem;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<BrinquedoModel> brinquedos;

	public CategoriaModel() {
	}

	public CategoriaModel(Long id, String nome, String descricao, byte[] imagem, List<BrinquedoModel> brinquedos) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
		this.brinquedos = brinquedos;
	}

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

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public List<BrinquedoModel> getBrinquedos() {
		return brinquedos;
	}

	public void setBrinquedos(List<BrinquedoModel> brinquedos) {
		this.brinquedos = brinquedos;
	}
    
}