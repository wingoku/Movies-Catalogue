package com.wingoku.moviescatalogue.domain.mappers.interfaces;

import java.util.List;

public interface DomainMapper<DomainObject, DTOObject> {
    public DomainObject fromDTO(DTOObject dtoObject);
    public DTOObject toDTO(DomainObject domainObject);

    public List<DomainObject> fromDTO(List<DTOObject> dtoObjectList);
    public List<DTOObject> toDTO(List<DomainObject> domainObjectList);
}
