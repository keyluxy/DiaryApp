# DiaryApp

DiaryApp — это мобильное приложение для управления ежедневными задачами (делами). Приложение позволяет пользователям создавать, просматривать, редактировать и удалять задачи. Основной функционал включает экран со списком задач, экран деталей задачи и экран создания новой задачи. Приложение разработано с использованием архитектуры MVVM, Jetpack Compose для UI, Room для локального хранения данных и Unit-тестов для проверки основных функций.

## Скриншоты приложения

   ![Screenshot from 2024-12-23 20-28-20](https://github.com/user-attachments/assets/9d846e63-4202-46ea-918e-b73616041f40))(![Screenshot from 2024-12-23 20-28-14](https://github.com/user-attachments/assets/3c38c12a-a446-4035-8001-d41855ded635)
)



 (![Screenshot from 2024-12-23 20-27-52](https://github.com/user-attachments/assets/04afa48d-e2c8-44a1-9ed5-1a54ecd7b97a))(![Screenshot from 2024-12-23 20-26-48](https://github.com/user-attachments/assets/e113a7e9-42c8-47a0-9259-cd2679f4d3ee))

   

## Основные функции

### Список задач с календарем:
- Пользователь может выбрать день в календаре.
- После выбора дня отображается таблица с задачами на выбранный день.
- Каждая ячейка таблицы представляет один час дня (например, 14:00-15:00).
- Если в это время есть задача, она отображается в виде блока с названием и временем.
- Имеется возможность удалять и изменять информацию о задаче, включая название, дату, время и краткое описание.

### Подробное описание задачи:
- На экране деталей задачи отображается название, дата, время, краткое описание задачи.
- Пользователь может редактировать или удалять задачу.

### Создание новой задачи:
- Пользователь может создать новую задачу, указав название, выбрав дату и время, а также добавив краткое описание.

### Локальное хранение данных:
- Для хранения задач используется Room Database.
- Задачи сохраняются локально на устройстве и доступны даже после перезапуска приложения.

### Unit-тесты:
- Реализованы Unit-тесты для проверки основных функций, таких как валидация времени и форматирование ввода.

## Архитектура

Приложение разработано с использованием архитектуры MVVM (Model-View-ViewModel):

### Model:
- `Task` — класс, представляющий задачу.
- `TaskDao` — интерфейс для взаимодействия с базой данных Room.
- `AppDatabase` — класс для инициализации базы данных Room.

### View:
Экраны реализованы с использованием Jetpack Compose.
- `TaskListScreen` — экран со списком задач.
- `TaskDetailScreen` — экран с деталями задачи.
- `AddTaskScreen` — экран для создания новой задачи.

### ViewModel:
- `TaskViewModel` — ViewModel для управления данными задач и взаимодействия с репозиторием.
- `TaskViewModelFactory` — фабрика для создания ViewModel.

### Repository:
- `TaskRepository` — репозиторий для взаимодействия с базой данных Room.

## Технологии

- **Jetpack Compose**: Для создания пользовательского интерфейса используется Jetpack Compose, что позволяет создавать динамические и адаптивные UI-компоненты.
- **Room Database**: Для локального хранения данных используется Room, который предоставляет удобный способ работы с SQLite.
- **Kotlin Coroutines**: Для асинхронной работы с базой данных и другими операциями используются корутины.
- **Moshi**: Для парсинга JSON-данных, используемых для инициализации базы данных.
- **Unit-тесты**: Для проверки функциональности приложения написаны Unit-тесты.

## Структура проекта

### data:
- `Task.kt` — модель задачи.
- `TaskDao.kt` — DAO для работы с базой данных.
- `AppDatabase.kt` — инициализация Room Database.
- `Converters.kt` — конвертеры для работы с типами данных, такими как LocalDate.

### repository:
- `TaskRepository.kt` — репозиторий для работы с данными задач.

### ui:
#### screens:
- `TaskListScreen.kt` — экран со списком задач.
- `TaskDetailScreen.kt` — экран с деталями задачи.
- `AddTaskScreen.kt` — экран для создания новой задачи.
- `TaskItem.kt` — компонент для отображения задачи в списке.
- `TimeSlot.kt` — компонент для отображения временного слота.
- `DatePicker.kt` — компонент для выбора даты.
- `AddButton.kt` — кастомная кнопка для добавления задачи.
- `RoundedCornerTextField.kt` — кастомное текстовое поле с закругленными углами.

### utils:
- `ValidateTime.kt` — валидация времени.
- `TaskJson.kt` — парсинг JSON-данных для инициализации базы данных.

### viewmodels:
- `TaskViewModel.kt` — ViewModel для управления данными задач.
- `TaskViewModelFactory.kt` — фабрика для создания ViewModel.

### MainActivity:
Основная активность приложения, которая инициализирует ViewModel и отображает основной экран.

   
