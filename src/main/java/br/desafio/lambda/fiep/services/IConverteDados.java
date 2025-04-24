package br.desafio.lambda.fiep.services;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> obterDadosLista(String json, Class<T> classe);
}