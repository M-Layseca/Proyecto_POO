# ⚔️ Dark Souls: POO Edition 🛡️

¡Bienvenido! Este es un juego RPG de aventuras por turnos basado en texto, desarrollado en **Java**. El proyecto aplica los pilares fundamentales de la **Programación Orientada a Objetos (POO)** para crear una experiencia de combate estratégico, exploración de mazmorras y gestión de recursos.

## 📜 Descripción del Proyecto
El jugador se adentra en una mazmorra generada mediante un sistema de orquestación. A través de 5 salas consecutivas, deberá enfrentarse a distintos enemigos usando ataques físicos, habilidades pasivas, magia y frascos de Estus para sobrevivir. Al derrotar enemigos, el jugador absorbe sus "Almas" (Experiencia) para subir de nivel y restaurar su salud antes del combate contra el Jefe Final.

## 🚀 Características Principales (Conceptos POO aplicados)
* **Herencia y Polimorfismo:** Uso de clases abstractas (`Entidad`, `Jugador`, `Enemigo`) de las que derivan clases específicas con comportamientos únicos (ej. el `GUERRERO` ataca distinto al `MAGO`; el `BlackKnight` tiene probabilidad de daño crítico).
* **Encapsulamiento:** Las estadísticas (vida, daño, maná) están protegidas y solo se modifican a través de métodos controlados (como `recibirDanio()` o `ganarExp()`).
* **Game Manager (Orquestador):** Una clase dedicada exclusivamente a dirigir el flujo del juego, instanciar las batallas y generar mapas, manteniendo la clase `Main` limpia (Separación de Responsabilidades).
* **Interfaces:** Implementación de interfaces (ej. `Hechizos`) para estandarizar el uso de la magia.

## 🧙‍♂️ Clases Jugables
Al iniciar la aventura, puedes elegir entre tres clases principales:
1. **GUERRERO:** Especialista en combate cuerpo a cuerpo. Posee un gran daño base y su pasiva le permite entrar en estado *Berserker*.
2. **MAGO:** Débil físicamente, pero con un repertorio de hechizos devastadores que consumen Maná.
3. **TANK:** Inmune al dolor. Su defensa es impenetrable, ideal para resistir combates largos.
4. **STEVE:** Creativo y con un posible gran arsenal que consigue armar con su entorno


## 👹 Bestiario
* **Hollows:** Enemigos básicos. Fáciles de derrotar, pero peligrosos en grupo.
* **Black Knights:** Caballeros letales con una alta probabilidad de asestar golpes críticos.
* **Jefes:** Seres abismales (como *El Gran Molinillo*) que custodian el final de la mazmorra.

## 🛠️ Instalación y Ejecución

Para jugar en tu propia máquina (optimizado para entornos Windows/IntelliJ):

1. **Clona el repositorio** en tu entorno local usando Git:
   ```bash
   git clone [https://github.com/M-Layseca/Proyecto_POO.git](https://github.com/M-Layseca/Proyecto_POO.git)
