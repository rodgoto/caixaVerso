import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        List<Cliente> listaCliente = new ArrayList<>();
        List<Produto> listaProduto = new ArrayList<>();
        List<Venda> listaVenda = new ArrayList<>();

        while (continuar) {
            //monta o menu para escolha
            System.out.println(" === MENU PRINCIPAL ===");
            System.out.println("1 - Clientes");
            System.out.println("2 - Produtos");
            System.out.println("3 - Vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção digitada não é número.");
                sc.nextLine();
            }
            switch (opcao) {
                case 0:
                    continuar = false;
                    break;
                case 1:
                    MenuCliente.subMenuClientes(sc, listaCliente);
                    break;
                case 2:
                    MenuProduto.subMenuProdutos(sc, listaProduto);
                    break;
                case 3:
                    MenuVenda.subMenuVendas(sc, listaCliente, listaVenda);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        sc.close();
    }
}