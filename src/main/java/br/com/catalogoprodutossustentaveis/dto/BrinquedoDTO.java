package br.com.catalogoprodutossustentaveis.dto;

import java.math.BigDecimal;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * DTO para representar os dados de um brinquedo na aplicação.
 * 
 * <p>Esta classe é utilizada para transferir dados entre as camadas de
 * apresentação e serviço, incluindo validações e atributos necessários
 * para criar ou atualizar um brinquedo.</p>
 */
public class BrinquedoDTO {
	private Long id;

	@NotNull(message = "{brinquedoDTO.descricao.notnull}")
	@Size(min = 1, max = 255, message = "{brinquedoDTO.descricao.size}")
	private String descricao;

	@NotNull(message = "{brinquedoDTO.categoriaId.notnull}")
	private Long categoriaId;

	@NotNull(message = "{brinquedoDTO.marca.notnull}")
	@Size(max = 100, message = "{brinquedoDTO.marca.size}")
	private String marca;

	private MultipartFile imagem;

	@NotNull(message = "{brinquedoDTO.valor.notnull}")
	@Positive(message = "{brinquedoDTO.valor.positive}")
	private BigDecimal valor;

	@Size(max = 500, message = "{brinquedoDTO.detalhes.size}")
	private String detalhes;

	// Getters e Setters
	
	/**
	 * Obtém o identificador único do brinquedo.
	 * 
	 * @return o ID do brinquedo
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador único do brinquedo.
	 * 
	 * @param id o novo ID do brinquedo
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém a descrição do brinquedo.
	 * 
	 * @return a descrição do brinquedo
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define a descrição do brinquedo.
	 * 
	 * @param descricao a nova descrição do brinquedo
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Obtém o identificador da categoria associada ao brinquedo.
	 * 
	 * @return o ID da categoria
	 */
	public Long getCategoriaId() {
		return categoriaId;
	}

	/**
	 * Define o identificador da categoria associada ao brinquedo.
	 * 
	 * @param categoriaId o novo ID da categoria
	 */
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	/**
	 * Obtém a marca do brinquedo.
	 * 
	 * @return a marca do brinquedo
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Define a marca do brinquedo.
	 * 
	 * @param marca a nova marca do brinquedo
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Obtém a imagem associada ao brinquedo.
	 * 
	 * @return o arquivo de imagem do brinquedo
	 */
	public MultipartFile getImagem() {
		return imagem;
	}

	/**
	 * Define a imagem associada ao brinquedo.
	 * 
	 * @param imagem o novo arquivo de imagem do brinquedo
	 */
	public void setImagem(MultipartFile imagem) {
		this.imagem = imagem;
	}

	/**
	 * Obtém o valor monetário do brinquedo.
	 * 
	 * @return o valor do brinquedo
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Define o valor monetário do brinquedo.
	 * 
	 * @param valor o novo valor do brinquedo
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * Obtém os detalhes adicionais do brinquedo.
	 * 
	 * @return os detalhes do brinquedo
	 */
	public String getDetalhes() {
		return detalhes;
	}

	/**
	 * Define os detalhes adicionais do brinquedo.
	 * 
	 * @param detalhes os novos detalhes do brinquedo
	 */
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
}
