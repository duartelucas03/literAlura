package br.com.alura.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosIdioma(@JsonAlias("languages") String idioma) {
}
