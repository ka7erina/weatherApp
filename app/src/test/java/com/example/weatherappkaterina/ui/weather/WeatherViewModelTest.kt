import android.location.Location
import com.example.weatherappkaterina.core.WeatherResult
import com.example.weatherappkaterina.domain.location.LocationTracker
import com.example.weatherappkaterina.domain.model.Main
import com.example.weatherappkaterina.domain.model.Weather
import com.example.weatherappkaterina.domain.model.WeatherResponse
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import com.example.weatherappkaterina.ui.weather.WeatherState
import com.example.weatherappkaterina.ui.weather.WeatherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var locationTracker: LocationTracker
    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        weatherRepository = mock(WeatherRepository::class.java)
        locationTracker = mock(LocationTracker::class.java)
        viewModel = WeatherViewModel(weatherRepository, locationTracker)
    }

    @Test
    fun `loadLocation with valid location data should emit Success state`() = runTest {
        val latitude = 37.422
        val longitude = -122.084
        val location = mock(Location::class.java)
        location.latitude = latitude
        location.longitude = longitude
        `when`(locationTracker.getCurrentLocation()).thenReturn(location)
        val weather = WeatherResponse(
            name = "Mountain View", main = Main(
                feels_like = 298.74, temp = 272.1, temp_min = 297.56, temp_max = 300.05
            ), weather = listOf(
                Weather(
                    description = "clear sky"
                )
            )
        )
        val successResult = WeatherResult.Success(weather)
        `when`(
            weatherRepository.getWeather(
                location.latitude.toString(), location.longitude.toString()
            )
        ).thenReturn(flowOf(successResult))

        viewModel.loadLocation()

        val state = viewModel.weatherState.value
        assert(state is WeatherState.Loading)
        assert(state is WeatherState.Success)
    }

    @Test
    fun `loadLocation with null location data should emit Error state`() = runTest {
        `when`(locationTracker.getCurrentLocation()).thenReturn(null)

        viewModel.loadLocation()

        val state = viewModel.weatherState.value
        assert(state is WeatherState.Loading)
        assert(state is WeatherState.Error)
    }

    @Test
    fun `loadLocation with error should emit Error state`() = runTest {
        val latitude = 37.422
        val longitude = -122.084
        val location = mock(Location::class.java)
        location.latitude = latitude
        location.longitude = longitude
        `when`(locationTracker.getCurrentLocation()).thenReturn(location)
        val errorMessage = "Network error"
        val errorResult = WeatherResult.Error(errorMessage)
        `when`(
            weatherRepository.getWeather(
                location.latitude.toString(), location.longitude.toString()
            )
        ).thenReturn(flowOf(errorResult))

        viewModel.loadLocation()

        val state = viewModel.weatherState.value
        assert(state is WeatherState.Loading)
        assert(state is WeatherState.Error)
    }
}
