package com.example.epms.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface DtoEntityMapper<D, E> {

    D toDto(E entity);

    E toEntity(D dto);

    default List<D> toDtoList(List<E> entityList) {
        if (entityList == null) return null;
        return entityList.stream().map(this::toDto).toList();
    }

    default List<E> toEntity(List<D> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream().map(this::toEntity).toList();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(D dto, @MappingTarget E entity);
}
