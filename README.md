# Proyecto Space Invaders Epsilón

Este repositorio contiene todos los documentos, código y organización del proyecto del equipo Epsilón.

## Estructura del Proyecto

```
├── docs/
│   ├── reuniones/
│   │   ├── sprint_1/
│   │   │   ├──reunion_inicial.txt
│   │   │   ├── reunion_final.txt
│   │   │   ├── scrum_log_[dia-mes].txt
│   ├── kanban/
│   │   ├──hoja_calculo.odt
│   └── txt/
└── proyecto/
```

### Descripción de Carpetas y Archivos

- **docs/**: Carpeta principal para documentos relacionados con el proyecto.

  - **reuniones/**: Contiene los documentos de las reuniones iniciales, finales para cada sprint y el registro diario de scrum en formato `txt`.
  - **kanban/**: Carpeta para hojas de cálculo relacionadas con la planificación y el progreso.
  - **txt/**: Espacio para archivos de texto adicionales, notas, y documentación.

- **proyecto/**: Carpeta que almacena el código fuente y recursos específicos del proyecto.

## Flujo de Trabajo y Estructura de Ramas

Para gestionar las tareas y el desarrollo de código, se utiliza la siguiente estructura de ramas:

```
main
└── develop
    ├── f(Iniciales Colaborador)(Tarea)
```

### Detalles de las Ramas

- **main**: Rama principal, contiene la versión estable del proyecto.
- **develop**: Rama secundaria donde se integran los desarrollos y se prueban nuevas funcionalidades antes de ser fusionadas en `Main`.
- **f(Iniciales Colaborador)(Tarea)**: Ramas de características dedicadas a tareas específicas. Cada rama se nombra con el prefijo `f`, seguido de las iniciales del desarrollador y el nombre o descripción de la tarea.

Ejemplo de nombres de rama: `firzdocsscrum`

- f: feature
- irz: Iván Ruiz Zorrilla
- docsscrum: Nombre de la tarea

## Documentación

Todos los documentos importantes se encuentran en la carpeta `docs/`. Los registros de reuniones y actividades diarias de scrum están en la subcarpeta `reuniones/`.

### Reuniones

- **reunion_inicial.txt**: Resumen y acuerdos de la reunión inicial del sprint.
- **reunion_final.txt**: Resultados y análisis de la reunión final.
- **scrum*log*[dia-mes].txt**: Registros de cada sesión diaria de scrum, con el formato `scrum_log_15-09.txt`.
