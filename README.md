# StudyAI

Aplicativo Android (Jetpack Compose) para gerenciamento de estudos com auxílio de IA (heurística local).

## Requisitos
- Android Studio Koala ou Flamingo+
- JDK 17
- Android SDK configurado (defina `sdk.dir` em `local.properties` ou `ANDROID_HOME`)

## Como executar
1. Abra o projeto no Android Studio
2. Sincronize o Gradle
3. Se necessário, crie `local.properties` com o caminho do SDK:
   ```
   sdk.dir=/caminho/para/Android/Sdk
   ```
4. Selecione a configuração `app` e rode no emulador/dispositivo

## Funcionalidades
- Planejamento diário sugerido por heurística (prioridade, prazo, duração)
- Lista de tarefas persistida via Room
- Injeção de dependência com Hilt
- UI em Compose com navegação e bottom bar

## Estrutura
- `data/`: Room (Entity, DAO, Database, Repository)
- `planner/`: Lógica de sugestão do plano diário
- `ui/`: Composables, ViewModel (Hilt)

## Próximos passos
- Marcar tarefa como concluída e editar itens
- Integração com LLM opcional para sugestões mais ricas