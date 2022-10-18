package com.fiap.crm.model;

import com.fiap.crm.validation.Put;
import com.fiap.crm.validation.Post;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PAN_CLIENTE")
public class Cliente {
	@Id
	@Column(name = "id_cliente")
	@SequenceGenerator(name = "sq_id_cliente", sequenceName = "PAN_SQ_ID_CLIENTE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_cliente")
	@NotNull(groups = Put.class)
	private Long id;
	@Column(name = "nm_cliente", nullable = false)
	@NotBlank(message = "Nome obrigatório")
	@Size(max = 80, message = "Nome deve ter no máximo 80 caracteres", groups = {Put.class, Post.class})
	private String nome;
	@Column(name = "nr_cpf", nullable = false, unique = true)
	@CPF(message = "CPF inválido", groups = {Post.class, Put.class})
	private String cpf;
	@Column(name = "dt_nascimento", nullable = false)
	@Past(message = "Data de nascimento deve estar no passado", groups = {Post.class, Put.class})
	private LocalDate dataNascimento;
	@Column(name = "dt_adesao", nullable = false)
	@Past(message = "Data de adesão deve estar no passado", groups = {Post.class, Put.class})
	private LocalDate dataAdesao;
	@Column(name = "ds_email", nullable = false, unique = true)
	@Email(message = "Email inválido", groups = {Post.class, Put.class})
	private String email;
	@OneToMany
	@JoinColumn(referencedColumnName = "id_cliente", name = "id_cliente")
	private List<@Valid Endereco> enderecos;

	public Cliente() {
	}

	public Cliente(Long id, String nome, String cpf, LocalDate dataNascimento, LocalDate dataAdesao, String email, List<Endereco> enderecos) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataAdesao = dataAdesao;
		this.email = email;
		this.enderecos = enderecos;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataAdesao() {
		return dataAdesao;
	}

	public void setDataAdesao(LocalDate dataAdesao) {
		this.dataAdesao = dataAdesao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}

