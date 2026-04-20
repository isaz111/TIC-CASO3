
public class Clasificador extends Thread {
    private int idClasificador;
    private MonitorEventos clasificacion;
    private MonitorEventos[] consolidacion;
    private ControlClasificadores control;

    public Clasificador(MonitorEventos clasificacion, MonitorEventos[] consolidacion, ControlClasificadores control, int idClasificador) {
        this.clasificacion = clasificacion;
        this.consolidacion = consolidacion;
        this.control = control;
        this.idClasificador = idClasificador;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Evento e = clasificacion.revisarEvento();
                if (e.isEsFin()) {
                    System.out.println("Clasificador " + idClasificador + " recibió FIN");
                    control.registrarFin();
                    return;
                }
                consolidacion[e.getServidorDestino() - 1].generarEvento(e);
                System.out.println("Clasificador " + idClasificador + " envía evento " + e.getId() + " al servidor " + e.getServidorDestino());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
