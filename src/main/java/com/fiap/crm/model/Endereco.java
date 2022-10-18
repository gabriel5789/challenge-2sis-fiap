package com.fiap.crm.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PAN_ENDERECO")
public class Endereco {
	@Id
	@Column(name = "id_endereco", nullable = false)
	@SequenceGenerator(name = "sq_id_endereco", sequenceName = "PAN_SQ_ID_ENDERECO", allocationSize = 1)
	@GeneratedValue(generator = "sq_id_endereco", strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "ds_complemento")
	@Size(max = 50, message = "Complemento deve ter no máximo 50 caracteres")
	private String complemento;
	@NotNull(message = "Número da rua obrigatório")
	@Column(name = "nr_rua", nullable = false)
	private Integer numero;
	@NotBlank(message = "Nome da rua obrigatório")
	@Column(name = "nm_rua", nullable = false)
	private String rua;
	@NotBlank(message = "Nome do bairro obrigatório")
	@Column(name = "nm_bairro", nullable = false)
	private String bairro;
	@Column(name = "nr_cep", nullable = false)
	private Integer cep;
	@ManyToOne
	@JoinColumn(name = "id_cidade", referencedColumnName = "id_cidade")
	private Cidade cidade;

	@Column(name = "id_cliente", nullable = false)
	@NotNull(message = "Endereço deve ter o identificador do cliente")
	private Long clienteId;

	public Endereco() {
	}

	public Endereco(Long id, String complemento, Integer numero, String rua, String bairro, Integer cep, Cidade cidade, Long clienteId) {
		this.id = id;
		this.complemento = complemento;
		this.numero = numero;
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.clienteId = clienteId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
}
