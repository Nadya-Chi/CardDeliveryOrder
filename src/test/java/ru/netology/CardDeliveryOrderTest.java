package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSubmitRequest() {
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Санкт-Петербург")).click();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79210000000");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000).shouldHave(exactText("Успешно! Встреча успешно забронирована на " + date));
    }

    @Test
    public void requestWithInvalidCity() {
        $("[data-test-id=city] input").setValue("Saint");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79210000000");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=city] [class='input__sub']").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void requestWithoutCity() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79210000000");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=city] [class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void requestWithInvalidName() {
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Санкт-Петербург")).click();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=phone] input").setValue("+79210000000");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=name] [class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void requestWithoutName() {
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Санкт-Петербург")).click();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=phone] input").setValue("+79210000000");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=name] [class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void requestWithInvalidPhone() {
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Санкт-Петербург")).click();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+792100000");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=phone] [class='input__sub']").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void requestWithoutPhone() {
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Санкт-Петербург")).click();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=phone] [class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void requestWithoutCheckbox() {
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Санкт-Петербург")).click();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79210000000");
        $("[class='button__text']").click();
        $(".input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
