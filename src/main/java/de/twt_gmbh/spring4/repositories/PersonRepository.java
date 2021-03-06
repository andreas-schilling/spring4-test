package de.twt_gmbh.spring4.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import de.twt_gmbh.spring4.model.Person;


public interface PersonRepository extends CrudRepository<Person, Long>,
        JpaSpecificationExecutor<Person>
{
}
