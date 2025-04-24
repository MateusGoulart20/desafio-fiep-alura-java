package br.desafio.lambda.fiep.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(
    @JsonProperty("TipoVeiculo")
    Integer tipo,
    @JsonProperty("Valor") 
    String valor,
    @JsonProperty("Marca") 
    String marca,
    @JsonProperty("Modelo") 
    String modelo,
    @JsonProperty("AnoModelo") 
    Integer anoModelo,
    @JsonProperty("Combustivel") 
    String combustivel,
    @JsonProperty("CodigoFipe") 
    String codigoFipe,
    @JsonProperty("MesReferencia") 
    String mesReferencia,
    @JsonProperty("SiglaCombustivel") 
    String siglaCombustivel
) {

}
