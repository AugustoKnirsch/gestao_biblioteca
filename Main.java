import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        biblioteca.inicializarBiblioteca();

        while (true) {
            System.out.println("--- Bem-vindo ao Sistema da Biblioteca ---");
            System.out.println("1. Ver livros disponíveis");
            System.out.println("2. Emprestar livro");
            System.out.println("3. Devolver livro");
            System.out.println("4. Adicionar novo livro");
            System.out.println("5. Listar empréstimos feitos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:                    
                    List<Livro> livrosDisponiveis = biblioteca.listarLivrosDisponiveis();
                    if (livrosDisponiveis.isEmpty()) {
                        System.out.println("Não há livros disponíveis no momento.");
                    } else {
                        System.out.println("Livros disponíveis:");
                        for (Livro livro : livrosDisponiveis) {
                            System.out.println(livro.getId() + ": " + livro.getTitulo());
                        }
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID do livro que você deseja emprestar: ");
                    int idLivroEmprestimo = scanner.nextInt();
                    scanner.nextLine();
                    Livro livroSelecionado = biblioteca.buscarLivroPorId(idLivroEmprestimo);
                    if (livroSelecionado != null && livroSelecionado.isDisponivel()) {
                        System.out.print("Digite seu nome: ");
                        String nomeUsuario = scanner.nextLine();
                        biblioteca.emprestarLivro(livroSelecionado, nomeUsuario);
                        System.out.println("O livro " + livroSelecionado.getTitulo() + " foi emprestado para " + nomeUsuario);
                    } else {
                        System.out.println("Livro não encontrado ou não disponível para empréstimo.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do empréstimo que você deseja devolver: ");
                    int idEmprestimoDevolucao = scanner.nextInt();
                    scanner.nextLine();
                    biblioteca.devolverLivro(idEmprestimoDevolucao);
                    System.out.println("Livro devolvido com sucesso.");
                    break;

                case 4:
                    System.out.print("Digite o título do livro: ");
                    String tituloLivro = scanner.nextLine();
                    System.out.print("Digite o nome do autor: ");
                    String nomeAutor = scanner.nextLine();
                    biblioteca.adicionarLivro(tituloLivro, nomeAutor);
                    System.out.println("Livro " + tituloLivro + " adicionado com sucesso.");
                    break;

                case 5:
                    biblioteca.listarEmprestimosDetalhados();
                    break;

                case 6:
                    System.out.println("Obrigado por utilizar o sistema da biblioteca.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }
}
