@echo off
echo ╔════════════════════════════════════════╗
echo ║  Compilando Tower Defense Game...     ║
echo ╚════════════════════════════════════════╝
echo.

REM Crear carpeta bin si no existe
if not exist bin mkdir bin

REM Compilar todos los archivos Java
javac -d bin src\com\towerdefense\model\*.java src\com\towerdefense\decorator\*.java src\com\towerdefense\game\*.java src\com\towerdefense\server\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✓ Compilacion exitosa!
    echo ✓ Archivos .class generados en la carpeta 'bin'
    echo.
    echo Ejecuta 'run.bat' para iniciar el servidor
) else (
    echo.
    echo ✗ Error en la compilacion
    echo Revisa los mensajes de error anteriores
)

echo.
pause
