package br.desafio.lambda.fiep.principal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.desafio.lambda.fiep.model.DadosAno;
import br.desafio.lambda.fiep.model.DadosMarca;
import br.desafio.lambda.fiep.model.DadosModelo;
import br.desafio.lambda.fiep.model.DadosModeloAno;
import br.desafio.lambda.fiep.services.ConsumoApi;
import br.desafio.lambda.fiep.services.ConverteDados;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi(); 	
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String CARRO = "carros/marcas/";
    private final String MOTO = "motos/marcas/";
    private final String CAMINHOES = "caminhoes/marcas/";
    private final String MODELOS = "/modelos/";
    private final String ANOS = "/anos/";

    public void iniciar(){
        String json, resposta, ano;
        Integer marca, modelo;
        List<DadosModelo> dadosModeloLista;
        
        //  buscarCarro();
        
        System.out.println("Que marca deseja ver? ");
        //numero = leitura.nextInt();
        marca = 56;
        //buscarCarroModelo(marca);
        modelo = 9984;
        // buscarCarroModeloAno(marca, modelo)
        ano = "2024-1";
        json = consumo.obterDados(ENDERECO + CARRO + marca + MODELOS + modelo + ANOS + ano);
        //System.out.println("json:\n"+json);
        List<DadosAno> dadosAno = conversor.obterDadosLista(json, DadosAno.class);
        //System.out.println(dadosModeloAno);
        //dadosModeloLista = Arrays.asList(dadosModeloAno.modelos());
        dadosAno.stream().forEach(System.out::println);



//        https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos
        //dadosModeloAno.modelos();
        
        //dadosModeloAno.stream().forEach(System.out::println);

    }

    private List<DadosAno> buscarCarroModeloAno(Integer marca, Integer modelo){
        String json = consumo.obterDados(ENDERECO + CARRO + marca + MODELOS + modelo + ANOS);
        //System.out.println("json:\n"+json);
        List<DadosAno> dadosAno = conversor.obterDadosLista(json, DadosAno.class);
        //System.out.println(dadosModeloAno);
        //dadosModeloLista = Arrays.asList(dadosModeloAno.modelos());
        dadosAno.stream().forEach(System.out::println);
        return dadosAno;
    }

    private List<DadosModelo> buscarCarroModelo(Integer marca){
        String json = consumo.obterDados(ENDERECO + CARRO + marca + MODELOS);
        //System.out.println("json:\n"+json);
        DadosModeloAno dadosModeloAno = conversor.obterDados(json, DadosModeloAno.class);
        //System.out.println(dadosModeloAno);
        List<DadosModelo> dadosModeloLista = Arrays.asList(dadosModeloAno.modelos());
        dadosModeloLista.stream().forEach(System.out::println);
        return dadosModeloLista;
    }

    private List<DadosMarca> buscarCarro(){
        String json;
        List<DadosMarca> dadosMarcaLista;

        json = consumo.obterDados(ENDERECO + CARRO);
        //System.out.println("json:\n"+json);
        dadosMarcaLista = conversor.obterDadosLista(json, DadosMarca.class);
        //System.out.println("dados:\n"+dadosMarcaLista);
        //System.out.println("dados.stream()");
        dadosMarcaLista.stream().forEach(System.out::println);
        return dadosMarcaLista;
    }

}
