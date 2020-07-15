package org.healthnlp.deepphe.core.ae;

import org.apache.ctakes.core.pipeline.PipeBitInfo;
import org.apache.ctakes.core.util.log.DotLogger;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.core.neo4j.Neo4jEmbeddedConnector;
import org.healthnlp.deepphe.core.neo4j.Neo4jServerConnector;

import java.io.IOException;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 1/16/2018
 */
@PipeBitInfo(
      name = "Neo4jEmbeddedConnectAe",
      description = "Connects to neo4j session on initialization.",
      role = PipeBitInfo.Role.SPECIAL
)
final public class Neo4jEmbeddedConnectAe extends JCasAnnotator_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "Neo4jEmbeddedConnectAe" );

   public static final String PARAMETER_NEO4J_URI = "Neo4jUri";
   public static final String PARAMETER_NEO4J_USER = "Neo4jUser";
   public static final String PARAMETER_NEO4J_PASS = "Neo4jPass";
   @ConfigurationParameter(
         name = PARAMETER_NEO4J_URI,
         description = "The URI to the neo4j server."
   )
   private String _neo4jUri;

   @ConfigurationParameter(
         name = PARAMETER_NEO4J_USER,
         description = "The User name for the neo4j server."
   )
   private String _neo4jUser;

   @ConfigurationParameter(
         name = PARAMETER_NEO4J_PASS,
         description = "The User password for the neo4j server."
   )
   private String _neo4jPass;

   /**
    * {@inheritDoc}
    */
   @Override
   public void initialize( final UimaContext context ) throws ResourceInitializationException {
      super.initialize( context );
      LOGGER.info( "Loading Graph ..." );
      try ( DotLogger dotLogger = new DotLogger() ) {
            Neo4jServerConnector.getInstance()
                                .createDriver( _neo4jUri, _neo4jUser, _neo4jPass );
      } catch ( IOException ioE ) {
         // Do nothing
      }
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jCas ) throws AnalysisEngineProcessException {
      // Do nothing
   }


}
