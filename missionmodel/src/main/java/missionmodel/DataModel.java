package missionmodel;

import gov.nasa.jpl.aerie.contrib.serialization.mappers.EnumValueMapper;
import gov.nasa.jpl.aerie.contrib.streamline.core.MutableResource;
import gov.nasa.jpl.aerie.contrib.streamline.core.Resource;
import gov.nasa.jpl.aerie.contrib.streamline.modeling.Registrar;
import gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.Discrete;
import gov.nasa.jpl.aerie.contrib.serialization.mappers.DoubleValueMapper;

import static gov.nasa.jpl.aerie.contrib.streamline.core.MutableResource.resource;
import static gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.Discrete.discrete;
import static gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.monads.DiscreteResourceMonad.map;


public class DataModel {
  public MutableResource<Discrete<Double>> RecordingRate; // Megabits/s
  public MutableResource<Discrete<MagDataCollectionMode>> MagDataMode;
  public Resource<Discrete<Double>> MagDataRate;

  public DataModel(Registrar registrar) {
    RecordingRate = resource(discrete(0.0));
    registrar.discrete("RecordingRate", RecordingRate, new DoubleValueMapper());
    MagDataMode = resource(discrete(MagDataCollectionMode.OFF));
    registrar.discrete("MagDataMode",MagDataMode, new EnumValueMapper<>(MagDataCollectionMode.class));
    MagDataRate = map(MagDataMode, MagDataCollectionMode::getDataRate);
    registrar.discrete("MagDataRate", MagDataRate, new DoubleValueMapper());
  }
}
