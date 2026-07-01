# =============================================================================
# CONFIGURACIÓN DEL PROYECTO (WSL LINUX - ESTRUCTURA MAVEN REAL)
# =============================================================================

# Directorios principales estrictos
SRC_DIR      := src/main/java
BIN_DIR      := bin
RES_DIR      := src/main/resources

# CONFIGURACIÓN DE JAVAFX EN LINUX
PATH_TO_FX   := /usr/share/openjfx/lib
FX_MODULES   := javafx.controls,javafx.media

# Nombre de la clase principal
MAIN_CLASS   := com.example.pruebas_de_fx.MenuPrincipal2

# Comandos y flags del compilador
JAVAC        := javac
JAVA         := java

JFLAGS       := -d $(BIN_DIR) -cp "$(SRC_DIR)" --module-path "$(PATH_TO_FX)" --add-modules $(FX_MODULES)
RUN_FLAGS    := -cp "$(BIN_DIR):$(RES_DIR)" --module-path "$(PATH_TO_FX)" --add-modules $(FX_MODULES)

# Buscador de archivos .java recursivo en Linux
SOURCES      := $(shell find $(SRC_DIR) -name "*.java")

# =============================================================================
# REGLAS DE COMPILACIÓN Y EJECUCIÓN
# =============================================================================

all: compile

compile:
	@echo "Compilando clases de Java en WSL Linux..."
	@mkdir -p $(BIN_DIR)
	$(JAVAC) $(JFLAGS) $(SOURCES)
	@echo "Copiando recursos (imágenes y música) a la carpeta bin..."
	@cp -r $(RES_DIR)/* $(BIN_DIR)/ 2>/dev/null || true
	@echo "Compilación y copia de recursos completada con éxito."

run: compile
	@echo "Iniciando Dark Souls: POO Edition desde WSL..."
	@# Forzamos todas las variables de audio de WSLg y ALSA antes de lanzar la JVM
	export PULSE_SERVER=unix:/mnt/wslg/PulseServer && \
	export LIBASOUND_THREAD_SAFE=1 && \
	$(JAVA) $(RUN_FLAGS) $(MAIN_CLASS)
clean:
	@echo "Limpiando archivos binarios y recursos copiados..."
	rm -rf $(BIN_DIR)
	@echo "Limpieza completada."

.PHONY: all compile run clean