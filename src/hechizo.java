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
class FlechadeAlma implements Hechizos {
    @Override
    public String getNombreHechizo() {return "Flecha de alma";}
    @Override
    public int getCosteMana() {return 1;}
    @Override
    public void aplicarEfecto(Entidad objetivo){
        System.out.println("La flecha del alma le dio a "+objetivo.nombre);
        objetivo.recibirDanio(15);
    }
}

class LanzaRelampago implements Hechizos {
    @Override
    public String getNombreHechizo() {return "Lanza Relampago";}
    @Override
    public int getCosteMana() {return 7;}
    @Override
    public void aplicarEfecto(Entidad objetivo){
        System.out.println("Los cielos golpearon a "+objetivo.nombre);
        objetivo.recibirDanio(40);
    }
}