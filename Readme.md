# Система управления складом

Данный проект является частью системы управления складом и предназначен для работы с данными о носках на складе и представлен в виде RESTful веб-сервиса для автоматизации учёта носков на складе магазина. 

## Установка

В проекте используется контейнеризация с Docker Compose для PostgreSQL

Для установки необходимо на сервере, где установлен Docker выполнить следующие команды:

```
git clone https://github.com/NikolayLukjanchikov/Warehouse.git
cd Warehouse
docker-compose up
```
Все необходимые образы будут скачаны и установлены.

## Использование

Все доступные методы API перечислены ниже:

### `POST /api/socks/income`

Метод регистрирует приход носков на склад и принимает JSON вида:

```json
{
  "color": "красный",
  "cottonPart": 65,
  "quantity": 10
}
```

* `color` - Название цвета носков
* `cottonPart` - Процентное содержание хлопка в составе носков
* `quantity` - Количество носков

### `POST /api/socks/outcome`

Метод регистрирует отпуск носков со склада и принимает JSON вида:

```json
{
  "color": "красный",
  "cottonPart": 65,
  "quantity": 10
}
```

* `color` - Название цвета носков
* `cottonPart` - Процентное содержание хлопка в составе носков
* `quantity` - Количество носков

### `GET /api/socks?color=<color>&operation=<operation>&cottonPart=<cottonPart>`

Метод возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
В параметры запроса необходимо передать следующие значения:

* `color` - Название цвета носков
* `operation` - Оператор сравнения коттон-парта: `moreThan`, `lessThan`, либо `equal`
* `cottonPart` - Процентное содержание хлопка в составе носков

### Примеры запросов

`/api/socks?color=red&operation=moreThan&cottonPart=90` — вернёт общее количество красных носков с долей хлопка более 90%

`/api/socks?color=black&operation=lessThan?cottonPart=10` — вернёт общее количество черных носков с долей хлопка менее 10%.

### Swagger
Доступ по адресу
http://ip:8881/swagger-ui/index.html

где `ip` - адрес вашего сервера
`8881` - порт для подключения


### Используемые модули
```
Java 17
Spring Boot 2
Maven
SpringDoc
Lombok
Liquibase
PostreSQL
```

## Лицензия

Данный проект распространяется как свободное программное обеспечение под лицензией [MIT](https://opensource.org/licenses/MIT).

## Автор

Николай Лукьянчиков
