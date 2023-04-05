package lecture;

public class WeatherServiceImpl implements WeatherService {
    @Override
    public Weather currentWeather() {
        return Weather.SUNNY;
    }
}