package com.example.banco_hex_yoder.postgresql_repository.repository;

import com.example.banco_hex_yoder.postgresql_repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Integer> {



}
