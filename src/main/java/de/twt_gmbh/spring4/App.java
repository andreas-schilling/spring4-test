package de.twt_gmbh.spring4;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import de.twt_gmbh.spring4.model.Company;
import de.twt_gmbh.spring4.model.Person;
import de.twt_gmbh.spring4.repositories.CompanyRepository;
import de.twt_gmbh.spring4.repositories.PersonRepository;
import de.twt_gmbh.spring4.service.Repositories;


public class App
{
  private static final Logger LOGGER = LoggerFactory.getLogger( App.class );


  public static void main( final String[] args )
  {
    BasicConfigurator.configure();
    final ApplicationContext context =
            new FileSystemXmlApplicationContext(
                    "src/main/java/de/twt_gmbh/spring4/application-config.xml" );
    final Repositories repos = context.getBean( Repositories.class );
    final PersonRepository personRepo = repos.getRepository( Person.class );
    final CompanyRepository companyRepo = repos.getRepository( Company.class );

    LOGGER.debug( "Number of persons: " + personRepo.count() );
    final Company twt = new Company();
    twt.setName( "TWT GmbH" );
    companyRepo.save( twt );
    LOGGER.debug( "Number of companies: " + companyRepo.count() );

    final Person hansWurst = new Person();
    hansWurst.setName( "Hans" );
    hansWurst.setSurname( "Wurst" );
    hansWurst.setCompany( twt );
    personRepo.save( hansWurst );
    LOGGER.debug( "Number of persons: " + personRepo.count() );

    System.exit( 0 );
  }
}
