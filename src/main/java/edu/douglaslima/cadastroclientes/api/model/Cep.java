package edu.douglaslima.cadastroclientes.api.model;

public record Cep(String cep, String logradouro, String complemento, String unidade, String bairro, String localidade, String uf, Integer ibge, String gia, Integer ddd, String siafi) {

}
