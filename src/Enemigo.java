import java.util.Random;

public abstract class Enemigo extends Entidad{
    Random random = new Random();

    public Enemigo(String nombre, int vidaMax, int ataque, int defensa, int usosPasiva) {
        super(nombre, vidaMax, ataque, defensa, usosPasiva);
    }

    public abstract void atacar(Jugador objetivo);
}
