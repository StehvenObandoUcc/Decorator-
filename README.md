# 🏰 Tower Defense Game - Patrón Decorador Interactivo

## 📋 Descripción

**Juego interactivo de Tower Defense** que implementa el **Patrón Decorador (Decorator Pattern)** de forma práctica y visual. Defiende tu torre de oleadas de enemigos mientras aplicas mejoras dinámicas sin modificar la estructura original.

## 🎮 Características del Juego

### Características Principales
- ✅ **Juego completamente funcional** con enemigos, proyectiles y colisiones
- ✅ **Sistema de oleadas** con dificultad progresiva (cada 5 segundos)
- ✅ **3 tipos de enemigos**: Básico, Rápido y Tanque (velocidades balanceadas)
- ✅ **4 decoradores únicos** que mejoran la torre
- ✅ **Sistema de recursos** (oro y puntuación)
- ✅ **Animaciones en tiempo real** con Canvas HTML5
- ✅ **Efectos visuales** (congelamiento, barras de vida, etc.)
- ✅ **Sistema de reinicio** funcional después de Game Over
- ✅ **20 vidas** para una experiencia más prolongada

### Decoradores Disponibles

1. **🚀 Disparo Rápido (FastDecorator)**
   - Aumenta la velocidad de disparo +5
   - Permite atacar más enemigos por segundo

2. **❄️ Aura de Hielo (IceDecorator)**
   - Congela enemigos aleatoriamente
   - Los enemigos congelados se mueven más lento

3. **🛡️ Escudo de Energía (ShieldDecorator)**
   - Añade protección defensiva
   - Bonus especial de defensa

4. **💰 Generador de Oro (GoldDecorator)**
   - Genera 10 de oro por segundo automáticamente
   - Permite comprar más mejoras

## 🏗️ Estructura del Proyecto (Refactorizada)

```
decorator/
├── src/
│   └── com/
│       └── towerdefense/
│           ├── model/                  # Modelos base
│           │   ├── Tower.java          # Interfaz principal
│           │   └── BaseTower.java      # Torre básica
│           ├── decorator/              # Decoradores (Patrón)
│           │   ├── TowerDecorator.java # Decorador abstracto
│           │   ├── FastDecorator.java
│           │   ├── IceDecorator.java
│           │   ├── ShieldDecorator.java
│           │   └── GoldDecorator.java
│           ├── game/                   # Motor del juego
│           │   ├── GameEngine.java     # Motor principal
│           │   ├── Enemy.java          # Clase enemigo
│           │   └── Projectile.java     # Clase proyectil
│           └── server/                 # Servidor HTTP
│               ├── GameServer.java     # Servidor principal
│               ├── FrontendHandler.java
│               ├── StatusHandler.java
│               ├── GameStateHandler.java
│               ├── UpgradeHandler.java
│               ├── ResetHandler.java
│               └── RestartGameHandler.java  # Reinicio del juego
├── public/
│   └── index.html                      # Interfaz interactiva
├── bin/                                # Archivos compilados
├── compile.bat / compile.sh            # Scripts de compilación
├── run.bat / run.sh                    # Scripts de ejecución
└── README.md
```

### ✨ Mejoras Arquitectónicas

- **Sin clases anidadas**: Cada clase en su propio archivo
- **Separación de responsabilidades**: Handlers independientes
- **Motor de juego dedicado**: GameEngine maneja toda la lógica
- **Código limpio**: Una clase por archivo siguiendo estándares Java

## 🚀 Cómo Ejecutar

### Opción 1: Scripts Automáticos (Recomendado)

**Windows:**
```bash
compile.bat
run.bat
```

**Linux/Mac:**
```bash
chmod +x compile.sh run.sh
./compile.sh
./run.sh
```

### Opción 2: Compilación Manual

```bash
# Compilar
javac -d bin src/com/towerdefense/model/*.java src/com/towerdefense/decorator/*.java src/com/towerdefense/game/*.java src/com/towerdefense/server/*.java

# Ejecutar
java -cp bin com.towerdefense.server.GameServer
```

### 3. Abrir en el navegador:
```
http://localhost:8080
```

## 🎯 Cómo Jugar

1. **Inicio**: El juego comienza automáticamente con una torre base
2. **Defensa**: La torre dispara automáticamente a los enemigos más cercanos
3. **Mejoras**: Haz clic en los botones de decoradores para mejorar tu torre
4. **Recursos**: Gana oro eliminando enemigos o con el decorador de oro
5. **Objetivo**: Sobrevive el mayor número de oleadas posible
6. **Game Over**: Pierdes cuando tu vida llega a 0

### Tipos de Enemigos

| Tipo | Vida | Velocidad | Oro | Velocidad Real |
|------|------|-----------|-----|----------------|
| Básico 🔴 | 25 | Media | 5 | 0.5 px/frame |
| Rápido 🔵 | 15 | Rápida | 5 | 0.8 px/frame |
| Tanque 🟢 | 50 | Lenta | 5 | 0.3 px/frame |

