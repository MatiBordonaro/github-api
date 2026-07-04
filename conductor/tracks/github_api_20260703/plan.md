# Plan: github_api_20260703

## Fase 1: Scaffolding
- [x] Inicializar proyecto Spring Boot con Maven
- [x] Crear estructura de paquetes (controller, service, model, dto, config)
- [x] Agregar dependencias en pom.xml
- [x] Configurar application.properties
- [x] Crear landing page de bienvenida

## Fase 2: OpenAPI Spec
- [x] Crear documento openapi.yaml con todos los endpoints
- [x] Definir schemas de request/response
- [x] Definir esquema de autenticación Bearer

## Fase 3: Cliente GitHub
- [x] Implementar GitHubService interface y su implementación
- [x] Implementar endpoint GET /user/repos (listar repos)
- [x] Implementar endpoint POST /user/repos (crear repo)
- [x] Implementar endpoint POST /repos/{owner}/{repo}/pulls (crear PR)
- [x] Implementar endpoint POST /repos/{owner}/{repo}/issues/{number}/comments (comentar PR)
- [x] Configurar RestClient con Bearer Token

## Fase 4: Controllers REST
- [x] Implementar RepoController (listar y crear repos)
- [x] Implementar PullRequestController (crear PR)
- [x] Implementar CommentController (comentar PR)
- [x] Agregar @RestControllerAdvice para manejo de errores

## Fase 5: SwaggerUI
- [x] Integrar springdoc-openapi
- [x] Configurar SwaggerUI para usar la spec
- [x] Verificar que los endpoints sean invocables desde SwaggerUI

## Fase 6: Selenium Tests
- [x] Configurar Selenium WebDriver
- [x] Test: verificar que SwaggerUI carga y el esquema de auth está presente
- [x] Test: probar GET /api/repos desde SwaggerUI
- [x] Test: probar POST /api/repos desde SwaggerUI

## Fase 7: Tests Unitarios
- [x] Test: GitHubService listar repos
- [x] Test: GitHubService crear repo
- [x] Test: GitHubService crear PR
- [x] Test: GitHubService comentar PR
- [x] Test: manejo de errores y excepciones
