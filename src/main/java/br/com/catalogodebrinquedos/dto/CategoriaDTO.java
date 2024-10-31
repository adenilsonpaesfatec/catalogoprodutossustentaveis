package br.com.catalogodebrinquedos.dto;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object para Categoria, usado para transferir dados entre a
 * camada de apresentação e a camada de serviço.
 * 
 * <p>Esta classe contém os atributos e validações necessários para criar ou 
 * atualizar uma categoria no sistema, incluindo nome, descrição e imagem opcional.</p>
 */
public class CategoriaDTO {

	private Long id;

	@NotNull(message = "{categoriaDTO.nome.notnull}")
	@Size(min = 2, max = 50, message = "{categoriaDTO.nome.size}")
	private String nome;

	@NotNull(message = "{categoriaDTO.descricao.notnull}")
	@Size(min = 5, max = 255, message = "{categoriaDTO.descricao.size}")
	private String descricao;

	private MultipartFile imagem;

	/**
	 * Obtém o identificador único da categoria.
	 * 
	 * @return o ID da categoria
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador único da categoria.
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
	 * Obtém a imagem associada à categoria.
	 * 
	 * @return o arquivo de imagem da categoria
	 */
	public MultipartFile getImagem() {
		return imagem;
	}

	/**
	 * Define a imagem associada à categoria.
	 * 
	 * @param imagem o novo arquivo de imagem da categoria
	 */
	public void setImagem(MultipartFile imagem) {
		this.imagem = imagem;
	}
}
