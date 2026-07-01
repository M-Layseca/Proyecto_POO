public interface Hechizos { // Debe ser 'interface'
    String getNombreHechizo();
    int getCosteMana();
    void aplicarEfecto(Entidad objetivo); // Usamos Entidad como tipo
}
