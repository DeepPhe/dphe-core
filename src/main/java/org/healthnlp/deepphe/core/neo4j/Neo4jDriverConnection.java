package org.healthnlp.deepphe.core.neo4j;


import org.healthnlp.deepphe.neo4j.driver.DriverFactory;
import org.neo4j.driver.Driver;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/26/2017
 */
public enum Neo4jDriverConnection {
   INSTANCE;

   public static Neo4jDriverConnection getInstance() {
      return INSTANCE;
   }

   private String _url = "";
   private Driver _driver;


   public Driver createDriver( final String url, final String user, final String pass ) {
      if ( _driver != null ) {
         return _driver;
      }
      if ( url.equals( "Local" ) && user.equals( "Me" ) && pass.equals( "None" ) ) {
         return null;
      }
      _driver = DriverFactory.createDriver( url, user, pass );
      _url = url;
      return _driver;
   }

   public String getUrl() {
      return _url;
   }

   public Driver getDriver() {
      return _driver;
   }

}
