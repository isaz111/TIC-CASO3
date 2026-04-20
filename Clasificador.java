
public class Clasificador extends Thread {
    private MonitorEventos clasificacion;
    private MonitorEventos[] consolidacion;
    private ControlClasificadores control;

    public Clasificador(MonitorEventos clasificacion, MonitorEventos[] consolidacion, ControlClasificadores control) {
        this.clasificacion = clasificacion;
        this.consolidacion = consolidacion;
        this.control = control;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Evento e = clasificacion.revisarEvento();
                if (e.isEsFin()) {
                    System.out.println("Clasificador recibió FIN");
                    control.registrarFin();
                    return;
                }
                consolidacion[e.getServidorDestino() - 1].generarEvento(e);
                System.out.println("Clasificador envía evento " + e.getId() + " al servidor " + e.getServidorDestino());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
