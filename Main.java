import java.io.FileReader;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        if (args.length == 0){
            System.out.println("No se ha proporcionado un archivo de configuración.");
        }

        String Archivo = args[0];

        Properties properties = new Properties();
        properties.load(new FileReader(Archivo));

        int numB_Sensores = Integer.parseInt(properties.getProperty("numSensores"));
        int numBase_eventos = Integer.parseInt(properties.getProperty("numBase_eventos"));
        int numClasificadores = Integer.parseInt(properties.getProperty("numClasificadores"));
        int numServidores = Integer.parseInt(properties.getProperty("numServidores"));
        int capacidadB_clasificacion = Integer.parseInt(properties.getProperty("capacidadB_clasificacion"));
        int capacidadB_consolidacion = Integer.parseInt(properties.getProperty("capacidadB_consolidacion"));

        

    }
}    
