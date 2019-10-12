package com.freelancer.billing.service.impl;

import com.freelancer.billing.domain.Providers;
import com.freelancer.billing.exception.DuplicatedResourceException;
import com.freelancer.billing.exception.FieldType;
import com.freelancer.billing.repository.ProvidersRepository;
import com.freelancer.billing.service.ProvidersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvidersServiceImpl implements ProvidersService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProvidersServiceImpl.class);

    @Autowired
    ProvidersRepository repository;

    @Override
    public List<Providers> buscarTodo() {
        return repository.findAll();
    }

    @Override
    public Providers guardar(Providers providers) {

        if (repository.validateIfExistEmail(providers.getEmail()).isPresent()){
            throw new DuplicatedResourceException(FieldType.EMAIL, providers.getEmail());
        }

        return repository.save(providers);
    }

    @Override
    public Providers eliminar(Providers providers) {
       LOGGER.info("Deleting provider");
        repository.delete(providers);

        return providers;
    }

    @Override
    public Providers actualizar(Providers providers) {
        return repository.save(providers);
    }

    @Override
    public Providers buszarPorId(String id) {
        return repository.buscarPorId(id);
    }
}
