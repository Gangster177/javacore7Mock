package homework;

import homework.ru.netology.geo.GeoService;
import homework.ru.netology.geo.GeoServiceImpl;
import homework.ru.netology.i18n.LocalizationService;
import homework.ru.netology.i18n.LocalizationServiceImpl;
import homework.ru.netology.sender.MessageSender;
import homework.ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class Main {

    //Тестовый пример
    public static void main(String[] args) {
        GeoService geoService = new GeoServiceImpl();
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        messageSender.send(headers);
    }
}