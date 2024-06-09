package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.checkerframework.checker.units.qual.C;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement header = $("[data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item div");
    public final SelenideElement reloadButton = $("[data-test-id='action-reload']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    // private SelenideElement card1ID = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    // private SelenideElement card2ID = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashboardPage() {
        header.shouldBe(Condition.visible);
    }

   // public int getCardBalance(String maskedCardNumber) {
        //val text = cards.findBy(Condition.text(maskedCardNumber)).getText();
       // return extractBalance(text);
    //}

    public int getCardBalance(int index) {
        var text = cards.get(index).getText();
        return extractBalance(text);
    }

    public void reloadDashboardPage() {
        reloadButton.click();
        header.shouldBe(Condition.visible);

    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId())).$("button").click();
        return new TransferPage();
    }
    //public TransferPage clickOnSecondButton() {
    //card2ID.$("[data-test-id='action-deposit']").click();
    // return new TransferPage();
    // }
}