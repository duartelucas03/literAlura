package br.com.alura.literAlura.service;

import br.com.alura.literAlura.model.Autor;
import br.com.alura.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;


    @Transactional(readOnly = true)
    public List<String> listarAutores() {
        List<Autor> autores = autorRepository.findAll();

        return autores.stream().map(autor -> {
            String livros = autor.getLivros().stream()
                    .map(livro -> livro.getTitulo())
                    .collect(Collectors.joining(", "));

            return "Autor: " + autor.getNome() + "\n" +
                    "Ano de nascimento: " + autor.getAnoNascimento() + "\n" +
                    "Ano de falecimento: " + (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "N/A") + "\n" +
                    "Livros: [" + livros + "]\n";
        }).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<String> listarAutoresVivosNoAno(int ano) {
        List<Autor> autores = autorRepository.findAll();

        return autores.stream()
                .filter(autor -> autor.getAnoNascimento() != null
                        && autor.getAnoFalecimento() != null
                        && autor.getAnoNascimento() <= ano
                        && autor.getAnoFalecimento() > ano)
                .map(autor -> {
                    String livros = autor.getLivros().stream()
                            .map(livro -> livro.getTitulo())
                            .collect(Collectors.joining(", "));

                    return "Autor: " + autor.getNome() + "\n" +
                            "Ano de nascimento: " + autor.getAnoNascimento() + "\n" +
                            "Ano de falecimento: " + (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "N/A") + "\n" +
                            "Livros: [" + livros + "]\n";
                })
                .collect(Collectors.toList());
    }
}
