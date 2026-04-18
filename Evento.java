public class Evento {
    private String id;
    private int servidorDestino;
    private boolean esFin;

    public Evento(String id, int servidorDestino, boolean esFin) {
        this.id = id;
        this.servidorDestino = servidorDestino;
        this.esFin = esFin;
    }
    public String getId() { return id; }
    public int getServidorDestino() { return servidorDestino; }
    public boolean isEsFin() { return esFin; }
}