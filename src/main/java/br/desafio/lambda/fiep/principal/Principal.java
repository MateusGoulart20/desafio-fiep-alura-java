package br.desafio.lambda.fiep.principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.desafio.lambda.fiep.model.DadosAno;
import br.desafio.lambda.fiep.model.DadosMarca;
import br.desafio.lambda.fiep.model.DadosModelo;
import br.desafio.lambda.fiep.model.DadosModeloAno;
import br.desafio.lambda.fiep.model.DadosVeiculo;
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
        String resposta ="", ano ="";
        int menu = 1;
        int tipo = 0; // carro = 1. moto = 2. caminhao = 3. 
        Integer marca =0, modelo=0;
        List<Object> lista;

        while (menu != 0){
            System.out.println("Digite 'voltar' para voltar um menu ou digite 'sair' para sair do menu");
            switch (menu) {
                case 1:
                    System.out.println("1. Que tipo de veiculo deseja? ( carro / moto / caminhao )");
                    resposta = leitura.nextLine().trim().toLowerCase();
                    switch (resposta) {
                        case "carro":
                        case "carros":
                            System.out.println("Carregando lista de marcas de carros.");
                            buscarCarro();
                            menu++; tipo = 1;
                        break;
                        case "moto":
                        case "motos":
                            System.out.println("Carregando lista de marcas de motos.");
                            
                            menu++; tipo = 2;
                        break;
                        case "caminhao":
                        case "caminhoes":
                            System.out.println("Carregando lista de marcas de caminhoes.");
                            
                            menu++; tipo = 3;
                        break;
                        case "voltar":
                        case "sair":
                        break;
                        default:
                            System.out.println("Não entendi.");
                            break;
                    }
                break;
                case 2:
                    System.out.println("2. Qual marca deseja ver? (codigo) ");
                    resposta = leitura.nextLine().trim().toLowerCase();
                    try {
                        marca = Integer.parseInt(resposta.trim());
                        if(tipo==1 && !buscarCarroModelo(marca).isEmpty()) menu++;
                        if(tipo==2){}
                        if(tipo==3){}
                    } catch (NumberFormatException e) {
                        switch (resposta){
                            case "voltar":
                            case "sair":
                            break;
                            default: 
                            System.out.println("?");
                            break;
                        }
                    }
                break;
                case 3: 
                    System.out.println("3. Qual modelo deseja ver? (codigo) ");
                    resposta = leitura.nextLine().trim().toLowerCase();
                    try {
                        modelo = Integer.parseInt(resposta.trim());
                        if(tipo==1 && !buscarCarroModeloAno(marca, modelo).isEmpty() ) menu++;
                        if(tipo==2){}
                        if(tipo==3){}
                    } catch (NumberFormatException e) {
                        switch (resposta){
                            case "voltar":
                            case "sair":
                            break;
                            default: 
                            System.out.println("?");
                            break;
                        }
                    }
                break;
                case 4: 
                    System.out.println("4. De qual ano deseja ver? (codigo) ");
                    ano = leitura.nextLine().trim().toLowerCase();
                
                    if(tipo==1 && buscarCarroModeloAnoVeiculo(marca, modelo, ano).tipo() !=null) menu=1;
                    if(tipo==2){}
                    if(tipo==3){}
                
                    switch (resposta){
                        case "voltar":
                        case "sair":
                        break;
                        default: 
                        System.out.println("?");
                        break;
                    }

                break;
                default:break;
                }
            
            //"Digite 'voltar' para voltar um menu ou digite 'sair' para sair do menu"    
            switch (resposta) {
                case "sair": menu=0; break;
                case "voltar": menu--; break;
            }
        }
        System.out.println("Adeus");
        
        
        //  buscarCarro();
        
        //numero = leitura.nextInt();
        marca = 56;
        //buscarCarroModelo(marca);
        modelo = 9984;
        // buscarCarroModeloAno(marca, modelo)
        ano = "2024-1";
        

    }

    private DadosVeiculo buscarCarroModeloAnoVeiculo(Integer marca, Integer modelo, String ano){
        String json = consumo.obterDados(ENDERECO + CARRO + marca + MODELOS + modelo + ANOS + ano);
        //System.out.println("json:\n"+json);
        DadosVeiculo dadosVeiculo = conversor.obterDados(json, DadosVeiculo.class);
        System.out.println(dadosVeiculo);
        //dadosModeloLista = Arrays.asList(dadosModeloAno.modelos());
        //if(dadosVeiculo.isEmpty() || dadosVeiculo.get(0).tipo() == null) return new ArrayList<>();
        //dadosVeiculo.stream().forEach(System.out::println);
        return dadosVeiculo;
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
        System.out.println(dadosModeloAno);
        if (dadosModeloAno == null) return new ArrayList<>();
        if (dadosModeloAno.modelos() == null) return new ArrayList<>();
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
