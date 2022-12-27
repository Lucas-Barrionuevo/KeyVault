package com.keyVault.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyVault.app.entity.Icon;

public interface IcoRepository extends JpaRepository<Icon, Integer> {

}
