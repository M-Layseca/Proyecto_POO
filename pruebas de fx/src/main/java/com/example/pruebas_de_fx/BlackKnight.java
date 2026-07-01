package com.example.pruebas_de_fx;

/**
 * Enemigo "The Black Knight". Posee una probabilidad creciente de
 * provocar un golpe crítico con el doble de daño; si falla el crítico,
 * su probabilidad de acertar en el siguiente turno aumenta.
 */
public class BlackKnight extends Enemigo {

    /** Probabilidad actual (en porcentaje) de hacer un golpe crítico. */
    private int critico;

    /**
     * Crea al Black Knight con sus estadísticas fijas por defecto y una
     * probabilidad inicial de crítico del 20%.
     */
    public BlackKnight() {
        super("The Black Knight", 150, 25, 12, 0, 80);
        this.critico = 20;
    }

    /**
     * Ataca al jugador objetivo. Con probabilidad {@code critico}
     * asesta un golpe crítico (doble daño) y reinicia dicha probabilidad
     * a 20%; en caso contrario realiza un ataque normal y aumenta la
     * probabilidad de crítico en 5 puntos para el próximo turno.
     */
    @Override
    public String atacar(Jugador objetivo) {
        StringBuilder sb = new StringBuilder();
        if (random.nextInt(100) < critico) {
            sb.append(nombre).append(" saca su arma legendaria y....\n");
            sb.append(nombre).append(" ha atacado con su Espada Maldita!\n");
            sb.append(objetivo.recibirDanio(this.ataque * 2));
            this.critico = 20;
        } else {
            sb.append(nombre).append(" ha atacado con su Espada!\n");
            sb.append(objetivo.recibirDanio(this.ataque)).append("\n");
            this.critico += 5;
            sb.append("(El caballero se ha vuelto más peligroso...)");
        }
        return sb.toString();
    }
}
