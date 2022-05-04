package fr.hexagone.versailles.cityfix.service.context;

import fr.hexagone.versailles.cityfix.domain.ItemAlert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterService implements GenericContext {

    @Override
    public Integer getSumContext() {
        return null;
    }

    @Override
    public List<ItemAlert> getAlerts() {
        return null;
    }

    @Override
    public Integer getTotalElements() {
        return null;
    }

    @Override
    public Integer getAlertElements() {
        return null;
    }

}
