package org.kyungmin0729.repositories;

import org.kyungmin0729.entities.Configs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigsRepository extends JpaRepository<Configs, String> {
}