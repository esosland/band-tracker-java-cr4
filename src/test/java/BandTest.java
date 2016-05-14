import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiatesCorrectly_true() {
    Band myBand = new Band("The Roots");
    assertEquals(true, myBand instanceof Band);
  }

  @Test
  public void getName_bandInstantiatesWithName_String() {
    Band myBand = new Band("Anderson Paak");
    assertEquals("Anderson Paak", myBand.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBandsAreTheSame_true() {
    Band firstBand = new Band("Kendrick Lamar");
    Band secondBand = new Band("Kendrick Lamar");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesBandCorrectly_1() {
    Band newBand = new Band("Santigold");
    newBand.save();
    assertEquals(1, Band.all().size());
  }

  @Test
  public void find_findsBandInDatabase_true() {
    Band myBand = new Band("People Under The Stairs");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void update_updatesBandName_true() {
    Band myBand = new Band("Santogold");
    myBand.save();
    myBand.update("Santigold");
    assertEquals("Santigold", Band.find(myBand.getId()).getName());
  }

  @Test
  public void delete_deletesBandName_true() {
    Band myBand = new Band("The Internet");
    myBand.save();
    int myBandId = myBand.getId();
    myBand.delete();
    assertEquals(null, Band.find(myBandId));
  }

  @Test
  public void addVenue_addVenueToBand() {
    Venue myVenue = new Venue("Mississippi Studios");
    myVenue.save();
    Band myBand = new Band("Jurassic Five");
    myBand.save();
    myBand.addVenue(myVenue);
    Venue savedVenue = myBand.getVenues().get(0);
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void getVenues_returnsAllVenues_emptyList() {
    Venue myVenue = new Venue("Doug Fir Lounge");
    myVenue.save();
    Band myBand = new Band("Janelle Monae");
    myBand.save();
    List savedVenues = myBand.getVenues();
    assertEquals(0, savedVenues.size());
  }



}
