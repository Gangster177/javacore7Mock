package homework.ru.netology.i18n;

import homework.ru.netology.entity.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {
    //Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
    //Проверить работу метода public String locale(Country country)
    @ParameterizedTest
    @MethodSource("country")
    public void locateTest(Country country) {
        LocalizationService service = new LocalizationServiceImpl();

        final String argument = service.locale(country);
        final String expected = "Welcome";

        Assertions.assertEquals(argument, expected);
    }

    static Stream<Country> country() {
        return Stream.of(Country.USA, Country.GERMANY, Country.BRAZIL);
    }

    @Test
    public void locateRusTest() {
        LocalizationService service = new LocalizationServiceImpl();
        final String argument = service.locale(Country.RUSSIA);
        final String expected = "Добро пожаловать";
        Assertions.assertEquals(argument, expected);
    }
}
