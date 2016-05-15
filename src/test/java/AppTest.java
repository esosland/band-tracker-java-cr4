import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker");
  }

  @Test
  public void allBandsPageIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("View All Bands"));
    assertThat(pageSource().contains("All Bands"));
  }

  // @Test
  // public void individualBandPageIsDisplayed() {
  //   Band testBand = new Band("People Under The Stairs");
  //   testBand.save();
  //   String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
  //   goTo(url);
  //   assertThat(pageSource()).contains("People Under The Stairs");
  // }

}
