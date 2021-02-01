package com.wolfkologeski.bloodbank.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "CANDIDATES")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CANDIDATE_ID")
    private Long candidateId;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "RG")
    private String rg;

    @Column(name = "DATA_NASC")
    private String data_nasc;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "MAE")
    private String mae;

    @Column(name = "PAI")
    private String pai;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "NUMERO")
    private int numero;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "TELEFONE_FIXO")
    private String telefone_fixo;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "ALTURA")
    private double altura;

    @Column(name = "PESO")
    private int peso;

    @Column(name = "TIPO_SANGUINEO")
    private String tipo_sanguineo;
}
