# Product Guidelines

## Estilo de Documentación

- **Javadoc minimalista**: documentar solo interfaces públicas y métodos principales.
- Comentarios inline solo donde sea necesario explicar lógica compleja.
- Código fuente en inglés (clases, métodos, variables).
- Documentación y commits en español.

## Estructura del Proyecto

- **Package by layer**: `controller/`, `service/`, `repository/`, `model/`, `config/`, `dto/`.
- DTOs separados de las clases de dominio.
- Uso de interfaces para servicios.

## Manejo de Errores

- `@RestControllerAdvice` con excepciones personalizadas y handlers específicos.
- Mensajes de error claros en español.

## Convenciones de Código

- Nombres de clases: PascalCase. Métodos y variables: camelCase. Constantes: UPPER_SNAKE_CASE.
- Spring Boot 3 + Maven.
- Tests con JUnit 5 + Mockito.
