package homework.ru.netology.geo;


import homework.ru.netology.entity.Location;

public interface GeoService {

    Location byIp(String ip);

    Location byCoordinates(double latitude, double longitude);
}
