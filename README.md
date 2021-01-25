# WeatherService

Сервис, предоставляющий данные о погоде с OpenWeather API с предварительным кэшированием в БД.

Для корректной работы проекта необходимы следующие переменные среды:  
**WEATHER_SERVICE_URL** - полный URL до OpenWeather "https://api.openweathermap.org/data/2.5/weather"  
**WEATHER_SERVICE_DEFAULT_LANG** - язык локализации по умолчанию, например "ru"  
**WEATHER_SERVICE_TOKEN** - токен OpenWeather  
**SPRING_DATASOURCE_USERNAME** - логин от БД  
**SPRING_DATASOURCE_PASSWORD** - пароль от БД  
**SPRING_DATASOURCE_URL** - полный URL до БД, например: "jdbc:postgresql://localhost:5432/weather_service"

Для внешнего взаимодействия реализован контроллер, принимающий GET-запрос, принимающий на вход имя города

```url
http://localhost:8080/weather?city=Moscow
```

Ответ возвращается в формате JSON и имеет следующий вид:

```json
{
  "city": {
    "id": 524901,
    "cityName": "Москва",
    "coordinates": {
      "latitude": 55.7522,
      "longitude": 37.6156
    }
  },
  "shortDescription": "Clouds",
  "fullDescription": "переменная облачность",
  "parameters": {
    "minTemp": 253,
    "maxTemp": 258,
    "pressure": 1016,
    "humidity": 78
  },
  "wind": {
    "speed": 1,
    "direction": 0
  },
  "refreshDate": 1610967434470
}
```

По умолчанию погода предоставляется из БД, если в БД нужной записи нет, отправляется запрос в OpenWeather на получение
погоды, ответ кэшируется и возвращается пользователю.

Реализовано "ленивое" обновление информации о погоде в БД: при каждом запросе погоды по определенному городу проверяется
время последнего обновления. Если погода обновлялась более 1 часа назад, то выполняются те же действия, что и при
добавлении новой погоды.

В настоящий момент поиск погоды осуществляется по названию города. В случае, если в БД будет 2 города с одинаковым
названием пользователю вернется JSON с ошибкой:

```json
{
  "code": "900",
  "message": "Need more information. Please send needed location"
}
```

Если город с таким именем не найден:

```json
{
  "code": "404",
  "cityName": "SomeCityName"
}
```

Назначение данного проекта - реализация тестового задания.

Стек: Spring Boot, Hibernate, PostgreSQL.


