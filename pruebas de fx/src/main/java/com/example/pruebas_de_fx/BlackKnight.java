package com.example.pruebas_de_fx;

public class BlackKnight extends Enemigo {
    private int critico;

    public BlackKnight() {
        super("The Black Knight", 150, 25, 12, 0, 80);
        this.critico = 20;
    }

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