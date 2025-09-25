import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        Path pathCliente = Paths.get("clientes.csv");
        Path pathProduto = Paths.get("produtos.csv");
        List<Cliente> listaCliente = ClienteProcessadorCsv.readFromCsv(pathCliente);
        List<Produto> listaProduto = ProdutoProcessadorCsv.readFromCsv(pathProduto);
        List<Venda> listaVenda = new ArrayList<>();
        List<ItemVenda> listaItemVenda = new ArrayList<>();

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
                    MenuCliente.subMenuClientes(sc, listaCliente, pathCliente);
                    break;
                case 2:
                    MenuProduto.subMenuProdutos(sc, listaProduto, pathProduto);
                    break;
                case 3:
                    MenuVenda.subMenuVendas(sc, listaCliente, listaVenda, listaProduto, listaItemVenda);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        sc.close();
    }
}