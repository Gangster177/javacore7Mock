package lecture;

//public class WeatherServiceMock implements WeatherService {
//    @Override
//    public Weather currentWeather() {
//        return Weather.STORMY;
//    }
//}

//Такая класс-заглушка всегда будет возвращать только плохую погоду -
//добавим ей возможность изменять возвращаемое значение на наше:

public class WeatherServiceMock implements WeatherService {
    private Weather value;
    @Override
    public Weather currentWeather() {
        return value;
    }
    public void setValue(Weather value) {
        this.value = value;
    }
}