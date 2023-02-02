public class StationObservation {
  private double temperature;
  private double humidity;
  private double dew_point;

  public StationObservation(double temp, double hum, double dew) {
    temperature = temp;
    humidity = hum;
    dew_point = dew;
  }

  public double getTemp() { return temperature; }

  public double getHumidity() { return humidity; }

  public double getDewPont() { return dew_point; }

  public String toString() {
    return "StationObservation: temp=" + temperature +
        ", humdity= " + humidity + "\n";
  }
}
