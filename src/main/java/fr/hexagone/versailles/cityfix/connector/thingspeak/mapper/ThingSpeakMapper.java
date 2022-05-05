package fr.hexagone.versailles.cityfix.connector.thingspeak.mapper;

import fr.hexagone.versailles.cityfix.connector.thingspeak.dto.ThingSpeakEntry;
import fr.hexagone.versailles.cityfix.domain.CaptorObject;
import org.mapstruct.*;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ThingSpeakMapper {

    @Mapping(source = "field1", target = "temperature")
    @Mapping(source = "field2", target = "humidity")
    @Mapping(source = "field3", target = "air")
    CaptorObject mapToCaptorObject(ThingSpeakEntry thingSpeakEntry);

}
