public class ControlClasificadores {
    private int  terminados = 0;
    private int numClasificadores, numServidores;
    private MonitorEventos[] consolidacion;

    public ControlClasificadores(int numClasificadores, int numServidores, MonitorEventos[]  consol) {
        this.numClasificadores = numClasificadores;
        this.numServidores = numServidores;
        this.consolidacion = consol;
        }

    public synchronized void registrarFin() throws InterruptedException {
        terminados++;
        if (terminados == numClasificadores) {
            for (int i = 0; i < numServidores; i++) {
                Evento finEvento = new Evento("Fin", 0, true);
                consolidacion[i].generarEvento(finEvento);
            }
        }
    }

}
