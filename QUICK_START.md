# 🚀 INICIO RÁPIDO

## Para ejecutar el juego:

### Windows:
```
1. Doble clic en compile.bat
2. Doble clic en run.bat
3. Abre http://localhost:8080 en tu navegador
```

### Linux/Mac:
```
1. ./compile.sh
2. ./run.sh
3. Abre http://localhost:8080 en tu navegador
```

## ¿Qué hace este proyecto?

Es un **juego Tower Defense interactivo** que demuestra el **Patrón Decorador**:

- Tu torre dispara automáticamente a los enemigos
- Aplica "decoradores" para mejorar la torre en tiempo real
- Cada decorador añade nuevas habilidades sin modificar el código base
- ¡Sobrevive el mayor número de oleadas posible!

## Decoradores disponibles:

- 🚀 **Disparo Rápido**: +5 velocidad de ataque
- ❄️ **Aura de Hielo**: Congela enemigos
- 🛡️ **Escudo**: Protección extra
- 💰 **Generador de Oro**: +10 oro por segundo

## Estructura del código:

```
src/
  com/towerdefense/
    model/         ← Interfaz Tower y BaseTower
    decorator/     ← Patrón Decorador (4 decoradores + base abstracta)
    game/          ← Motor del juego (Enemy, Projectile, GameEngine)
    server/        ← Servidor HTTP (cada handler en su archivo)
```

**¡Sin clases anidadas! Cada clase en su propio archivo.**

## Tecnologías:

- **Backend**: Java puro (sin frameworks)
- **Frontend**: HTML5 Canvas + JavaScript
- **Servidor**: HttpServer nativo de Java
- **Patrón**: Decorator (sin herencia innecesaria)

---

**¡Diviértete jugando y aprendiendo patrones de diseño!** 🎮
