package com.nissanusa.helios.ownerservice.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {

    @Produces
    @PersistenceContext(unitName = "persistence-unit-HeliosOSOracleDS", type = PersistenceContextType.TRANSACTION)
    private EntityManager osEm;

}
