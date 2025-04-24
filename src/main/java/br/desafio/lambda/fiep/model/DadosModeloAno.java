package br.desafio.lambda.fiep.model;

import java.util.List;

//import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModeloAno(  
    @JsonProperty("modelos") // lê e escreve por esse campo.
    DadosModelo[] modelos,
    @JsonProperty("anos") // lê e escreve por esse campo.
    DadosAno[] anos
) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
}
