import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    
    public void adicionarLivro(String titulo, String nomeAutor) {
        Autor autor = buscarAutorPorNome(nomeAutor);
        if (autor == null) {
            autor = new Autor(autores.size() + 1, nomeAutor, new Date());
            adicionarAutor(autor);
        }
        Livro livro = new Livro(livros.size() + 1, titulo, autor);
        adicionarLivro(livro);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                livrosDisponiveis.add(livro);
            }
        }
        return livrosDisponiveis;
    }

    public Livro buscarLivroPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    public void atualizarLivro(int id, String novoTitulo) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                livro.setTitulo(novoTitulo);
                break;
            }
        }
    }

    public void removerLivro(int id) {
        livros.removeIf(livro -> livro.getId() == id);
    }

    public void adicionarAutor(Autor autor) {
        autores.add(autor);
    }

    public List<Autor> listarAutores() {
        return autores;
    }

    public Autor buscarAutorPorId(int id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }

    public Autor buscarAutorPorNome(String nome) {
        for (Autor autor : autores) {
            if (autor.getNome().equalsIgnoreCase(nome)) {
                return autor;
            }
        }
        return null;
    }

    public void atualizarAutor(int id, String novoNome) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                autor.setNome(novoNome);
                break;
            }
        }
    }

    public void removerAutor(int id) {
        autores.removeIf(autor -> autor.getId() == id);
    }

    public void emprestarLivro(Livro livro, String nomeUsuario) {
        if (livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livro, nomeUsuario);
            emprestimos.add(emprestimo);
            livro.setDisponivel(false);
        } else {
            System.out.println("Livro não disponível para empréstimo.");
        }
    }

    public void devolverLivro(int idEmprestimo) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == idEmprestimo && emprestimo.isAtivo()) {
                emprestimo.devolverLivro();
                break;
            }
        }
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }

    public Emprestimo buscarEmprestimoPorId(int id) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id) {
                return emprestimo;
            }
        }
        return null;
    }

    public void listarEmprestimosDetalhados() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }
        
        System.out.println("Empréstimos registrados:");
        for (Emprestimo emprestimo : emprestimos) {
            String status = emprestimo.isAtivo() ? "Ativo" : "Devolvido";
            System.out.println("ID: " + emprestimo.getId() +
                               ", Livro: " + emprestimo.getLivro().getTitulo() +
                               ", Usuário: " + emprestimo.getNomeUsuario() +
                               ", Status: " + status);
        }
    }

    public void inicializarBiblioteca() {
        List<Autor> autoresList = List.of(
            new Autor(1, "Gabriel García Márquez", new Date()),
            new Autor(2, "J.K. Rowling", new Date()),
            new Autor(3, "George Orwell", new Date()),
            new Autor(4, "J.R.R. Tolkien", new Date()),
            new Autor(5, "Harper Lee", new Date()),
            new Autor(6, "F. Scott Fitzgerald", new Date()),
            new Autor(7, "Jane Austen", new Date()),
            new Autor(8, "Mark Twain", new Date()),
            new Autor(9, "Ernest Hemingway", new Date()),
            new Autor(10, "Isaac Asimov", new Date())
        );

        for (Autor autor : autoresList) {
            adicionarAutor(autor);
        }

        List<Livro> livrosList = List.of(
            new Livro(1, "Cem Anos de Solidão", autoresList.get(0)),
            new Livro(2, "Harry Potter e a Pedra Filosofal", autoresList.get(1)),
            new Livro(3, "1984", autoresList.get(2)),
            new Livro(4, "O Senhor dos Anéis: A Sociedade do Anel", autoresList.get(3)),
            new Livro(5, "O Sol é para Todos", autoresList.get(4)),
            new Livro(6, "O Grande Gatsby", autoresList.get(5)),
            new Livro(7, "Orgulho e Preconceito", autoresList.get(6)),
            new Livro(8, "As Aventuras de Huckleberry Finn", autoresList.get(7)),
            new Livro(9, "O Velho e o Mar", autoresList.get(8)),
            new Livro(10, "Fundação", autoresList.get(9))
        );

        for (Livro livro : livrosList) {
            adicionarLivro(livro);
        }
    }
}
