
public interface Hechizos { // Debe ser 'interface'
    String getNombreHechizo();
    int getCosteMana();
    void aplicarEfecto(Entidad objetivo); // Usamos Entidad como tipo
}

class BoladeFuego implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Bola de Fuego"; }

    @Override
    public int getCosteMana() { return 3; }

    @Override
    public void aplicarEfecto(Entidad objetivo) {
        System.out.println("¡Una explosión de fuego alcanza a " + objetivo.nombre + "!");
        objetivo.recibirDanio(25); // Usamos el método de Entidad
    }
}