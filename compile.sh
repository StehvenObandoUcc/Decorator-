#!/bin/bash

echo "╔════════════════════════════════════════╗"
echo "║  Compilando Tower Defense Game...     ║"
echo "╚════════════════════════════════════════╝"
echo ""

# Crear carpeta bin si no existe
mkdir -p bin

# Compilar todos los archivos Java
javac -d bin src/com/towerdefense/model/*.java src/com/towerdefense/decorator/*.java src/com/towerdefense/game/*.java src/com/towerdefense/server/*.java

if [ $? -eq 0 ]; then
    echo ""
    echo "✓ Compilación exitosa!"
    echo "✓ Archivos .class generados en la carpeta 'bin'"
    echo ""
    echo "Ejecuta './run.sh' para iniciar el servidor"
else
    echo ""
    echo "✗ Error en la compilación"
    echo "Revisa los mensajes de error anteriores"
    exit 1
fi
