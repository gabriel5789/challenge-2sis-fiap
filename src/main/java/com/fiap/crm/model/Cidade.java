package com.fiap.crm.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "PAN_CIDADE")
public class Cidade {
	@Id
	@SequenceGenerator(name = "sq_id_cidade", sequenceName = "PAN_SQ_ID_CIDADE", allocationSize = 1)
	@Column(name = "id_cidade", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_cidade")
	@NotNull(message = "Id da cidade obrigatório")
	private Long id;
	@Column(name = "nm_cidade", nullable = false)
	@NotBlank(message = "Nome da cidade obrigatório")
	private String nome;
	@Column(name = "sg_estado", nullable = false)
	@NotBlank(message = "Sigla do estado obrigatória")
	@Size(min = 2, max = 2, message = "Sigla do estado deve ter 2 caracteres")
	private String estado;

	public Cidade() {
	}

	public Cidade(Long id, String nome, String estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
