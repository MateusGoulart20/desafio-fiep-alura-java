package br.desafio.lambda.fiep.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();
    // List<MyClass> myObjects = mapper.readValue(jsonInput, new TypeReference<List<MyClass>>(){});

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }

    @Override
    public <T> List<T> obterDadosLista(String jsonLista, Class<T> classe) {
        CollectionType lsita = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(jsonLista, lsita);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return separarJsons(jsonLista).stream()
            .map(json -> obterDados(json, classe))
            .collect(Collectors.toList());
        /*
         * 
         List<String> listJson= separarJsons(jsonLista);
         List<T> listObjects = new ArrayList<>();
         
         for(String json : listJson ){
            listObjects.add(obterDados(json, classe));
        }
        return listObjects;
        */
    } 

    private static List<String> separarJsons(String jsonList) {
        List<String> jsons = new ArrayList<>();
        int contadorChaves = 0;
        int inicio = -1;

        for (int i = 0; i < jsonList.length(); i++) {
            char c = jsonList.charAt(i);
            if (c == '{') {
                if (contadorChaves == 0) {
                    inicio = i;
                }
                contadorChaves++;
            } else if (c == '}') {
                contadorChaves--;
                if (contadorChaves == 0 && inicio != -1) {
                    jsons.add(jsonList.substring(inicio, i + 1));
                    inicio = -1;
                }
            }
        }

        return jsons;
    }



}
