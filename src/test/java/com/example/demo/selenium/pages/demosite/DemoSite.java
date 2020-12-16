package com.example.demo.selenium.pages.demosite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSite {
    // ATTRIBUTES
    public static final String URL = "http://127.0.0.1:9092/";

    @FindBy(xpath = "/html/body/h1/a")
    private WebElement createTaskList;

    @FindBy(xpath = "/html/body/div[1]/h1/a[1]")
    private WebElement createTask;

    @FindBy(xpath = "/html/body/div[1]/h1/a[2]")
    private WebElement updateTaskList;

    @FindBy(xpath = "/html/body/div[1]/ul/li[1]/a[1]")
    private WebElement updateTask;

    @FindBy(xpath = "/html/body/div[2]/h1/a[3]")
    private WebElement deleteTaskList;

    @FindBy(xpath = "/html/body/div[1]/ul/li[2]/a[2]")
    private WebElement deleteTask;

    // CONSTRUCTOR
    public DemoSite() {
    }

    // METHODS
    public void createTL() {
        createTaskList.click();
    }

    public void createT() {
        createTask.click();
    }

    public void updateTL() {
        updateTaskList.click();
    }

    public void updateT() {
        updateTask.click();
    }

    public void deleteTL() {
        deleteTaskList.click();
    }

    public void deleteT() {
        deleteTask.click();
    }


}
