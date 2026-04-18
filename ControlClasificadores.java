public class ControlClasificadores {
    private int  terminados = 0;
    private int nc, ns;
    private MonitorEventos[] consolidacion;

    public ControlClasificadores(int nc, int ns, MonitorEventos[]  consol) {
        this.nc = nc;
        this.ns = ns;
        this.consolidacion = consol;
        }

    public synchronized void registrarFin() throws InterruptedException {
        terminados++;
        if (terminados == nc) {
            for (int i = 0; i < ns; i++) {
                Evento finEvento = new Evento("Fin", 0, true);
                consolidacion[i].generarEvento(finEvento);
            }
        }
    }

}
