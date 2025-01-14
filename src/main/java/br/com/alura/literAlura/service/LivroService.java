package br.com.alura.literAlura.service;

import br.com.alura.literAlura.model.Autor;
import br.com.alura.literAlura.model.DadosLivros;
import br.com.alura.literAlura.model.Livro;
import br.com.alura.literAlura.repository.AutorRepository;
import br.com.alura.literAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void salvarLivro(DadosLivros dadosLivro) {

        Optional<Livro> livroExistente = livroRepository.findByTitulo(dadosLivro.titulo());

        if (livroExistente.isPresent()) {
            System.out.println("O livro '" + dadosLivro.titulo() + "' já está registrado no banco de dados.");
            return;
        }

        Livro livro = new Livro();
        livro.setTitulo(dadosLivro.titulo());


        livro.setIdioma(dadosLivro.idioma().isEmpty() ? null : dadosLivro.idioma().get(0));


        List<Autor> autores = dadosLivro.autores().stream().map(dadosAutor -> {
            return autorRepository.findByNome(dadosAutor.nome())
                    .orElseGet(() -> {
                        Autor novoAutor = new Autor();
                        novoAutor.setNome(dadosAutor.nome());
                        novoAutor.setAnoNascimento(dadosAutor.anoNascimento());
                        novoAutor.setAnoFalecimento(dadosAutor.anoFalecimento());
                        return novoAutor;
                    });
        }).collect(Collectors.toList());


        autorRepository.saveAll(autores.stream().filter(autor -> autor.getId() == null).collect(Collectors.toList()));
        livro.setAutores(autores);


        livroRepository.save(livro);
    }


    @Transactional(readOnly = true)
    public List<String> listarLivros() {
        List<Livro> livros = livroRepository.findAll();


        for (Livro livro : livros) {
            livro.getAutores().size();
        }


        return livros.stream().map(livro -> {
            String autores = livro.getAutores().stream()
                    .map(Autor::getNome)
                    .collect(Collectors.joining(", "));

            return "-------------- Livro ----------------\n" +
                    "Título: " + livro.getTitulo() + "\n" +
                    "Autores: " + autores + "\n" +
                    "Idioma: " + livro.getIdioma() + "\n" +
                    "--------------------------------------";
        }).collect(Collectors.toList());
    }


    public Optional<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }


    @Transactional(readOnly = true)
    public List<Livro> buscarLivrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepository.findByIdiomaWithAutores(idioma);


        return livros;
    }
}
