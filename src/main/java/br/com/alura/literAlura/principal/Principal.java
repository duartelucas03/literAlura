package br.com.alura.literAlura.principal;

import br.com.alura.literAlura.model.DadosLivros;
import br.com.alura.literAlura.model.Livro;
import br.com.alura.literAlura.model.LivroWrapper;
import br.com.alura.literAlura.service.AutorService;
import br.com.alura.literAlura.service.ConsumoApi;
import br.com.alura.literAlura.service.ConverteDados;
import br.com.alura.literAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private final String ENDERECO = "http://gutendex.com/books/?search=";
    Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    @Autowired
    private LivroService livroService;
    @Autowired
    private AutorService autorService;


    public void exibeMenu() {
        var menu = """
                --------------------------------------------
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em determinado idioma
                
                0 - Sair                                 
                --------------------------------------------
                """;
        var opcao = -1;
        while (opcao != 0) {

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    getDadosLivro();
                    break;
                case 2:
                    buscarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosPeriodo();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");

            }

        }

    }

    public void getDadosLivro() {
        System.out.println("Digite o nome do livro para busca");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        try {
            // Converte o JSON em uma lista de DadosLivros
            LivroWrapper dados = conversor.obterDados(json, LivroWrapper.class);

            if (dados.livros() != null && !dados.livros().isEmpty()) {
                for (DadosLivros livro : dados.livros()) {
                    System.out.println(livro);
                    System.out.println("\n");

                    if (livroService.buscarPorTitulo(livro.titulo()).isEmpty()) {
                        livroService.salvarLivro(livro);
                    } else {
                        System.out.println("O livro '" + livro.titulo() + "' já se encontra no banco de dados.");
                    }
                }
            } else {
                System.out.println("Nenhum resultado encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar dados da API: " + e.getMessage());
        }
    }

    private void buscarLivros() {
        List<String> livros = livroService.listarLivros();
        if (!livros.isEmpty()) {
            for (String livro : livros) {
                System.out.println(livro);
            }
        } else {
            System.out.println("Nenhum livro registrado!");
        }
    }

    private void listarAutores() {
        List<String> autores = autorService.listarAutores();
        if (!autores.isEmpty()) {
            for (String autor : autores) {
                System.out.println(autor);
            }
        } else {
            System.out.println("Nenhum autor registrado!");
        }
    }

    private void listarAutoresVivosPeriodo() {
        System.out.println("Digite o ano que deseja buscar:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<String> autoresVivos = autorService.listarAutoresVivosNoAno(ano);
        if (!autoresVivos.isEmpty()) {
            for (String autor : autoresVivos) {
                System.out.println(autor);
            }
        } else {
            System.out.println("Nenhum autor vivo em " + ano + "." );
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Insira o idioma que deseja realizar a busca:");
        System.out.println("es- espanhol");
        System.out.println("en- inglês");
        System.out.println("pt- português");

        String idiomaEscolhido = leitura.nextLine();


        List<Livro> livros = livroService.buscarLivrosPorIdioma(idiomaEscolhido);
        if (!livros.isEmpty()) {
            for (Livro livro : livros) {
                System.out.println("-------------- Livro ----------------");
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autores: " + livro.getAutores().stream()
                        .map(autor -> autor.getNome())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("Nenhum autor encontrado"));
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("Não existem livros nesse idioma no banco de dados.");
        }
    }





}