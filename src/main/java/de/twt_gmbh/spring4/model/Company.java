package de.twt_gmbh.spring4.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.Objects;


@Entity
@Table(name = "CT_COMPANIES")
public class Company
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COM_SEQ")
  @SequenceGenerator(name = "COM_SEQ", sequenceName = "COM_SEQ")
  @Column(name = "COM_ID")
  private Long id;

  @Column(name = "COM_NAME", unique = true)
  private String name;

  @OneToMany(mappedBy = "company")
  private List<Person> employees;


  @Override
  public int hashCode()
  {
    return Objects.hashCode( id, name );
  }


  @Override
  public boolean equals( final Object obj )
  {
    if( this == obj )
    {
      return true;
    }
    if( obj == null )
    {
      return false;
    }
    if( getClass() != obj.getClass() )
    {
      return false;
    }
    final Company otherCompany = (Company) obj;
    return Objects.equal( getId(), otherCompany.getId() );
  }


  public Long getId()
  {
    return id;
  }


  public void setId( final Long id )
  {
    this.id = id;
  }


  public String getName()
  {
    return name;
  }


  public void setName( final String name )
  {
    this.name = name;
  }


  public List<Person> getEmployees()
  {
    return employees;
  }


  public void setEmployees( final List<Person> employees )
  {
    this.employees = employees;
  }
}
