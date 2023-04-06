package homework.ru.netology.geo;

import homework.ru.netology.entity.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GeoServiceImplTest {
    //Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
    //Проверить работу метода public Location byIp(String ip)

    @ParameterizedTest
    @ValueSource(strings = {"127.0.0.1", "172.0.32.11", "96.44.183.149", "172.", "96."})
    public void byIpTest(String argument) {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp(argument);
        Assertions.assertNotNull(location);
    }

    @Test
    public void byIpIsNullTest() {
        GeoService geoService = new GeoServiceImpl();
        String ip = "111.";
        Location location = geoService.byIp(ip);
        Assertions.assertNull(location);
    }

    @Test
    public void byCoordinatesTest() {
        GeoService geoService = new GeoServiceImpl();
        Assertions.assertThrows(RuntimeException.class,
                () -> geoService.byCoordinates(55.45, 37.37));
    }
}
