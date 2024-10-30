package br.com.catalogodebrinquedos.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidade que representa um brinquedo no sistema de catálogo de brinquedos.
 */
@Entity
@Table(name = "brinquedos")
public class BrinquedoModel {

	/**
	 * Identificador único do brinquedo.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bri_id")
	private Long id;

	/**
	 * Descrição do brinquedo. Deve conter entre 5 e 255 caracteres.
	 */
	@NotNull(message = "{brinquedoDTO.descricao.notnull}")
	@Size(min = 5, max = 255, message = "{brinquedoDTO.descricao.size}")
	@Column(name = "bri_descricao", nullable = false, length = 255)
	private String descricao;

	/**
	 * Categoria à qual o brinquedo pertence. Este campo é obrigatório.
	 */
	@NotNull(message = "{brinquedoDTO.categoriaId.notnull}")
	@ManyToOne
	@JoinColumn(name = "cat_id", nullable = false)
	private CategoriaModel categoria;

	/**
	 * Marca do brinquedo. Deve conter no máximo 100 caracteres.
	 */
	@NotNull(message = "{brinquedoDTO.marca.notnull}")
	@Size(max = 100, message = "{brinquedoDTO.marca.size}")
	@Column(name = "bri_marca", nullable = false, length = 100)
	private String marca;

	/**
	 * Imagem do brinquedo em formato binário (BLOB).
	 */
	@Column(name = "bri_imagem", columnDefinition = "MEDIUMBLOB")
	private byte[] imagem;

	/**
	 * Valor do brinquedo. Deve ser um valor positivo.
	 */
	@NotNull(message = "{brinquedoDTO.valor.notnull}")
	@DecimalMin(value = "0.01", inclusive = false, message = "{brinquedoDTO.valor.positive}")
	@Column(name = "bri_valor", nullable = false)
	private BigDecimal valor;

	/**
	 * Detalhes adicionais do brinquedo. Pode conter até 500 caracteres.
	 */
	@Size(max = 500, message = "{brinquedoDTO.detalhes.size}")
	@Column(name = "bri_detalhes", length = 500)
	private String detalhes;

	/**
	 * Construtor padrão.
	 */
	public BrinquedoModel() {
	}

	/**
	 * Construtor com todos os parâmetros.
	 *
	 * @param id        o identificador do brinquedo
	 * @param descricao a descrição do brinquedo
	 * @param categoria a categoria do brinquedo
	 * @param marca     a marca do brinquedo
	 * @param imagem    a imagem do brinquedo
	 * @param valor     o valor do brinquedo
	 * @param detalhes  os detalhes adicionais do brinquedo
	 */
	public BrinquedoModel(Long id, String descricao, CategoriaModel categoria, String marca, byte[] imagem,
			BigDecimal valor, String detalhes) {
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.marca = marca;
		this.imagem = imagem;
		this.valor = valor;
		this.detalhes = detalhes;
	}

	// Getters e Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
}
