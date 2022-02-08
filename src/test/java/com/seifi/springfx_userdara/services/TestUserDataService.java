package com.seifi.springfx_userdara.services;

import com.seifi.springfx_userdara.entities.UserDataEntity;
import com.seifi.springfx_userdara.repositories.UserDataRepository;
import com.seifi.springfx_userdara.services.imp.UserDataService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserDataService {

    IUserDataService userDataService;

    @Mock
    UserDataRepository userDataRepository;

    @Mock
    IWeatherService weatherService;

    private String testLocation = "testLocation";
    private String testLocationFalse = "testLocationFalse";

    private String testName = "testName";
    private int testAGe = 23;

    private String testWeather = "testWeather";
    private String testFalseWeatherMessage = String.format("There's no weather information about %s.", this.testLocationFalse);

    private List<UserDataEntity> testUsersList = new ArrayList<>();

    private UserDataEntity testUser = new UserDataEntity(100L, "User-100", 50, "Location-Test");

    public TestUserDataService() {
        testUsersList = new ArrayList<>();
        for(int i=1; i<5; i++){
            testUsersList.add(new UserDataEntity(1L * i, String.format("User-%d", i), 5 * i, String.format("Location-%d", i)));
        }

    }

    @Before
    public void init() throws Exception {
        MockitoAnnotations.openMocks(this);

        Mockito.when(weatherService.getWeatherForecastByLocation(this.testLocation)).thenReturn(this.testWeather);
        Mockito.when(weatherService.getWeatherForecastByLocation(this.testLocationFalse)).thenThrow(new Exception(testFalseWeatherMessage));
        Mockito.when(userDataRepository.findByOrderByIdDesc()).thenReturn(this.testUsersList);
        Mockito.when(userDataRepository.save(any())).thenReturn(this.testUser);
        userDataService = new UserDataService(weatherService, userDataRepository);
    }

    @Test
    public void TestGetUserAdditionInfoCorrect() throws Exception {

        String correctResponse = String.format(
                "You are %s (%d years old) and are living at %s. The weather at your location is %s",
                testName,
                testAGe,
                testLocation,
                testWeather);

        String resultInfo = this.userDataService.getUserAdditionInfo(testName, testAGe, testLocation);

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", resultInfo);
        Assert.assertEquals("The result of getUserAdditionInfo must not be " + correctResponse, correctResponse, resultInfo);

        verify(weatherService, times(2)).getWeatherForecastByLocation(testLocation);
    }

    @Test
    public void TestGetUserAdditionInfoFalseLocation() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> {
            this.userDataService.getUserAdditionInfo(testName, testAGe, testLocationFalse);
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw " + testFalseWeatherMessage, testFalseWeatherMessage, exception.getLocalizedMessage());
    }

    @Test
    public void TestGetUserAdditionInfoInvalidName() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> {
            this.userDataService.getUserAdditionInfo("", testAGe, testLocation);
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw 'Invalid name'!", "Invalid name!", exception.getLocalizedMessage());
    }

    @Test
    public void TestGetUserAdditionInfoInvalidAge() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> {
            this.userDataService.getUserAdditionInfo(testName, -1, testLocation);
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw 'Invalid age'!", "Invalid age!", exception.getLocalizedMessage());
    }

    @Test
    public void TestGetUserAdditionInfoInvalidLocation() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> {
            this.userDataService.getUserAdditionInfo(testName, testAGe, "");
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw 'Invalid location'!", "Invalid location!", exception.getLocalizedMessage());
    }

    @Test
    public void TestGetSortedUserList() throws Exception {

        List<UserDataEntity> resultList = this.userDataService.getSortedUserList();

        Assert.assertNotNull("The result of getSortedUserList must not be null", resultList);
        Assert.assertEquals("The result of getSortedUserList must have " + testUsersList.size() + " items", testUsersList.size(), resultList.size());

        for(UserDataEntity item: testUsersList){
            Assert.assertTrue(resultList.stream().anyMatch(u-> u.isEqual(item)));
        }
        verify(userDataRepository, times(1)).findByOrderByIdDesc();
    }

    @Test
    public void TestInsertUser() throws Exception {

        UserDataEntity resultUser = this.userDataService.insertUser(testName, testAGe, testLocation);

        Assert.assertNotNull("The result of insertUser must not be null", resultUser);
        Assert.assertTrue("The result of insertUser must be " + testUser.getTitle(), resultUser.isEqual(testUser));

        verify(userDataRepository, times(1)).save(any());
    }

    @Test
    public void TestInsertUserInvalidName() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            this.userDataService.insertUser("", testAGe, testLocation);
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw 'Invalid name'!", "Invalid name!", exception.getLocalizedMessage());

        verify(userDataRepository, times(0)).save(any());
    }

    @Test
    public void TestInsertUserInvalidAge() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            this.userDataService.insertUser(testName, -1, testLocation);
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw 'Invalid age'!", "Invalid age!", exception.getLocalizedMessage());

        verify(userDataRepository, times(0)).save(any());
    }

    @Test
    public void TestInsertUserInvalidLocation() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            this.userDataService.insertUser(testName, testAGe, "");
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw 'Invalid location'!", "Invalid location!", exception.getLocalizedMessage());

        verify(userDataRepository, times(0)).save(any());
    }

    @Test
    public void TestInsertUserFalseLocation() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            this.userDataService.insertUser(testName, testAGe, testLocationFalse);
        });

        Assert.assertNotNull("The result of getUserAdditionInfo must not be null", exception);
        Assert.assertEquals("The result of getUserAdditionInfo must throw " + testFalseWeatherMessage, testFalseWeatherMessage, exception.getLocalizedMessage());

        verify(userDataRepository, times(0)).save(any());
    }


}
