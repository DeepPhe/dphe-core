package org.healthnlp.deepphe.core.ae;

import org.apache.ctakes.core.pipeline.PipeBitInfo;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.core.neo4j.Neo4jServerConnector;
import org.neo4j.driver.v1.Driver;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 6/15/2020
 */
@PipeBitInfo(
      name = "Neo4jDriverConnector",
      description = "For deepphe.", role = PipeBitInfo.Role.SPECIAL
)
final public class Neo4jServerConnectAe extends JCasAnnotator_ImplBase {

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


   static private final Logger LOGGER = Logger.getLogger( "Neo4jServerConnectAe" );

   /**
    * {@inheritDoc}
    */
   @Override
   public void initialize( final UimaContext uimaContext ) throws ResourceInitializationException {
      LOGGER.info( "Initializing Neo4j Driver ..." );
      // The super.initialize call will automatically assign user-specified values for to ConfigurationParameters.
      super.initialize( uimaContext );
      try {
         final Driver driver = Neo4jServerConnector.getInstance().createDriver( _neo4jUri, _neo4jUser, _neo4jPass );
      } catch ( Exception e ) {
         throw new ResourceInitializationException( e );
      }
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void process( final JCas jCas ) throws AnalysisEngineProcessException {
      // Does nothing.
   }


}
