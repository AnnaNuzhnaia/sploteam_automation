package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;


public class EventsPage {

    public static final String ARENA_FILTER_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[1]/div/div";
    public static final String BUTTON_CREATE_GAME_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div/div/div[3]/div[4]/button";
    public static final String START_TIME_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div/div/div[3]/div[2]";
    public static final String DROP_DOWN_LIST_CLASS = "Select_dropdownItem__2T2FU";
    public static final String DURATION_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div/div/div[3]/div[1]/div[1]";
    public static final String TIME_LIST_CLASS = "create-event__time";
    public static final String MESSAGE_NO_RESULTS_CLASS = "search__no-results";
    public static final String ARENA_LIST_CLASS = "GrayRoundedSelect_dropdownItem__18xe6";
    public static final String FILTERS_LIST_CLASS = "GrayRoundedSelect_select__1yKm5";
    public static final String CALENDAR_DATE_TEXT_CLASS ="NavLink_active__2Lz1H";
    public static final String CALENDAR_DATE_CLASS ="NavLink_navLink__3OIyY";
    public static final String LIST_PRICE_CLASS ="create-event__price";
    public static final String NAME_ARENA_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[3]/div[1]/div[1]/div/div";
    public static final String TIME_TEXT_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[4]/div/div/div[3]/div[2]/div/div";
    public static final String EVENTS_AND_CREATE_GAMES_TABS_CLASS = "Tabs_tab__3e8GV";
    public static final String ARENA_NAME_CLASS = "create-event__playground-name";
    public static final String CHOSEN_DATE_XPATH = "//*[@id=\"root\"]/div[2]/div[1]/div/div[1]/div/div[13]/span[2]";
    public static final String PRICES_FOR_ARENA = "create-event__prices";
    public static final String ARENA_SECTION_CLASS = "create-event";
    public static final String ARENA_SECTION_TIME_OPTIONS_CLASS = "Select_control__3L5dE";
    public static final String CREATE_GAME_FOR_ARENA_BUTTON_CLASS = "button";
    public static final String LOADER_WRAPPER_CLASS = "Loader_wrapper__YL9BV";


    String time;

