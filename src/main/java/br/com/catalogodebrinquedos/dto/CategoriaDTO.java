package br.com.catalogodebrinquedos.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object para Categoria, usado para transferir dados entre a
 * camada de apresentação e a camada de serviço.
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

	public MultipartFile getImagem() {
		return imagem;
	}

	public void setImagem(MultipartFile imagem) {
		this.imagem = imagem;
	}
}
