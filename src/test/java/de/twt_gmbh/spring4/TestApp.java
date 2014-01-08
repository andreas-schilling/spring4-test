package de.twt_gmbh.spring4;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Iterables;

import de.twt_gmbh.spring4.model.Company;
import de.twt_gmbh.spring4.model.Company_;
import de.twt_gmbh.spring4.model.Person;
import de.twt_gmbh.spring4.repositories.CompanyRepository;
import de.twt_gmbh.spring4.repositories.PersonRepository;
import de.twt_gmbh.spring4.service.Repositories;


public class TestApp
{
  private static PersonRepository personRepo;
  private static CompanyRepository companyRepo;


  @BeforeClass
  public static void initializeTest()
  {
    BasicConfigurator.configure();
    final ApplicationContext context =
            new FileSystemXmlApplicationContext(
                    "src/main/java/de/twt_gmbh/spring4/application-config.xml" );
    final Repositories repos = context.getBean( Repositories.class );
    personRepo = repos.getRepository( Person.class );
    companyRepo = repos.getRepository( Company.class );
  }


  @Before
  public void clearRepositories()
  {
    personRepo.deleteAll();
    companyRepo.deleteAll();
  }


  @Test
  public void testInitialRepositoriesAreEmpty()
  {
    Assert.assertEquals( 0L, personRepo.count() );
    Assert.assertEquals( 0L, companyRepo.count() );
  }


  @Test
  public void testInsertOneCompany()
  {
    final Company twt = new Company();
    twt.setName( "TWT GmbH" );
    companyRepo.save( twt );

    Assert.assertEquals( 1L, companyRepo.count() );
  }


  @Test
  public void testInsertAndDeleteOneCompany()
  {
    final Company twt = new Company();
    twt.setName( "TWT GmbH" );
    companyRepo.save( twt );
    Assert.assertEquals( 1L, companyRepo.count() );
    companyRepo.delete( twt );
    Assert.assertEquals( 0L, companyRepo.count() );
  }


  @Test
  public void testInsertOneCompanyAndPerson()
  {
    final Company twt = new Company();
    twt.setName( "TWT GmbH" );
    companyRepo.save( twt );

    final Person hansWurst = new Person();
    hansWurst.setName( "Hans" );
    hansWurst.setSurname( "Wurst" );
    hansWurst.setCompany( twt );
    hansWurst.setAge( 31L );
    personRepo.save( hansWurst );

    Assert.assertEquals( 1L, companyRepo.count() );
    Assert.assertEquals( 1L, personRepo.count() );
    Assert.assertEquals( twt, Iterables.getOnlyElement( personRepo.findAll() )
            .getCompany() );
    Assert.assertEquals( hansWurst,
            Iterables.getOnlyElement( companyRepo.findAll( withEagerEmployees() ) )
                    .getEmployees().get( 0 ) );
  }


  private Specification<Company> withEagerEmployees()
  {
    return new Specification<Company>()
    {
      @Override
      public Predicate toPredicate( final Root<Company> root,
              final CriteriaQuery<?> query, final CriteriaBuilder cb )
      {
        root.fetch( Company_.employees );
        return null;
      }
    };
  }
}
