import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuVenda {

    public static void enviarNotificacao(Venda c) {
        try {
            System.out.println(Thread.currentThread().getName()
                    + " enviando e-mail para: " + c.getCliente().getNome());
            Thread.sleep(1000); // simula trabalho
            System.out.println(Thread.currentThread().getName()
                    + " e-mail enviado: " + c.getCliente().getNome());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void entregar(Scanner sc, List<Venda> listaVenda){
        Venda venda = findVenda(sc, listaVenda);

        if (venda != null){
            if (venda.getStatus() == "Pago"){

                venda.setStatus("Finalizado");

                //entregar e notificar cliente
                Thread t = new Thread(() -> enviarNotificacao(venda));
                t.setName("Worker-Entrega");
                t.start();

                exibirResumo(venda);
            } else {
                System.out.println("Não é possível entregar uma venda sem pagamento ("+venda.getStatus()+")");
            }

        } else {
            System.out.println("Não existe venda para o código informado!");
        }
    }

    private static void pagamento(Scanner sc, List<Venda> listaVenda){
        Venda venda = findVenda(sc, listaVenda);

        if (venda != null){
            if (venda.getStatus() == "Aguardando pagamento"){

                venda.setStatus("Pago");

                //pagar e notificar cliente
                Thread t = new Thread(() -> enviarNotificacao(venda));
                t.setName("Worker-Pagamento");
                t.start();

                exibirResumo(venda);
            } else {
                System.out.println("Não é possível efetuar o pagamento de venda não finalizada ("+venda.getStatus()+")");
            }

        } else {
            System.out.println("Não existe venda para o código informado!");
        }
    }

    private static void finalizarVenda(Scanner sc, List<Venda> listaVenda){
        Venda venda = findVenda(sc, listaVenda);

        if (venda != null){
            //a venda tem pelo menos 1 item
            if (venda.getItens().size() > 0){
                venda.setStatus("Aguardando pagamento");
                exibirResumo(venda);
            } else {
                System.out.println("Não é possível finalizar uma venda sem item.");
            }

        } else {
            System.out.println("Não existe venda para o código informado!");
        }
    }

    private static void itemExcluir(Scanner sc, List<Venda> listaVenda, List<ItemVenda> listaItemVenda) {
        Venda venda = findVenda(sc, listaVenda);

        if (venda != null){
            //a venda tem pelo menos 1 item
            if (venda.getItens().size() > 0){
                System.out.print("Informe o código do item a ser excluído: ");
                int codigoItem = sc.nextInt();

                for (int i = 0; i < listaItemVenda.size(); i++) {
                    ItemVenda itemVenda = listaItemVenda.get(i);
                    if (codigoItem == itemVenda.getCodigo()) {
                        venda.removerItem(itemVenda);
                        break;
                    }
                }
            } else {
                System.out.println("Não existe itens para a venda informada.");
            }

        } else {
            System.out.println("Não existe venda para o código informado!");
        }

    }

    private static void listarItem(Scanner sc, List<Venda> listaVenda){
        Venda venda = findVenda(sc, listaVenda);

        if (venda != null){
            //a venda tem pelo menos 1 item
            if (venda.getItens().size() > 0){
                exibirResumo(venda);
            } else {
                System.out.println("Não existe itens para a venda informada.");
            }

        } else {
            System.out.println("Não existe venda para o código informado!");
        }
    }

    private static void exibirResumo(Venda venda){
        venda.exibirResumo();
    }

    private static void itemInserir(Scanner sc,
                                    List<Venda> listaVenda,
                                    List<Produto> listaProduto,
                                    List<ItemVenda> listaItemVenda){

        Venda venda = findVenda(sc, listaVenda);
        Produto produto = findProduto(sc, listaProduto);

        if ((venda != null) && (produto != null)){
            if(venda.getStatus() == "Aberto") {
                System.out.print("Informe a quantidade: ");
                int quantidade = sc.nextInt();

                if (quantidade > 0) {
                    ItemVenda itemVenda = venda.adicionarItem(produto, quantidade, produto.getValor());
                    //tem que colocar o codigo do item na lista para poder excluir
                    listaItemVenda.add(itemVenda);
                    exibirResumo(venda);
                } else {
                    System.out.println("Digite uma quantidade maior que zero");
                }
            } else {
                System.out.println("Status não permite inclusão de item.(" + venda.getStatus() + ")");
            }
            //return venda;
        }
       // return null;
    }

    private static Produto findProduto(Scanner sc, List<Produto> listaProduto){
        System.out.print("Informe o código do produto: ");
        int codigoProduto = sc.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < listaProduto.size(); i++) {
            Produto produto = listaProduto.get(i);
            if (codigoProduto == produto.getCodigo()) {
                return produto;
            }
        }
        return null;
    }

    private static Venda findVenda(Scanner sc, List<Venda> listaVenda){
        System.out.print("Informe o código da venda: ");
        int codigoVenda = sc.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < listaVenda.size(); i++) {
            Venda venda = listaVenda.get(i);
            if (codigoVenda == venda.getCodigo()) {
                return venda;
            }
        }
        return null;
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

    static void subMenuVendas(Scanner sc,
                              List<Cliente> listaCliente,
                              List<Venda> listaVenda,
                              List<Produto> listaProduto,
                              List<ItemVenda> listaItemVenda){
        boolean voltar = false;
        //Venda vendaGlobal = null;

        while (!voltar) {
            //monta o menu para escolha
            System.out.println(" --- SUBMENU VENDAS --- ");
            System.out.println("1 - Criar Pedido");
            System.out.println("2 - Adicionar Item(s)");
            System.out.println("3 - Remover Item(s)");
            System.out.println("4 - Listar Item(s)");
            System.out.println("5 - Finalizar pedido");
            System.out.println("6 - Pagamento");
            System.out.println("7 - Entrega");
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
                    itemInserir(sc, listaVenda, listaProduto, listaItemVenda);
                    //exibirResumo(vendaGlobal);
                    break;
                case 3:
                    itemExcluir(sc, listaVenda, listaItemVenda);
                    //exibirResumo(vendaGlobal);
                    break;
                case 4:
                    listarItem(sc, listaVenda);
                    break;
                case 5:
                    finalizarVenda(sc, listaVenda);
                    break;
                case 6:
                    pagamento(sc, listaVenda);
                    break;
                case 7:
                    entregar(sc, listaVenda);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}