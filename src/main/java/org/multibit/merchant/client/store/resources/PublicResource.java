package org.multibit.merchant.client.store.resources;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for generic template view</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class PublicResource {

  /**
   * Provide the initial view on to the system
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(maxAge = 60, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView retrieveByPage() {
    return new PublicFreemarkerView("site/index.ftl");
  }

  /**
   * Provide the initial view on to the system
   *
   * @return A localised view containing HTML
   */
  @GET
  @Path("{resource}")
  @Timed
  @CacheControl(maxAge = 60, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView retrieveByPage(@PathParam("resource") String resource) {
    return new PublicFreemarkerView("site/"+resource+".ftl");
  }

}
