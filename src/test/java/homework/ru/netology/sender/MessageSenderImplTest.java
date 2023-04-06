package homework.ru.netology.sender;

import homework.ru.netology.entity.Country;
import homework.ru.netology.entity.Location;
import homework.ru.netology.geo.GeoService;
import homework.ru.netology.geo.GeoServiceImpl;
import homework.ru.netology.i18n.LocalizationService;
import homework.ru.netology.i18n.LocalizationServiceImpl;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    //TODO (для сервиса MessageSenderImpl, нужно обязательно создать заглушки (mock) в виде GeoServiceImpl и LocalizationServiceImpl)

    //Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito
    //Поверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
    //Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.

    @ParameterizedTest
    @ValueSource(strings = {"172.123.12.19", "172.0.32.11", "172.0.0.0", "172."})
    public void sendRusTest(String ip) {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ip);
        String actual = messageSender.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.44.183.149", "96.", "96.0.0.0"})
    public void sendEngTest(String ip) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ip);
        String actual = messageSender.send(headers);
        String expected = "Welcome";
        Assertions.assertEquals(expected, actual);
    }
}
