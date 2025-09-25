import java.io.IOException;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuCliente {

    static void subMenuClientes(Scanner sc, List<Cliente> listaCliente, Path path) throws IOException {
        boolean voltar = false;

        while (!voltar) {
            //monta o menu para escolha
            System.out.println(" --- SUBMENU CLIENTES ---");
            System.out.println("1 - Incluir");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção digitada não é número.");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 0:
                    voltar = true;
                    break;
                case 1:
                    clienteInserir(sc, listaCliente, path);
                    break;
                case 2:
                    clienteAlterar(sc, listaCliente, path);
                    break;
                case 3:
                    clienteListar(listaCliente);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void clienteAlterar(Scanner sc, List<Cliente> listaCliente, Path path) throws IOException {
        System.out.print("Informe o código do cliente a ser alterado: ");
        int codigoCliente = sc.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente cliente = listaCliente.get(i);
            if (codigoCliente == cliente.getCodigo()) {
                encontrado = true;

                sc.nextLine();
                System.out.print("Nome: ");
                String nomeCliente = sc.nextLine();

                System.out.print("Documento: ");
                String documentoCliente = sc.nextLine();

                cliente.setNome(nomeCliente);
                cliente.setDocumento(documentoCliente);
                System.out.println("Cliente atualizado com sucesso.");

                //salvar no arquivo
                CriarCsv.salvarClienteList(listaCliente, path);

                break;
            }
        }

        if (!encontrado) {
            System.out.println("Código do cliente não encontrado!");
        }
    }

    private static void clienteInserir(Scanner sc, List<Cliente> listaCliente, Path path) throws IOException {
        System.out.print("Nome: ");
        String nomeCliente = sc.nextLine();
        System.out.print("Documento: ");
        String documentoCliente = sc.nextLine();;

        int codigoCliente = 1;
        if (!listaCliente.isEmpty()) {
            Cliente ultimoCliente = (Cliente) listaCliente.get(listaCliente.size() - 1);
            codigoCliente = ultimoCliente.getCodigo() + 1;
        }
        Cliente cliente = new Cliente(codigoCliente, nomeCliente, documentoCliente);
        System.out.println("Cliente inserido com sucesso.");
        listaCliente.add(cliente);

        //salvar no arquivo
        CriarCsv.salvarClienteList(listaCliente, path);
    }

    private static void clienteListar(List<Cliente> listaCliente){
        for (Object cliente : listaCliente) {
            System.out.println(cliente.toString());
        }
    }

}