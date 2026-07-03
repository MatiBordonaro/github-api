# Initial Concept

Aplicación web Spring Boot que consume la API REST de GitHub, documentada con OpenAPI 3.0, con tests automatizados via Selenium.

# Product Guide

## Propósito

Trabajo práctico académico para analizar, documentar y consumir la API REST oficial de GitHub. El proyecto integra consumo de APIs REST, especificación OpenAPI 3.0, testing automatizado con Selenium y buenas prácticas de seguridad.

## Objetivos de Aprendizaje

- **Documentación OpenAPI 3.0**: Diseñar y escribir specs YAML con endpoints, schemas y esquema de autenticación.
- **Testing automatizado con Selenium**: Validar la UI de SwaggerUI/Redoc para los incisos 1, 2 y 3.
- **Buenas prácticas de seguridad**: Manejo de tokens Bearer via variables de entorno, sin hardcodear credenciales.

## Funcionalidades

1. **Autenticación**: Bearer Token leído de variable de entorno.
2. **Listado de repositorios**: GET /user/repos — muestra nombre, visibilidad y URL.
3. **Creación de repositorio**: POST /user/repos con nombre, descripción y visibilidad.
4. **Creación de Pull Request**: POST /repos/{owner}/{repo}/pulls entre rama nueva y main.
5. **Comentarios en PRs**: POST /repos/{owner}/{repo}/issues/{number}/comments asociado al PR.

## Stack Tecnológico

- Java + Spring Boot 3 + Maven
- RestClient como cliente HTTP
- SwaggerUI + springdoc-openapi para servir la documentación
- Selenium WebDriver para tests de UI
- JUnit 5 + Mockito para tests unitarios
