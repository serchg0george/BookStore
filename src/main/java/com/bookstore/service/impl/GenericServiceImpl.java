package com.bookstore.service.impl;

import com.bookstore.dto.base.BaseDto;
import com.bookstore.dto.response.GenericResponse;
import com.bookstore.entity.base.BaseEntity;
import com.bookstore.exception.base.BaseNotFoundException;
import com.bookstore.mapper.base.BaseMapper;
import com.bookstore.service.GenericService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericServiceImpl<E extends BaseEntity, D extends BaseDto> implements GenericService<E, D> {

    public abstract BaseMapper<E, D> baseMapper();
    public abstract JpaRepository<E, Long> repository();

    @Override
    @Transactional
    public D create(D dto) {
        E entity = repository().save(baseMapper().mapDtoToEntity(dto));
        return baseMapper().mapEntityToDto(entity);
    }

    @Override
    public GenericResponse<D> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<E> entityPage = repository().findAll(pageable);
        List<E> entityList = entityPage.getContent();
        List<D> content = entityList.stream().map(baseMapper()::mapEntityToDto).toList();

        GenericResponse<D>  genericResponse = new GenericResponse<>();
        genericResponse.setContent(content);
        genericResponse.setPageNumber(entityPage.getNumber());
        genericResponse.setPageSize(entityPage.getSize());
        genericResponse.setTotalElements(entityPage.getTotalElements());
        genericResponse.setTotalPages(entityPage.getTotalPages());
        genericResponse.setLast(entityPage.isLast());

        return genericResponse;
    }

    @Override
    public D getbyId(Long id) {
        return repository()
                .findById(id)
                .map(baseMapper()::mapEntityToDto)
                .orElseThrow(() -> new BaseNotFoundException(id));
    }

    @Override
    @Transactional
    public D update(D dto, Long id) {
        E entity = repository().findById(id).orElseThrow(() -> new BaseNotFoundException(id));
        E updatedEntity = baseMapper().mapDtoToEntity(dto);
        updatedEntity.setId(entity.getId());
        return baseMapper().mapEntityToDto(repository().save(updatedEntity));
    }

    @Override
    public void delete(Long id) {
        if (repository().existsById(id)) {
            repository().deleteById(id);
        } else {
            throw new BaseNotFoundException(id);
        }
    }
}
