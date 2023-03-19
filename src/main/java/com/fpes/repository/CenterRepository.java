package com.fpes.repository;

import com.fpes.model.Center;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends CrudRepository<Center, Long> {
    Page<Center> findAll(Pageable pageable);

    Optional<Center> findCenterById(Long id);

//
@Query("SELECT DISTINCT d FROM Center d JOIN d.studies s WHERE "
        + "(:searchTerm IS NULL OR LOWER(d.name) LIKE %:searchTerm%) AND "
        + "(:region IS NULL OR LOWER(d.region) = LOWER(:region)) AND "
        + "(:province IS NULL OR LOWER(d.province) = LOWER(:province)) AND "
        + "(COALESCE(:type, d.naturalType) = d.naturalType) AND "
        + "(COALESCE(:format, s.format) = s.format)"
)
Page<Center> searchCenterByFilters(@Param("searchTerm") String searchTerm,
                                   @Param("region") String region,
                                   @Param("province") String province,
                                   @Param("type") List<String> type,
                                   @Param("format") List<String> format,
                                   Pageable page);

    @Query("SELECT DISTINCT d FROM Center d JOIN d.studies s WHERE "
            + "(COALESCE(:type, d.naturalType) = d.naturalType) AND "
            + "(COALESCE(:format, s.format) = s.format)"
    )
    Page<Center> searchCenterByFilters(
            @Param("format") List<String> format,
            @Param("type") List<String> type,
            Pageable page);


    @Query("SELECT DISTINCT d FROM Center d JOIN d.studies s WHERE "
            + "(:type IS NULL OR d.naturalType IN :type) "
    )
    Page<Center> searchCenterByFilters(
            @Param("type") List<String> type,
            Pageable page);

}

