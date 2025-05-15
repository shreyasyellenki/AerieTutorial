package missionmodel;

import gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.DiscreteEffects;
import gov.nasa.jpl.aerie.merlin.framework.annotations.ActivityType;
import gov.nasa.jpl.aerie.merlin.framework.annotations.Export.Parameter;
import gov.nasa.jpl.aerie.merlin.framework.annotations.Export.Validation;

import gov.nasa.jpl.aerie.merlin.protocol.types.Duration;

import static gov.nasa.jpl.aerie.merlin.framework.ModelActions.delay;
import static gov.nasa.jpl.aerie.merlin.protocol.types.Duration.SECONDS;

@ActivityType("CollectData")
public class CollectData {

  @Parameter
  public double rate = 10.0;
  @Parameter
  public Duration duration = Duration.duration(1,Duration.HOURS);

  @Validation("Collection rate is beyond buffer limit of 100.0 Mbps")
  @Validation.Subject("rate")
  public boolean validateCollectionRate() {
    return rate <= 100.0;
  }

  @ActivityType.EffectModel
  public void run(Mission model) {
    
    DiscreteEffects.increase(model.dataModel.RecordingRate, this.rate);
    delay(duration);
    DiscreteEffects.decrease(model.dataModel.RecordingRate, this.rate);

  }



}
