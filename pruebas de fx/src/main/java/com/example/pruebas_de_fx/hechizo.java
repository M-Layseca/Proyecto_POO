package com.example.pruebas_de_fx;

class BoladeFuego implements Hechizos {
    @Override
    public String getNombreHechizo() { return "Bola de Fuego"; }

    @Override
    public int getCosteMana() { return 3; }

    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int daniobase = 40 + lanzador.inteligencia;
        System.out.println("¡Una explosión de fuego alcanza a " + objetivo.nombre + "!");
        objetivo.recibirDanio(daniobase);
    }
}
class FlechadeAlma implements Hechizos {
    @Override
    public String getNombreHechizo() {return "Flecha de alma";}
    @Override
    public int getCosteMana() {return 1;}
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo){
        int daniobase = 25 + lanzador.inteligencia;
        System.out.println("La flecha del alma le dio a "+objetivo.nombre);
        objetivo.recibirDanio(daniobase);
    }
}

class LanzaRelampago implements Hechizos {
    @Override
    public String getNombreHechizo() {return "Lanza Relampago";}
    @Override
    public int getCosteMana() {return 7;}
    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo){
        int daniobase = 70 + lanzador.inteligencia;
        System.out.println("Los cielos golpearon a "+objetivo.nombre);
        objetivo.recibirDanio(daniobase);
    }
}
class Resplandor_Final implements Hechizos{
    @Override
    public String getNombreHechizo(){return "Resplandor Final";}
    @Override
    public int getCosteMana(){return 8;}

    @Override
    public void aplicarEfecto(Jugador lanzador, Entidad objetivo) {
        int daniobase = 100 + lanzador.inteligencia;
        System.out.println("Muere Insecto!!");
        objetivo.recibirDanio(daniobase);
    }
}