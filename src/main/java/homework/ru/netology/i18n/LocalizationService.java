package homework.ru.netology.i18n;


import homework.ru.netology.entity.Country;

public interface LocalizationService {

    String locale(Country country);
}
