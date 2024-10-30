package br.com.catalogodebrinquedos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidade que representa uma categoria no sistema de catálogo de brinquedos.
 * Cada categoria possui um nome, descrição, imagem, e uma lista de brinquedos
 * associados.
 */
@Entity
@Table(name = "categorias")
public class CategoriaModel {

	/**
	 * Identificador único da categoria.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id")
	private Long id;

	/**
	 * Nome da categoria. Deve conter entre 2 e 50 caracteres.
	 */
	@NotNull(message = "{categoriaDTO.nome.notnull}")
	@Size(min = 2, max = 50, message = "{categoriaDTO.nome.size}")
	@Column(name = "cat_nome", nullable = false)
	private String nome;

	/**
	 * Descrição da categoria. Deve conter entre 5 e 255 caracteres.
	 */
	@NotNull(message = "{categoriaDTO.descricao.notnull}")
	@Size(min = 5, max = 255, message = "{categoriaDTO.descricao.size}")
	@Column(name = "cat_descricao", nullable = false)
	private String descricao;

	/**
	 * Imagem da categoria em formato binário (BLOB).
	 */
	@Lob
	@Column(name = "cat_imagem", columnDefinition = "MEDIUMBLOB")
	private byte[] imagem;

	/**
	 * Lista de brinquedos associados a esta categoria.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<BrinquedoModel> brinquedos;

	/**
	 * Construtor padrão.
	 */
	public CategoriaModel() {
	}

	/**
	 * Construtor com todos os parâmetros.
	 *
	 * @param id         o identificador da categoria
	 * @param nome       o nome da categoria
	 * @param descricao  a descrição da categoria
	 * @param imagem     a imagem da categoria
	 * @param brinquedos a lista de brinquedos da categoria
	 */
	public CategoriaModel(Long id, String nome, String descricao, byte[] imagem, List<BrinquedoModel> brinquedos) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
		this.brinquedos = brinquedos;
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
