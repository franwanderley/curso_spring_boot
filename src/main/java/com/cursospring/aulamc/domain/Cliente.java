package com.cursospring.aulamc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cursospring.aulamc.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nome;
   private String email;
   private String CpfOuCnpj;
   private Integer tipo;
   
   @OneToMany(mappedBy = "cliente")
   private List<Endereco> enderecos = new ArrayList<>();
   @ElementCollection
   @CollectionTable(name = "TELEFONE")
   //Entidade fraca
   private Set<String> telefones = new HashSet<>(); 
   
   public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
      this.id = id;
      this.nome = nome;
      this.email = email;
      this.CpfOuCnpj = cpfOuCnpj;
      this.tipo = tipo.getCod();
   }
   public Set<String> getTelefones() {
      return telefones;
   }
   public void setTelefones(Set<String> telefones) {
      this.telefones = telefones;
   }
   public List<Endereco> getEnderecos() {
      return enderecos;
   }
   public void setEnderecos(List<Endereco> enderecos) {
      this.enderecos = enderecos;
   }
   public TipoCliente getTipo() {
      return TipoCliente.toEnum(tipo);
   }
   public void setTipo(TipoCliente tipo) {
      this.tipo = tipo.getCod();
   }
   public String getCpfOuCnpj() {
      return CpfOuCnpj;
   }
   public void setCpfOuCnpj(String cpfOuCnpj) {
      this.CpfOuCnpj = cpfOuCnpj;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getNome() {
      return nome;
   }
   public void setNome(String nome) {
      this.nome = nome;
   }
   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
      this.id = id;
   }
   public Cliente() {
   }
}
