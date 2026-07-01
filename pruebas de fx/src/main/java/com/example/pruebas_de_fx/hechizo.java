package com.example.pruebas_de_fx;

/**
 * Hechizo: bola de fuego. Consume 3 puntos de maná.
 */
class BoladeFuego implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Bola de Fuego"; }

    @Override
    public int getCosteMana() { return 3; }

    /**
     * Inflige daño base de 40 más la inteligencia del lanzador.
     *
     * @param lanzador jugador que ejecuta el hechizo.
     * @param objetivo entidad que recibe el daño.
     */
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int daniobase = 40 + lanzador.inteligencia;
        System.out.println("¡Una explosión de fuego alcanza a " + objetivo.nombre + "!");
        objetivo.recibirDanio(daniobase);
    }
}

/**
 * Hechizo: flecha de alma. Consume 1 punto de maná.
 */
class FlechadeAlma implements Hechizos {
    @Override
    public String getNombreHechizo() {return "Flecha de alma";}
    @Override
    public int getCosteMana() {return 1;}

    /**
     * Inflige daño base de 25 más la inteligencia del lanzador.
     *
     * @param lanzador jugador que ejecuta el hechizo.
     * @param objetivo entidad que recibe el daño.
     */
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo){
        int daniobase = 25 + lanzador.inteligencia;
        System.out.println("La flecha del alma le dio a "+objetivo.nombre);
        objetivo.recibirDanio(daniobase);
    }
}

/**
 * Hechizo lanza relámpago. Consume 7 puntos de maná.
 */
class LanzaRelampago implements Hechizos {
    @Override
    public String getNombreHechizo() {return "Lanza Relampago";}
    @Override
    public int getCosteMana() {return 7;}

    /**
     * Inflige daño base de 70 más la inteligencia del lanzador.
     *
     * @param lanzador jugador que ejecuta el hechizo.
     * @param objetivo entidad que recibe el daño.
     */
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo){
        int daniobase = 70 + lanzador.inteligencia;
        System.out.println("Los cielos golpearon a "+objetivo.nombre);
        objetivo.recibirDanio(daniobase);
    }
}

/**
 * Hechizo resplandor final. Consume 8
 * puntos de maná.
 */
class Resplandor_Final implements Hechizos{
    @Override
    public String getNombreHechizo(){return "Resplandor Final";}
    @Override
    public int getCosteMana(){return 8;}

    /**
     * Inflige daño base de 100 más la inteligencia del lanzador.
     *
     * @param lanzador jugador que ejecuta el hechizo.
     * @param objetivo entidad que recibe el daño.
     */
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int daniobase = 100 + lanzador.inteligencia;
        System.out.println("Muere Insecto!!");
        objetivo.recibirDanio(daniobase);
    }
}

/**
 * Hechizo ofensivo adicional (sin uso actual confirmado en el resto del
 * código). Consume 10 puntos de maná e inflige el daño más alto del juego.
 */
class Nicos_attack implements Hechizos{
    @Override
    public String getNombreHechizo(){return "Nicos";}
    @Override
    public int getCosteMana(){return 10;}

    /**
     * Inflige daño base de 200 más la inteligencia del lanzador.
     *
     * @param lanzador jugador que ejecuta el hechizo.
     * @param objetivo entidad que recibe el daño.
     */
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int daniobase = 200 + lanzador.inteligencia;
        System.out.println("Muere Insecto!!");
        objetivo.recibirDanio(daniobase);
    }

}
