package org.healthnlp.deepphe.core.neo4j;


import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.kernel.impl.store.kvstore.RotationTimeoutException;
import org.neo4j.kernel.lifecycle.LifecycleException;

import java.io.File;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/26/2017
 */
public enum Neo4jEmbeddedConnector {
   INSTANCE;

   public static Neo4jEmbeddedConnector getInstance() {
      return INSTANCE;
   }

   static private final Logger LOGGER = Logger.getLogger( "Neo4jEmbeddedConnector" );

   static private final String LOCAL_GRAPH_DB = "resources/graph/neo4j/ontology.db";

   private GraphDatabaseService _graphDb;


   private String getEmbeddedGraphDb() {
      return LOCAL_GRAPH_DB;
   }

   public GraphDatabaseService connectToGraph() {
      return connectToGraph( getEmbeddedGraphDb() );
   }

   synchronized public GraphDatabaseService connectToGraph( final String graphDbPath ) {
      if ( _graphDb != null ) {
         return _graphDb;
      }
      final File graphDbFile = new File( graphDbPath );
      if ( !graphDbFile.isDirectory() ) {
         LOGGER.error( "No Database exists at: " + graphDbPath );
         System.exit( -1 );
      }
//      _graphDb = new GraphDatabaseFactory()
//            .newEmbeddedDatabase( graphDbFile );
      _graphDb = new GraphDatabaseFactory()
            .newEmbeddedDatabaseBuilder( graphDbFile )
            .setConfig( GraphDatabaseSettings.read_only, "true" )
            .newGraphDatabase();
      if ( !_graphDb.isAvailable( 500 ) ) {
         LOGGER.error( "Could not initialize neo4j connection for: " + graphDbPath );
         System.exit( -1 );
      }
      registerShutdownHook( _graphDb );
      return _graphDb;
   }


   public GraphDatabaseService getGraph() {
      if ( _graphDb == null ) {
         return connectToGraph();
      }
      return _graphDb;
   }

   static private void registerShutdownHook( final GraphDatabaseService graphDb ) {
      // Registers a shutdown hook for the Neo4j instance so that it
      // shuts down nicely when the VM exits (even if you "Ctrl-C" the
      // running application).
      Runtime.getRuntime().addShutdownHook( new Thread( () -> {
         try {
            graphDb.shutdown();
         } catch ( LifecycleException | RotationTimeoutException multE ) {
            // ignore
         }
      } ) );
   }


}
