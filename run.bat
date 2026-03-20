@echo off
echo ╔════════════════════════════════════════╗
echo ║  Iniciando Tower Defense Game...      ║
echo ╚════════════════════════════════════════╝
echo.

REM Verificar si existe la carpeta bin
if not exist bin (
    echo ✗ Error: No se encontro la carpeta 'bin'
    echo Ejecuta 'compile.bat' primero para compilar el proyecto
    echo.
    pause
    exit /b 1
)

REM Ejecutar el servidor
java -cp bin com.towerdefense.server.GameServer

pause
