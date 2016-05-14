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


}
