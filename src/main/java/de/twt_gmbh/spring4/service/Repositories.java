package de.twt_gmbh.spring4.service;

import org.springframework.data.repository.CrudRepository;

import de.twt_gmbh.spring4.model.Company;
import de.twt_gmbh.spring4.model.Person;
import de.twt_gmbh.spring4.repositories.CompanyRepository;
import de.twt_gmbh.spring4.repositories.PersonRepository;


public class Repositories
{
  private PersonRepository personRepository;

  private CompanyRepository companyRepository;


  public Repositories()
  {
    super();
  }


  public void setPersonRepository( final PersonRepository personRepository )
  {
    this.personRepository = personRepository;
  }


  public void setCompanyRepository( final CompanyRepository companyRepository )
  {
    this.companyRepository = companyRepository;
  }


  public <T, R extends CrudRepository<T, Long>> R getRepository( final Class<T> clazz )
  {
    if( Person.class.isAssignableFrom( clazz ) )
    {
      return (R) personRepository;
    }
    else if( Company.class.isAssignableFrom( clazz ) )
    {
      return (R) companyRepository;
    }

    throw new IllegalArgumentException();
  }
}
