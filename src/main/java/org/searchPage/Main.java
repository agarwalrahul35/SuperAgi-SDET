package org.searchPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

       WebDriver driver  = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.amazon.in");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("LG Soundbar");
        searchBox.submit();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        List<WebElement> productNames = driver.findElements(By.cssSelector(".s-title-instructions-style"));
        List<WebElement> productPrices = driver.findElements(By.cssSelector(".a-price-whole"));

        Map<String, Integer> productMap = new HashMap<>();

        // Loop through the product names and prices
        for (int i = 0; i < productNames.size(); i++) {
            String name = productNames.get(i).getText();

            String priceString = (i < productPrices.size()) ? productPrices.get(i).getText().replace(",", "") : "0";

            int price = priceString.isEmpty() ? 0 : Integer.parseInt(priceString);

            productMap.put(name, price);
        }

        // Sort the products by price (ascending order)
        productMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {

                    System.out.println(  entry.getValue() + " " + entry.getKey() );
                });
        driver.quit();
    }

}