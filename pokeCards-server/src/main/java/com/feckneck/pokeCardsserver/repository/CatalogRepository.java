package com.feckneck.pokeCardsserver.repository;

import com.feckneck.pokeCardsserver.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

}
