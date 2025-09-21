import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuVenda {

    private static void itemInserir(Scanner sc, List<Venda> listaVenda){
        System.out.print("Informe o código da venda: ");
        int codigoVenda = sc.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < listaVenda.size(); i++) {
            Venda venda = listaVenda.get(i);
            if (codigoVenda == venda.getCodigo()) {
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Código do cliente não encontrado!");
        } else { //adicionar produtos na venda

        }
    }

    private static void vendaInserir(Scanner sc, List<Cliente> listaCliente, List<Venda> listaVenda){
        System.out.print("Informe o código do cliente para o pedido: ");
        int codigoCliente = sc.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente cliente = listaCliente.get(i);
            if (codigoCliente == cliente.getCodigo()) {
                encontrado = true;

                LocalDateTime hoje = LocalDateTime.now();
                Venda venda = new Venda(cliente, hoje, "Aberto");
                listaVenda.add(venda);

                System.out.println("Pedido criado com sucesso. Código do pedido: " + venda.getCodigo());

                break;
            }
        }

        if (!encontrado) {
            System.out.println("Código do cliente não encontrado!");
        }
    }

    static void subMenuVendas(Scanner sc, List<Cliente> listaCliente, List<Venda> listaVenda){
        boolean voltar = false;

        while (!voltar) {
            //monta o menu para escolha
            System.out.println(" --- SUBMENU VENDAS --- ");
            System.out.println("1 - Criar Pedido");
            System.out.println("2 - Adicionar Item(s)");
            System.out.println("3 - Remover Item(s)");
            System.out.println("4 - Alterar Quantidade");
            System.out.println("5 - Pagamento");
            System.out.println("6 - Entrega");
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
                    vendaInserir(sc, listaCliente, listaVenda);
                    break;
                case 2:
                    itemInserir(sc, listaVenda);
                    break;
                case 3:
                    System.out.println("Remover item");
                    break;
                case 4:
                    System.out.println("Alterar quantidade");
                    break;
                case 5:
                    System.out.println("Pagamento");
                    break;
                case 6:
                    System.out.println("Entregar");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}