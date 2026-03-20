#!/bin/bash

echo "╔════════════════════════════════════════╗"
echo "║  Iniciando Tower Defense Game...      ║"
echo "╚════════════════════════════════════════╝"
echo ""

# Verificar si existe la carpeta bin
if [ ! -d "bin" ]; then
    echo "✗ Error: No se encontró la carpeta 'bin'"
    echo "Ejecuta './compile.sh' primero para compilar el proyecto"
    echo ""
    exit 1
fi

# Ejecutar el servidor
java -cp bin com.towerdefense.server.GameServer
