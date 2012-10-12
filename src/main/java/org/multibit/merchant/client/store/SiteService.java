package org.multibit.merchant.client.store;

import com.google.common.cache.CacheBuilderSpec;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.bundles.AssetsBundle;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import com.yammer.dropwizard.views.ViewMessageBodyWriter;
import org.multibit.merchant.client.store.health.SiteHealthCheck;
import org.multibit.merchant.client.store.resources.PublicResource;

/**
 * <p>Service to provide the following to application:</p>
 * <ul>
 * <li>Provision of access to resources</li>
 * </ul>
 * <p>Use <code>java -jar site-develop-SNAPSHOT.jar server site.yml</code> to start the site</p>
 *
 * @since 0.0.1
 *         
 */
public class SiteService extends Service<SiteConfiguration> {

  /**
   * Main entry point to the application
   *
   * @param args CLI arguments
   *
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    new SiteService().run(args);
  }

  private SiteService() {
    super("site");
    // TODO Work out why this has to be in the constructor or it fails
    CacheBuilderSpec cacheBuilderSpec = (System.getenv("FILE_CACHE_ENABLED") == null) ? CacheBuilderSpec.parse("maximumSize=0") : AssetsBundle.DEFAULT_CACHE_SPEC;
    addBundle(new AssetsBundle("/assets/css", cacheBuilderSpec, "/css"));
    addBundle(new AssetsBundle("/assets/js", cacheBuilderSpec, "/js"));
    addBundle(new AssetsBundle("/assets/images", cacheBuilderSpec, "/images"));
  }

  @Override
  protected void initialize(SiteConfiguration configuration,
                            Environment environment) {

    // Read the configuration

    // Configure authenticator

    // Start Spring context based on the provided location

    // Configure environment accordingly
    environment.scanPackagesForResourcesAndProviders(PublicResource.class);

    // Health checks
    environment.addHealthCheck(new SiteHealthCheck());

    // Providers
    environment.addProvider(new ViewMessageBodyWriter());

    // Bundles
    addBundle(new ViewBundle());

  }

}