> **Nota:** Las velocidades están balanceadas para una experiencia de juego cómoda y estratégica.

## 📐 Patrón Decorador Explicado

### Implementación en el Juego

```java
// Torre base
Tower tower = new BaseTower();  // Daño: 10, Velocidad: 1

// Aplicar múltiples decoradores
tower = new FastDecorator(tower);     // Velocidad: 6
tower = new IceDecorator(tower);      // + Congelamiento
tower = new GoldDecorator(tower);     // + 10 oro/seg

// Resultado dinámico
tower.getDescription();  // "Base Tower + Rapid Fire + Ice Aura + Gold Miner"
tower.getDamage();       // 10
tower.getSpeed();        // 6
tower.getSpecialPower(); // "Freezes Enemies | Generates 10 Gold/sec"
```

### Diagrama de Clases

```
        ┌──────────┐
        │  Tower   │ (interface)
        │----------|
        │ +getDamage()
        │ +getSpeed()
        │ +getSpecialPower()
        └──────────┘
             △
             │
      ┌──────┴──────────┐
      │                 │
┌──────────┐   ┌─────────────────┐
│BaseTower │   │ TowerDecorator  │ (abstract)
│----------|   │-----------------|
│ damage=10│   │ -decoratedTower │
│ speed=1  │   └─────────────────┘
└──────────┘            △
                        │
        ┌───────────────┼───────────────┐
        │               │               │
   ┌────────┐    ┌──────────┐    ┌──────────┐
   │  Fast  │    │   Ice    │    │  Shield  │ ...
   │+speed  │    │+freeze() │    │+defense  │
   └────────┘    └──────────┘    └──────────┘
```

## 🌐 API Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/` | Interfaz web del juego |
| GET | `/api/tower/status` | Estado de la torre (JSON) |
| GET | `/api/game/state` | Estado del juego completo (JSON) |
| POST | `/api/tower/upgrade/fast` | Aplica FastDecorator |
| POST | `/api/tower/upgrade/ice` | Aplica IceDecorator |
| POST | `/api/tower/upgrade/shield` | Aplica ShieldDecorator |
| POST | `/api/tower/upgrade/gold` | Aplica GoldDecorator |
| POST | `/api/tower/reset` | Reinicia la torre al estado base |
| POST | `/api/game/restart` | **NUEVO:** Reinicia el juego completo |

### Ejemplo de Respuesta JSON

**GET /api/game/state**
```json
{
  "gold": 150,
  "score": 230,
  "wave": 5,
  "lives": 7,
  "gameOver": false,
  "enemies": [
    {"x": 650, "y": 250, "health": 20, "maxHealth": 25, "type": "basic", "frozen": false},
    {"x": 700, "y": 180, "health": 15, "maxHealth": 15, "type": "fast", "frozen": false}
  ],
  "projectiles": [
    {"x": 150, "y": 300},
    {"x": 200, "y": 280}
  ]
}
```

## 🔧 Requisitos

- **Java 8 o superior** (usa `com.sun.net.httpserver`)
- Navegador web moderno con soporte para Canvas
- Puerto 8080 disponible

## 📚 Conceptos de OOP Aplicados

- ✅ **Patrón Decorador**: Extensión dinámica sin herencia
- ✅ **Encapsulamiento**: Cada clase maneja su propia lógica
- ✅ **Polimorfismo**: Todos los decoradores implementan Tower
- ✅ **Composición sobre Herencia**: Decoradores envuelven objetos
- ✅ **Single Responsibility**: Cada clase tiene una responsabilidad
- ✅ **Open/Closed**: Abierto a extensión, cerrado a modificación
- ✅ **Separación de Concerns**: Modelo, Vista, Controlador separados

## 🎓 Propósito Educativo

Este proyecto demuestra:

1. **Patrón Decorador en Acción**: Aplicación práctica del patrón
2. **Arquitectura Limpia**: Separación clara de responsabilidades
3. **Sin Clases Anidadas**: Cada clase en su propio archivo
4. **Sin Herencia Innecesaria**: Solo el patrón decorador
5. **Código Profesional**: Estructura escalable y mantenible
6. **Full Stack**: Backend (Java) + Frontend (HTML/Canvas)

## 🌟 Características Técnicas

- **Game Loop**: 60 FPS con actualización en tiempo real
- **Renderizado Canvas**: Animaciones fluidas
- **Sistema de Colisiones**: Detección de impactos proyectil-enemigo
- **Spawning Dinámico**: Oleadas progresivamente más difíciles
- **Estado Sincronizado**: Backend maneja lógica, frontend visualiza
- **Arquitectura REST**: Comunicación mediante API JSON

## 👨‍💻 Autor

Proyecto educativo para demostrar el Patrón Decorador de forma interactiva y práctica.

## 📝 Licencia

Proyecto de uso educativo libre.

---

**¡Disfruta defendiendo tu torre mientras aprendes patrones de diseño!** 🎮🏰
