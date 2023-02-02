import core.data.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ProcessWeatherData {

  public static void main(String[] args) {
    DataSource ds =
        DataSource.connect("http://weather.gov/xml/current_obs/index.xml");
    ds.load();
    ArrayList<WeatherStation> allStations =
        ds.fetchList("WeatherStation", "station/station_name",
                     "station/station_id", "station/state", "station/latitude",
                     "station/longitude", "station/xml_url");
    System.out.println("Total stations: " + allStations.size());

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a state abbreviation: ");
    String state = sc.next();
    System.out.println("Stations in " + state);

    /* Traverse allStations, an ArrayList of WeatherStation objects
     *   - Determine if the station is in the State
     *   - If so:
     *         - get the URL from the WeatherStation  (call the accessor/getter)
     *         - use the same code above to read in the data into the
     *           StationObservation object
     *           => note: use similar process as above, but use fetch() instead
     *              of fetchList().  Same arguments.....
     *
     *
           - to get the URL from WeatherStation */
    var stateStations = new ArrayList<WeatherStation>();
    for (WeatherStation ws : allStations) {
      if (!state.equals(ws.getState())) {
        continue;
      }
      /* - store the reference, stationObs, in the WeatherStation
         - add WeatherStation to the stateStations ArrayList */
      try {
        DataSource ds2 = DataSource.connect(ws.getUrl());
        ds2.load();
        StationObservation stationObs = ds2.fetch(
            "StationObservation", "temp_f", "relative_humidity", "dewpoint_f");
        ws.setRef(stationObs);
      } catch (Exception e) {
        System.out.println("Exception reading entry: " + e);
        continue;
      }

      stateStations.add(ws);
    }

    /*
      Print out all the stations for the state
      Print out the total number of stations in the state
   */
    System.out.println("Stations in " + state);
    for (WeatherStation ws : stateStations) {
      System.out.println(ws);
    }
    System.out.printf("There are %d stations in %s.\n", stateStations.size(),
                      state);

    /* Traverse the stateStations ArrayList
     *    - determine southern and northern most stations
     *    - compute the average temperature of all the stations
     *    - determine the station with the max temperature
     *    - determine the station with the min temperature
     *
     * Display results.
     */
    Comparator<WeatherStation> latComp =
        Comparator.comparingDouble(WeatherStation::getLat);
    Comparator<WeatherStation> tempComp =
        Comparator.comparingDouble(ws -> ws.getRef().getTemp());
    var southernStation = stateStations.stream().min(latComp).orElseThrow();
    System.out.printf("Most Southern Station: %s Latitude: %f\n",
                      southernStation.getName(), southernStation.getLat());
    var northernStation = stateStations.stream().max(latComp).orElseThrow();
    System.out.printf("Most Northern Station: %s Latitude: %f\n",
                      northernStation.getName(), northernStation.getLat());
    System.out.printf("average Temp: %f\n",
                      stateStations.stream()
                          .mapToDouble(ws -> ws.getRef().getTemp())
                          .average()
                          .orElseThrow());
    var maxTempStation = stateStations.stream().max(tempComp).orElseThrow();
    System.out.printf("max Temp: %s: %f\n", maxTempStation.getName(),
                      maxTempStation.getRef().getTemp());
    var minTempStation = stateStations.stream().min(tempComp).orElseThrow();
    System.out.printf("min Temp: %s: %f\n", minTempStation.getName(),
                      minTempStation.getRef().getTemp());
  }
}
