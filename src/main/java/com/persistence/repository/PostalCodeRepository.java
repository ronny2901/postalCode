package com.persistence.repository;


import com.persistence.entity.PostalCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeRepository extends CrudRepository<PostalCodeEntity, Long> {
}
