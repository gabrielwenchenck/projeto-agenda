package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    public static final String pathContatos = "/home/gabrielwenchenck/Dev/projetos/agenda-contatos/arquivo/contatos.txt";
    public static final String pathTelefones = "/home/gabrielwenchenck/Dev/projetos/agenda-contatos/arquivo/telefones.txt";
    private List<Contato> contatos;

    public Agenda() {
        this.contatos = new ArrayList<>();
        carregarDados();
    }

    private void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathContatos))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length == 3) {
                    Contato contato = new Contato();
                    contato.setId(Long.parseLong(partes[0]));
                    contato.setNome(partes[1]);
                    contato.setSobreNome(partes[2]);
                    contato.setTelefones(new ArrayList<>());
                    contatos.add(contato);
                } else if (partes.length == 4) {
                    Telefone telefone = new Telefone();
                    telefone.setId(Long.parseLong(partes[0]));
                    telefone.setDdd(partes[1]);
                    telefone.setNumero(Long.parseLong(partes[2]));
                    if (!contatos.isEmpty()) {
                        contatos.get(contatos.size() - 1).getTelefones().add(telefone);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void adicionarContato(Contato contato) {
        boolean contatoJaExistente = false;
        for (Contato c : contatos) {
            if (c.getId().equals(contato.getId())) {
                contatoJaExistente = true;
                break;
            }
        }

        boolean telefonesDuplicados = telefonesDuplicados(contato);

        if (contatoJaExistente) {
            System.out.println("Não é permitido armazenar contatos com o mesmo ID.");
        } else if (telefonesDuplicados) {
            System.out.println("Não é permitido armazenar contatos com telefones já cadastrados.");
        } else {
            contatos.add(contato);
            salvarDados();
        }
    }

    private boolean telefonesDuplicados(Contato novoContato) {
        for (Contato contatoExistente : contatos) {
            for (Telefone telefoneExistente : contatoExistente.getTelefones()) {
                for (Telefone telefoneNovo : novoContato.getTelefones()) {
                    if (telefoneExistente.getDdd().equals(telefoneNovo.getDdd()) &&
                            telefoneExistente.getNumero().equals(telefoneNovo.getNumero())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void removerContato(Long id) {
        contatos.removeIf(contato -> contato.getId().equals(id));
        salvarDados();
    }

    public void editarContato(Long id, Contato novoContato) {
        int index = -1;

        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            Contato contatoExistente = contatos.get(index);
            contatoExistente.setNome(novoContato.getNome());
            contatoExistente.setSobreNome(novoContato.getSobreNome());
            contatoExistente.setTelefones(novoContato.getTelefones());

            salvarDados();
        } else {
            System.out.println("Contato não encontrado.");
        }
    }


    public void listarContatos() {
        System.out.println(">>>> Contatos <<<<");
        System.out.println("Id | Nome");
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }

    private void escreverTelefone(Telefone telefone) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(pathTelefones, true))) {
            String linha = String.format("%d|%s|%s", telefone.getId(), telefone.getDdd(), telefone.getNumero());
            writer.println(linha);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(pathContatos))) {
            for (Contato contato : contatos) {
                writer.printf("%d|%s|%s%n", contato.getId(), contato.getNome(), contato.getSobreNome());
                for (Telefone telefone : contato.getTelefones()) {
                    escreverTelefone(telefone);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
