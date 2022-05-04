package fr.hexagone.versailles.cityfix.service.context;

import fr.hexagone.versailles.cityfix.domain.ItemAlert;
import fr.hexagone.versailles.cityfix.domain.ItemContext;

import java.math.BigDecimal;
import java.util.List;

public interface GenericContext {

    default ItemContext getContext() {
        return new ItemContext()
                .setSum(getSumContext())
                .setAlerts(getAlerts())
                .setTotalElements(getTotalElements())
                .setAlertElements(getAlertElements());
    }

    Integer getSumContext();

    List<ItemAlert> getAlerts();

    Integer getTotalElements();

    Integer getAlertElements();

}
