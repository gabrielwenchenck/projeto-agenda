import entities.Agenda;
import entities.Contato;
import entities.Telefone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static long proximoIdContato = 1;
    private static long proximoIdTelefone = 1;

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        int opcao;

        do {
            System.out.println("##################");
            System.out.println("##### AGENDA #####");
            System.out.println("##################");
            System.out.println("");
            agenda.listarContatos();
            System.out.println("");
            System.out.println(">>>> Menu <<<<");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Remover Contato");
            System.out.println("3 - Editar Contato");
            System.out.println("4 - Sair");
            System.out.print("Escolha a opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Contato novoContato = criarNovoContato();
                    agenda.adicionarContato(novoContato);
                    break;
                case 2:
                    System.out.print("Informe o ID do contato a ser removido: ");
                    Long idRemover = scanner.nextLong();
                    agenda.removerContato(idRemover);
                    break;
                case 3:
                    System.out.print("Informe o ID do contato a ser editado: ");
                    Long idEditar = scanner.nextLong();
                    scanner.nextLine();
                    Contato contatoEditado = criarNovoContato();
                    agenda.editarContato(idEditar, contatoEditado);
                    break;
            }
        } while (opcao != 4);

        scanner.close();
    }

    private static Contato criarNovoContato() {
        System.out.print("Informe o Nome do contato: ");
        String nome = scanner.nextLine();

        System.out.print("Informe o Sobrenome do contato: ");
        String sobreNome = scanner.nextLine();

        List<Telefone> telefones = new ArrayList<>();
        int opcaoTelefone;
        do {
            Telefone novoTelefone = criarNovoTelefone();
            telefones.add(novoTelefone);

            System.out.println("Deseja adicionar outro telefone? (1 - Sim, 0 - Não): ");
            opcaoTelefone = scanner.nextInt();
            scanner.nextLine();
        } while (opcaoTelefone == 1);

        return new Contato(nome, sobreNome, telefones);
    }

    private static Telefone criarNovoTelefone() {
        System.out.print("Informe o DDD do telefone: ");
        String ddd = scanner.nextLine();

        System.out.print("Informe o Número do telefone: ");
        Long numero = scanner.nextLong();
        scanner.nextLine();

        return new Telefone(ddd, numero);
    }
}
