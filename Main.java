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

        BuzonSemiactivo buzonEntrada = new BuzonSemiactivo();
        BuzonSemiactivo buzonAlerta = new BuzonSemiactivo();

        MonitorEventos monitorClasificacion = new MonitorEventos(capacidadB_clasificacion);
        MonitorEventos[] monitorConsolidacion = new MonitorEventos[numServidores];
        for(int i = 0; i < numServidores;i++){
            monitorConsolidacion[i] = new MonitorEventos(capacidadB_consolidacion);
        }

        ControlClasificadores control = new ControlClasificadores(numClasificadores, numServidores, monitorConsolidacion);

        Servidores[] servidores = new Servidores[numServidores];
        for (int i = 0; i < numServidores; i++){
            servidores[i] = new Servidores(monitorConsolidacion[i],i+1);
        }

        Clasificador[] clasificadores = new Clasificador[numClasificadores];
        for (int i = 0; i < numClasificadores; i++) {
            clasificadores[i] = new Clasificador(monitorClasificacion, monitorConsolidacion, control, i+1);
        }

        Administador administrador = new Administador(numClasificadores, buzonAlerta, monitorClasificacion);

        Broker broker = new Broker(buzonEntrada, buzonAlerta, monitorClasificacion, numB_Sensores, numBase_eventos);

        Sensores[] sensores = new Sensores[numB_Sensores];
        for (int i = 0; i < numB_Sensores; i++) {
            sensores[i]  = new Sensores(numBase_eventos, i + 1, numServidores, buzonEntrada);
        }

        //inicia servidores
        for (int i = 0; i < numServidores; i++) {
            servidores[i].start();
        }
        //inicia clasificadores
        for (int i = 0; i < numClasificadores; i++) {
            clasificadores[i].start();
        }
        //admin y broker
        administrador.start();
        broker.start();
        
        //inicia sensores
        for (int i = 0; i < numB_Sensores; i++) {
            sensores[i].start(); 
        }

        //finaliza sensores
        for (Sensores sensor : sensores) {
            sensor.join();
        }

        //finaliza broker y admin
        broker.join();
        administrador.join();

        //finaliza clasificadores
        for (Clasificador c : clasificadores) {
            c.join();
        }

        //finaliza servidores
        for (int i=0; i < numServidores;i++){
            servidores[i].join();
        }

        System.out.println("El sistema finalizó correctamente");









        

    }
}    
