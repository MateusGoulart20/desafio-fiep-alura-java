package br.desafio.lambda.fiep.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarca(  
    @JsonProperty("codigo") // lê e escreve por esse campo.
    Integer codigo,
    @JsonProperty("nome") // lê e escreve por esse campo.
    String nome
) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
}
