public class Sensores {
    private int numB_sensores;
    private int id_sensor;

    public Sensores(int numB_sensores, int id_sensor) {
        this.numB_sensores = numB_sensores;
        this.id_sensor = id_sensor;
    }

    public int eventosporGenerar(){
        int totalEventos =numB_sensores * id_sensor;
        return totalEventos;
    }

}
