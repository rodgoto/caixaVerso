import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClienteProcessadorCsv {
    //adicionar lista de compromissos no CSV
    //Path - toda vez que se trabalha com arquivo precisa do path
    public static void addToCsv(Path path, List<Cliente> clientes){
        //verificar se o arquivo existe
        boolean arquivoExists = Files.exists(path);

        //BufferedWriter - escreve tudo junto ao inves do FileWriter que vai e volta
        //passa dentro do try pq ja fecha assim q terminar o uso
        try(BufferedWriter write = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE,              //se o arquivo nao existir vai criar
                StandardOpenOption.TRUNCATE_EXISTING)){

            //if(!arquivoExists){
                write.write("codigo,nome,documento");
                write.newLine();
            //}

            for(Cliente cliente : clientes){
                write.write(toCsvLine(cliente));
                write.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //leitura do arquivo Csv
    public static List<Cliente> readFromCsv(Path path) throws IOException {

        //se nao existir o arquivo, retorna um arraylist vazio
        if(!Files.exists(path)){
            return new ArrayList<>();
        }

        try(Stream<String> lines = Files.lines(path)){
            return lines.map(String::trim)
                    .filter(l -> !l.isEmpty() && !l.startsWith("#")) //ignorar se começar vazio e se tiver #
                    .filter(l -> !l.toLowerCase().startsWith("codigo")) //ignorar a linha se comecar com codigo
                    .map(ClienteProcessadorCsv::parseCsvLine) // transforma um em outro
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    //optional quer dizer que pode ou nao ter um cliente
    //cada linha do csv, vai pegar a linha toda e ler os campos individualmente e criar o objeto Compromisso
    public static Optional<Cliente> parseCsvLine(String line){
        try{
            //toda vez que encontrar a virgula, vai quebrar
            //-1 - vai pegar o campo mesmo se estiver vazio
            String[] parts = line.split(",", -1);

            //"nome,documento"
            //se estiver faltando um elemento, retorna vazio
            if (parts.length < 3){
                return Optional.empty();
            }

            int codigo = Integer.parseInt(parts[0]);
            String nome = parts[1].trim();
            String documento = parts[2].trim();

            return Optional.of(new Cliente(codigo, nome, documento));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    //formata a linha
    private static String toCsvLine(Cliente cliente){
        return String.join(",",
                String.valueOf(cliente.getCodigo()),
                cliente.getNome(),
                cliente.getDocumento()
        );
    }
}
