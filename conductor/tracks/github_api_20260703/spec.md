# Spec: Módulo de Integración con GitHub API

## Descripción

Implementar una aplicación Spring Boot que consuma la API REST de GitHub utilizando autenticación Bearer, documentada con OpenAPI 3.0 y servida via SwaggerUI.

## Endpoints a Implementar

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/repos | Listar repositorios del usuario autenticado |
| POST | /api/repos | Crear un nuevo repositorio |
| POST | /api/repos/{owner}/{repo}/pulls | Crear un Pull Request |
| POST | /api/repos/{owner}/{repo}/issues/{number}/comments | Comentar en un PR |

## Requisitos Técnicos

- Autenticación Bearer Token via variable de entorno `GITHUB_TOKEN`
- OpenAPI 3.0 spec documentando todos los endpoints
- SwaggerUI para explorar la API
- Tests Selenium para incisos 1, 2 y 3
- Tests unitarios JUnit 5 + Mockito
- Manejo de errores con `@RestControllerAdvice`
