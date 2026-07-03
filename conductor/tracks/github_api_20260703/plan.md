# Plan: github_api_20260703

## Fase 1: Scaffolding
- [ ] Inicializar proyecto Spring Boot con Maven
- [ ] Crear estructura de paquetes (controller, service, model, dto, config)
- [ ] Agregar dependencias en pom.xml
- [ ] Configurar application.properties
- [ ] Crear landing page de bienvenida

## Fase 2: OpenAPI Spec
- [ ] Crear documento openapi.yaml con todos los endpoints
- [ ] Definir schemas de request/response
- [ ] Definir esquema de autenticación Bearer

## Fase 3: Cliente GitHub
- [ ] Implementar GitHubService interface y su implementación
- [ ] Implementar endpoint GET /user/repos (listar repos)
- [ ] Implementar endpoint POST /user/repos (crear repo)
- [ ] Implementar endpoint POST /repos/{owner}/{repo}/pulls (crear PR)
- [ ] Implementar endpoint POST /repos/{owner}/{repo}/issues/{number}/comments (comentar PR)
- [ ] Configurar RestClient con Bearer Token

## Fase 4: Controllers REST
- [ ] Implementar RepoController (listar y crear repos)
- [ ] Implementar PullRequestController (crear PR)
- [ ] Implementar CommentController (comentar PR)
- [ ] Agregar @RestControllerAdvice para manejo de errores

## Fase 5: SwaggerUI
- [ ] Integrar springdoc-openapi
- [ ] Configurar SwaggerUI para usar la spec
- [ ] Verificar que los endpoints sean invocables desde SwaggerUI

## Fase 6: Selenium Tests
- [ ] Configurar Selenium WebDriver
- [ ] Test: verificar que SwaggerUI carga y el esquema de auth está presente
- [ ] Test: probar GET /user/repos desde SwaggerUI
- [ ] Test: probar POST /user/repos desde SwaggerUI

## Fase 7: Tests Unitarios
- [ ] Test: GitHubService listar repos
- [ ] Test: GitHubService crear repo
- [ ] Test: GitHubService crear PR
- [ ] Test: GitHubService comentar PR
- [ ] Test: manejo de errores y excepciones
