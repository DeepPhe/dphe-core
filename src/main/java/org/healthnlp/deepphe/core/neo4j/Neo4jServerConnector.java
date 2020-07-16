package org.healthnlp.deepphe.core.neo4j;


import org.apache.log4j.Logger;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.kernel.impl.store.kvstore.RotationTimeoutException;
import org.neo4j.kernel.lifecycle.LifecycleException;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/26/2017
 */
public enum Neo4jServerConnector {
   INSTANCE;

   public static Neo4jServerConnector getInstance() {
      return INSTANCE;
   }

   static private final Logger LOGGER = Logger.getLogger( "Neo4jServerConnector" );


   private String _url = "";
   private Driver _driver;


   public Driver createDriver( final String url, final String user, final String pass ) {
      if ( _driver != null ) {
         return _driver;
      }
      if ( url.equals( "Local" ) && user.equals( "Me" ) && pass.equals( "None" ) ) {
         return null;
      }
      _driver = GraphDatabase.driver( url, AuthTokens.basic( user, pass ) );

      registerShutdownHook( _driver );
      _url = url;
      return _driver;
   }

   public String getUrl() {
      return _url;
   }

   public Driver getDriver() {
      return _driver;
   }

   static private void registerShutdownHook( final Driver driver ) {
      // Registers a shutdown hook for the Neo4j instance so that it
      // shuts down nicely when the VM exits (even if you "Ctrl-C" the
      // running application).
      Runtime.getRuntime().addShutdownHook( new Thread( () -> {
         try {
            driver.close();
         } catch ( LifecycleException | RotationTimeoutException multE ) {
            // ignore
         }
      } ) );
   }


}