    private WebDriver driver;
    public EventsPage(WebDriver driver) {
        this.driver = driver;
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));;

    public WebElement getLoaderWrapper(){
        WebElement loaderWrapper = driver.findElement(By.className(LOADER_WRAPPER_CLASS));
        return loaderWrapper;
    }

    public List<WebElement> getArenaSection(){
        List<WebElement> arenaSection = driver.findElements(By.className(ARENA_SECTION_CLASS));
        return arenaSection;
    }

    public WebElement getArenaName(int index){
        WebElement arenaName = getArenaSection().get(index).findElement(By.className(ARENA_NAME_CLASS));
        return arenaName;
    }

    public List<WebElement> getArenaTimeTable(int index){
        List<WebElement> arenaTimeTable = getArenaSection().get(index).findElements(By.className(TIME_LIST_CLASS));
        return arenaTimeTable;
    }

    public WebElement getGameDurationOptions(int index){
        WebElement gameDurationOptions = getArenaSection().get(index).findElements(By.className(ARENA_SECTION_TIME_OPTIONS_CLASS)).get(0);
        return gameDurationOptions;
    }
    public WebElement getGameStartOptions(int index){
        WebElement gameStartOptions = getArenaSection().get(index).findElements(By.className(ARENA_SECTION_TIME_OPTIONS_CLASS)).get(1);
        return gameStartOptions;
    }

    public WebElement getCreateGameForArenaButton(int index){
        WebElement createGameForArenaButton =  getArenaSection().get(index).findElement(By.className(CREATE_GAME_FOR_ARENA_BUTTON_CLASS));
        return createGameForArenaButton;
    }

    public List<WebElement> getPricesForEachArena(){
        List<WebElement> pricesForEachArena = driver.findElements(By.className(PRICES_FOR_ARENA));
        return pricesForEachArena;
    }

    public List<WebElement> getEventsAndCreateGamesTabs(){
        List<WebElement> eventsAndCreateGamesTabs = driver.findElements(By.className(EVENTS_AND_CREATE_GAMES_TABS_CLASS));
        return eventsAndCreateGamesTabs;
    }

    public List<WebElement> getArenaName(){
        List<WebElement> arenaName = driver.findElements(By.className(ARENA_NAME_CLASS));
        return arenaName;
    }
    public List<WebElement> getDurationList(){
        return driver.findElements(By.className(DROP_DOWN_LIST_CLASS));
    }

    public List<WebElement> getStartTimeList(){
        return driver.findElements(By.className(DROP_DOWN_LIST_CLASS));
    }

    public WebElement getChosenDate(){
        return driver.findElement(By.xpath(CHOSEN_DATE_XPATH));
    }

    public WebElement getArenaFilter(){
        return driver.findElement(By.xpath(ARENA_FILTER_XPATH));
    }
    public WebElement getButtonCreateGame(){
        return driver.findElement(By.xpath(BUTTON_CREATE_GAME_XPATH));
    }
    public WebElement getStartTime(){
        return driver.findElement(By.xpath(START_TIME_XPATH));
    }
    public List<WebElement> getDropDownList(){
        return driver.findElements(By.className(DROP_DOWN_LIST_CLASS));
    }

    public WebElement getDuration(){
        return driver.findElement(By.xpath(DURATION_XPATH));
    }
    public List<WebElement> getTimeList(){
        return driver.findElements(By.className(TIME_LIST_CLASS));
    }
    public List<WebElement> getMessageNoResults(){
        return driver.findElements(By.className(MESSAGE_NO_RESULTS_CLASS));
    }
    public List<WebElement> getArenaList(){
        return driver.findElements(By.className(ARENA_LIST_CLASS));
    }
    public List<WebElement> getFiltersList(){
        return driver.findElements(By.className(FILTERS_LIST_CLASS));
    }
    public String getCalendarDateText(){
        return driver.findElement(By.className(CALENDAR_DATE_TEXT_CLASS)).getText();
    }
    public List<WebElement> getCalendarDateList(){
        return driver.findElements(By.className(CALENDAR_DATE_CLASS));
    }
    public List<WebElement> getListPrice(){
        return driver.findElements(By.className(LIST_PRICE_CLASS));
    }
    public String getNameArena(){
        return driver.findElement(By.xpath(NAME_ARENA_XPATH)).getText();
    }

    public String getTimeText(){
        return driver.findElement(By.xpath(TIME_TEXT_XPATH)).getText();
    }

    public void chooseRandomDate() {
        int randomDate = ThreadLocalRandom.current().nextInt(1,
                getCalendarDateList().size() - 1);
        getCalendarDateList().get(randomDate).click();
    }

    public void chooseRandomArena()  {
        do {
            getFiltersList().get(0).click();
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            int randomArena = ThreadLocalRandom.current().nextInt(1,
                    getArenaList().size() - 1);
            getArenaList().get(randomArena).click();
        }
        while (!getMessageNoResults().isEmpty());
    }

    public void chooseRandomPrice() {
        if (getTimeList().size() > 1) {
            int randomPrice = ThreadLocalRandom.current().nextInt(0,
                    getTimeList().size() - 1);
            getTimeList().get(randomPrice).click();
        } else {
            getTimeList().get(0).click();
        }
    }

    public void chooseRandomTime() {
        int randomStartTime = ThreadLocalRandom.current().nextInt(4,
                getDropDownList().size() - 1);
        getDropDownList().get(randomStartTime).click();
    }

    public String getTimeCalculation(){
        String timeText = getTimeText(); // bag: время -2 часа
        String hourText = timeText.substring(0, 2);
        String minuteText = timeText.substring(2);
        int hour = Integer.parseInt(hourText) - 2;
        String newHour = String.valueOf(hour);
        if (hour < 10) {
            time = "0" + newHour + minuteText;
        } else {
            time = newHour + minuteText;
        }
        return time;
    }

    public void waitArenaFilter( ) {
    wait.until(ExpectedConditions.textToBe(By.xpath(ARENA_FILTER_XPATH), "Все арены"));
    }

}
