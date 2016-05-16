import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

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

  @Test
  public void individualBandPageIsDisplayed() {
    Band testBand = new Band("People Under The Stairs");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("People Under The Stairs");
  }

  @Test
  public void bandFormIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("Add New Band"));
    assertThat(pageSource()).contains("Add New Band");
  }

  @Test
  public void bandNameIsUpdated() {
    Band testBand = new Band("Mos Def");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fill("#update").with("Yasiin Bey");
    submit("#update-submit");
    assertThat(pageSource().contains("Yasiin Bey"));
  }

  @Test
  public void bandIsDeleted() {
    Band testBand = new Band("GZA");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    submit("#delete-band");
    assertFalse(pageSource().contains("GZA"));
  }

  @Test
  public void venueIsAddedToBand() {
    Band testBand = new Band("People Under The Stairs");
    testBand.save();
    Venue testVenue = new Venue("Doug Fir Lounge");
    testVenue.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fill("#venue-name").with("Doug Fir Lounge");
    submit("#add-venue");
    assertThat(pageSource()).contains("Doug Fir Lounge");
  }

  @Test
  public void allVenuesPageIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("View All Venues"));
    assertThat(pageSource().contains("All Venues"));
  }


  @Test
  public void individualVenuePageIsDisplayed() {
    Venue testVenue = new Venue("The Palladium");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    assertThat(pageSource()).contains("The Palladium");
  }
}
