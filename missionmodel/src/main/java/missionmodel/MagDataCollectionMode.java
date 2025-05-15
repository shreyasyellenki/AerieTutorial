package missionmodel;

public enum MagDataCollectionMode {
  OFF(0.0),
  LOW_RATE(500.0),
  HIGH_RATE(5000.0);

  private final double magDataRate;

  MagDataCollectionMode(double magDataRate) {
    this.magDataRate = magDataRate;
  }
  public double getDataRate() {return magDataRate;}
}
