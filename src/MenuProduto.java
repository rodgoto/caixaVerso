import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuProduto {

    private static void produtoAlterar(Scanner sc, List<Produto> listaProduto) {
        System.out.print("Informe o código do produto a ser alterado: ");
        int codigoProduto = sc.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < listaProduto.size(); i++) {
            Produto produto = listaProduto.get(i);
            if (codigoProduto == produto.getCodigo()) {
                encontrado = true;

                sc.nextLine();
                System.out.print("Nome: ");
                String nomeProduto = sc.nextLine();
                System.out.print("Valor R$ ");
                String valorProdutoStr = sc.nextLine();
                BigDecimal valorProduto = new BigDecimal(valorProdutoStr);
                produto.setNome(nomeProduto);
                produto.setValor(valorProduto);
                System.out.println("Produto atualizado com sucesso.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Código do produto não encontrado!");
        }
    }

    private static void produtoListar(List<Produto> listaProduto){
        for (Object produto : listaProduto) {
            System.out.println(produto.toString());
        }
    }

    private static void produtoInserir(Scanner sc, List<Produto> listaProduto){

        System.out.print("Nome: ");
        String nomeProduto = sc.nextLine();
        System.out.print("Valor R$ ");
        String valorProdutoStr = sc.nextLine();
        BigDecimal valorProduto = new BigDecimal(valorProdutoStr);

        int codigoProduto = 1;
        if (!listaProduto.isEmpty()) {
            Produto ultimoProduto = (Produto) listaProduto.get(listaProduto.size() - 1);
            codigoProduto = ultimoProduto.getCodigo() + 1;
        }
        Produto produto = new Produto(codigoProduto, nomeProduto, valorProduto);
        System.out.println("Produto inserido com sucesso.");
        listaProduto.add(produto);

    }

    static void subMenuProdutos(Scanner sc, List<Produto> listaProduto){
        boolean voltar = false;

        while (!voltar) {
            //monta o menu para escolha
            System.out.println(" --- SUBMENU PRODUTOS --- ");
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
                    produtoInserir(sc, listaProduto);
                    break;
                case 2:
                    produtoAlterar(sc, listaProduto);
                    break;
                case 3:
                    produtoListar(listaProduto);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
