package fr.hexagone.versailles.cityfix.connector.spinalcore.mapper;

import fr.hexagone.versailles.cityfix.connector.spinalcore.dto.*;
import fr.hexagone.versailles.cityfix.domain.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SpinalCoreMapper {

    BasicObject mapToBasicObject(SpinalCoreBasicObject spinalCoreBasicObject);

    Ticket mapToTicket(SpinalCoreTicketDetails spinalCoreTicketDetails);

    SpinalCoreTicketCreation mapToSpinalCoreTicketCreation(TicketCreation ticketCreation);

    ControlEndpoint mapToControlEndpoint(SpinalCoreControlEndpoint spinalCoreControlEndpoint);

    RoomControlEndpoints mapToRoomControlEndpoints(SpinalCoreControlEndpointResponse spinalCoreControlEndpointResponse);

}
