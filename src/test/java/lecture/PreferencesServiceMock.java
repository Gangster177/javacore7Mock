package lecture;

import java.util.Set;
public class PreferencesServiceMock implements PreferencesService {
    private Set<Preference> value;
//На ваше усмотрение к таким классам можно добавить еще конструктор,
//который будет задавать значение по умолчанию для класса заглушки:
    public PreferencesServiceMock() {
        this.value = Set.of(Preference.READING, Preference.FOOTBALL);
    }

    @Override
    public Set<Preference> get(String userId) {
        return value;
    }
    public void setValue(Set<Preference> value) {
        this.value = value;
    }
}