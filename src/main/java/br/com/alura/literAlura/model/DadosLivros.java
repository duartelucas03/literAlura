package br.com.alura.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("languages") List<String> idioma) {

    public String toString(){

        String autor = autores.stream()
                .map(DadosAutor::nome)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Desconhecido");

        return "-------------- Livro ----------------\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Idioma: " + String.join(", ", idioma) + "\n" +
                "--------------------------------------";
    }
}
