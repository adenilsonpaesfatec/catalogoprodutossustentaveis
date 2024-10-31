package br.com.catalogodebrinquedos.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidade que representa uma categoria no sistema de catálogo de brinquedos.
 * 
 * <p>Cada categoria possui um nome, descrição, imagem, e uma lista de brinquedos
 * associados.</p>
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
	 * Nome da categoria.
	 * <p>Deve conter entre 2 e 50 caracteres.</p>
	 */
	@NotNull(message = "{categoriaDTO.nome.notnull}")
	@Size(min = 2, max = 50, message = "{categoriaDTO.nome.size}")
	@Column(name = "cat_nome", nullable = false)
	private String nome;

	/**
	 * Descrição da categoria.
	 * <p>Deve conter entre 5 e 255 caracteres.</p>
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
	 * @param imagem     a imagem da categoria em formato binário
	 * @param brinquedos a lista de brinquedos associados à categoria
	 */
	public CategoriaModel(Long id, String nome, String descricao, byte[] imagem, List<BrinquedoModel> brinquedos) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
		this.brinquedos = brinquedos;
	}

	// Getters e Setters

	/**
	 * Obtém o identificador da categoria.
	 * 
	 * @return o ID da categoria
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador da categoria.
	 * 
	 * @param id o novo ID da categoria
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém o nome da categoria.
	 * 
	 * @return o nome da categoria
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define o nome da categoria.
	 * 
	 * @param nome o novo nome da categoria
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém a descrição da categoria.
	 * 
	 * @return a descrição da categoria
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define a descrição da categoria.
	 * 
	 * @param descricao a nova descrição da categoria
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Obtém a imagem da categoria em formato binário.
	 * 
	 * @return a imagem da categoria
	 */
	public byte[] getImagem() {
		return imagem;
	}

	/**
	 * Define a imagem da categoria em formato binário.
	 * 
	 * @param imagem a nova imagem da categoria
	 */
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	/**
	 * Obtém a lista de brinquedos associados a esta categoria.
	 * 
	 * @return a lista de brinquedos da categoria
	 */
	public List<BrinquedoModel> getBrinquedos() {
		return brinquedos;
	}

	/**
	 * Define a lista de brinquedos associados a esta categoria.
	 * 
	 * @param brinquedos a nova lista de brinquedos da categoria
	 */
	public void setBrinquedos(List<BrinquedoModel> brinquedos) {
		this.brinquedos = brinquedos;
	}
}
