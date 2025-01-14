package br.com.alura.literAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroWrapper(@JsonProperty("results") List<DadosLivros> livros) {
}
