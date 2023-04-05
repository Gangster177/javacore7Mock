package lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
@ExtendWith(MockitoExtension.class)
class AdviceServiceTest {
    @Test
    void test_get_advice_in_bad_weather() {
        //Напишем тест, который проверяет, что сервис не посоветует нам пойти гулять в
        //плохую погоду (в дождь или при сильном ветре).

        //WeatherService weatherService = ?
        //PreferencesService preferencesService = ?;

        //Создадим два mock, которые будем использовать в
        //тестах:
        WeatherServiceMock weatherService = new WeatherServiceMock();
        weatherService.setValue(Weather.STORMY);

        PreferencesServiceMock preferencesService = new PreferencesServiceMock();
        preferencesService.setValue(Set.of(Preference.FOOTBALL, Preference.WATCHING_FILMS,
                Preference.READING));

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        Set<Preference> preferences = adviceService.getAdvice("user1");

        Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
        Assertions.assertEquals(expected, preferences);
    }
//Создание mock-классов позволяет сократить зависимость тестируемого сервиса,
//но усложняет разработку тестов тем, что нам приходится на каждый зависимый
//модуль писать свои реализации.
//В случае, если мы будем менять интерфейс любого из модулей, каждый раз
//придется актуализировать реализацию классов-заглушек.
//Получается, при таком подходе нужно:
//1. Создать класс-заглушку на каждый модуль, от которого зависит наш тест
//2. Всегда актуализировать наши классы-заглушки в случае изменения базового
//класса

    //TODO Перепишем наш тест с использованием Mockito
    @Test
    void test_get_advice_in_bad_weather_with_Mockito() {
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.currentWeather())
                .thenReturn(Weather.STORMY);

        PreferencesService preferencesService = Mockito.mock(PreferencesService.class);
        Mockito.when(preferencesService.get("user1"))
                .thenReturn(Set.of(Preference.FOOTBALL, Preference.WATCHING_FILMS, Preference.READING));

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        Set<Preference> preferences = adviceService.getAdvice("user1");

        Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
        Assertions.assertEquals(expected, preferences);
    }

    @Test
    void test_get_advice_in_bad_weather_by_verify() {
        //Помимо создания заглушек, библиотека Mockito позволяет узнать,
        //сколько раз у нашей заглушки был вызван тот или иной метод во
        //время теста.
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.currentWeather()).thenReturn(Weather.STORMY);

        PreferencesService preferencesService = Mockito.mock(PreferencesService.class);
        Mockito.when(preferencesService.get(Mockito.any())).thenReturn(Set.of(Preference.FOOTBALL));

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        adviceService.getAdvice("user1");

        Mockito.verify(preferencesService, Mockito.times(1)).get("user1");
        //Mockito.verify(preferencesService, times(3)).get(any(String.class));
        Mockito.verify(preferencesService, Mockito.never()).get("user2");
    }

    @Test
    void test_get_advice_in_bad_weather_by_ArgumentCaptor() {
        //Библиотека позволяет получать значения, с которыми были вызваны методы mock-
        //объекта. Для этого в библиотеке Mockito есть специальный класс ArgumentCaptor.
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.currentWeather()).thenReturn(Weather.STORMY);

        PreferencesService preferencesService = Mockito.mock(PreferencesService.class);
        Mockito.when(preferencesService.get(Mockito.any())).thenReturn(Set.of(Preference.FOOTBALL));

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        adviceService.getAdvice("user1");

        //Следующим шагом нам нужно вызвать Mockito.verify и в качестве аргумента передать
        //наш mock:
        Mockito.verify(preferencesService).get(argumentCaptor.capture());
        //Для перехвата значения обязательно нужно вызвать метод capture(): он не изменит
        //значение, но перехватит его и сохранит. Чтобы получить перехваченное значение у
        //argumentCaptor, нужно вызвать метод getValue() или getValues():
        Assertions.assertEquals("user1", argumentCaptor.getValue());

    }

    @Test
    void test_spy_weather_service() {
        //Для проверки Mockito Spy создадим реализацию интерфейса,
        //вызовем в тесте и посмотрим, что вернет наш spy-объект:
        WeatherService weatherService = Mockito.spy(WeatherServiceImpl.class);
        Assertions.assertEquals(Weather.SUNNY, weatherService.currentWeather());
        //У этого spy-объекта, как и у любого mock, можно переопределять
        //значения, возвращаемые из методов, вызовом конструкции
        //when/thenReturn
    }
}