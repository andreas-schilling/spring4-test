package de.twt_gmbh.spring4.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import de.twt_gmbh.spring4.model.Company;


public interface CompanyRepository extends CrudRepository<Company, Long>,
        JpaSpecificationExecutor<Company>
{
}
