package com.freelancer.billing.service;

import com.freelancer.billing.domain.Providers;

import java.util.List;

public interface ProvidersService {

    List<Providers> buscarTodo();

    Providers guardar(Providers providers);

    Providers eliminar(Providers providers);

    Providers actualizar(Providers providers);

    Providers buszarPorId(String id);
}
