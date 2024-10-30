package br.com.catalogodebrinquedos.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public MultipartFile getImagem() {
		return imagem;
	}

	public void setImagem(MultipartFile imagem) {
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
