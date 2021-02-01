package com.wolfkologeski.bloodbank.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CandidateDTO {

    private Long candidateId;

    private String nome;

    private String cpf;

    private String rg;

    private String data_nasc;

    private String sexo;

    private String mae;

    private String pai;

    private String email;

    private String cep;

    private String endereco;

    private int numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String telefone_fixo;

    private String celular;

    private double altura;

    private int peso;

    private String tipo_sanguineo;
}
