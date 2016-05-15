import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Venue_instantiatesCorrectly_true() {
    Venue myVenue = new Venue("The Alhambra");
    assertEquals(true, myVenue instanceof Venue);
  }

  @Test
  public void getName_venueInstantiatesWithName_String() {
    Venue myVenue = new Venue("The Aladdin");
    assertEquals("The Aladdin", myVenue.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfVenuesAreTheSame_true() {
    Venue firstVenue = new Venue("Star Theater");
    Venue secondVenue = new Venue("Star Theater");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesVenueCorrectly_1() {
    Venue newVenue = new Venue("Roseland Theater");
    newVenue.save();
    assertEquals(1, Venue.all().size());
  }

  @Test
  public void find_findsVenueInDatabase_true() {
    Venue myVenue = new Venue("Hawthorne Theater");
    myVenue.save();
    Venue savedVenue = Venue.find(myVenue.getId());
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void addBand_addBandToVenue() {
    Venue myVenue = new Venue("The Wonder Ballroom");
    myVenue.save();
    Band myBand = new Band("Run the Jewels");
    myBand.save();
    myVenue.addBand(myBand);
    Band savedBand = myVenue.getBands().get(0);
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void getBands_returnsAllBands_List() {
    Venue myVenue = new Venue("Liquor Store");
    myVenue.save();
    Band myBand = new Band("A Tribe Called Quest");
    myBand.save();
    myVenue.addBand(myBand);
    List savedBands = myVenue.getBands();
    assertEquals(1, savedBands.size());
  }



}
