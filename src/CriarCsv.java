import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CriarCsv {

    public static void salvarClienteList(List<Cliente> listaClientes, Path path) throws IOException {

        //comando para gravar no arquivo
        ClienteProcessadorCsv.addToCsv(path, listaClientes);
        System.out.println("Arquivo gravado em:" + path.toAbsolutePath());

        //le o arquivo
        /*List<Cliente> resultado = ClienteProcessadorCsv.readFromCsv(path);
        if(resultado.isEmpty()){
            System.out.println("Nenhum cliente encontrado");
            try(Stream<String> lines = Files.lines(path)){
                lines.forEach(System.out::println);
            }
        } else {
            resultado.forEach(System.out::println);
        }*/

    }

    public static void salvarProdutoList(List<Produto> listaProdutos, Path path) throws IOException {

        //comando para gravar no arquivo
        ProdutoProcessadorCsv.addToCsv(path, listaProdutos);
        System.out.println("Arquivo gravado em:" + path.toAbsolutePath());

        //le o arquivo
        /*List<Cliente> resultado = ClienteProcessadorCsv.readFromCsv(path);
        if(resultado.isEmpty()){
            System.out.println("Nenhum cliente encontrado");
            try(Stream<String> lines = Files.lines(path)){
                lines.forEach(System.out::println);
            }
        } else {
            resultado.forEach(System.out::println);
        }*/

    }

}
