package homework.ru.netology.i18n;


import homework.ru.netology.entity.Country;

public class LocalizationServiceImpl implements LocalizationService {

    public String locale(Country country) {
        switch (country) {
            case RUSSIA:
                return "Добро пожаловать";
            default:
                return "Welcome";
        }
    }
}
