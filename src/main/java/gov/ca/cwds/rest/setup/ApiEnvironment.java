package gov.ca.cwds.rest.setup;

import gov.ca.cwds.rest.services.Service;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.jetty.MutableServletContextHandler;
import io.dropwizard.jetty.setup.ServletEnvironment;
import io.dropwizard.lifecycle.setup.LifecycleEnvironment;
import io.dropwizard.setup.AdminEnvironment;
import io.dropwizard.setup.Environment;

import java.util.concurrent.ExecutorService;

import javax.servlet.Servlet;
import javax.validation.Validator;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Decorator for {@link Environment}.  Adds ability to register {@link Service}
 * 
 * @author CWDS API Team
 */
public class ApiEnvironment {
	private Environment environment;
	private ServiceEnvironment serviceEnvironment;

	public ApiEnvironment(Environment environment) {
		this.environment = environment;
		this.serviceEnvironment = new ServiceEnvironment();
	}

	/**
	 * @see io.dropwizard.setup.Environment#jersey()
	 * 
	 * {@inheritDoc}
	 */
	public JerseyEnvironment jersey() {
		return environment.jersey();
	}

	/**
	 * @see io.dropwizard.setup.Environment#getHealthCheckExecutorService()
	 * 
	 * {@inheritDoc}
	 */
	public ExecutorService getHealthCheckExecutorService() {
		return environment.getHealthCheckExecutorService();
	}

	/**
	 * @see io.dropwizard.setup.Environment#admin()
	 * 
	 * {@inheritDoc} 
	 */
	public AdminEnvironment admin() {
		return environment.admin();
	}

	/**
	 * @see io.dropwizard.setup.Environment#lifecycle()
	 * 
	 * {@inheritDoc} 
	 */
	public LifecycleEnvironment lifecycle() {
		return environment.lifecycle();
	}

	/**
	 * @see io.dropwizard.setup.Environment#servlets()
	 *
	 * {@inheritDoc}
	 */
	public ServletEnvironment servlets() {
		return environment.servlets();
	}

	/**
	 * @see io.dropwizard.setup.Environment#getObjectMapper()
	 * 
	 * {@inheritDoc}
	 */
	public ObjectMapper getObjectMapper() {
		return environment.getObjectMapper();
	}

	/**
	 * @see io.dropwizard.setup.Environment#getName()
	 * 
	 * {@inheritDoc}
	 */
	public String getName() {
		return environment.getName();
	}

	/**
	 * @see io.dropwizard.setup.Environment#getValidator()
	 * 
	 * {@inheritDoc}
	 */
	public Validator getValidator() {
		return environment.getValidator();
	}

	/**
	 * @see io.dropwizard.setup.Environment#setValidator(javax.validation.Validator)
	 * 
	 * {@inheritDoc}
	 */
	public void setValidator(Validator validator) {
		environment.setValidator(validator);
	}

	/**
	 * @see io.dropwizard.setup.Environment#metrics()
	 * 
	 * {@inheritDoc}
	 */
	public MetricRegistry metrics() {
		return environment.metrics();
	}

	/**
	 * @see io.dropwizard.setup.Environment#healthChecks()
	 * 
	 * {@inheritDoc}
	 */
	public HealthCheckRegistry healthChecks() {
		return environment.healthChecks();
	}

	/**
	 * @see io.dropwizard.setup.Environment#getApplicationContext()
	 * 
	 * {@inheritDoc}
	 */
	public MutableServletContextHandler getApplicationContext() {
		return environment.getApplicationContext();
	}

	/**
	 * @see io.dropwizard.setup.Environment#getJerseyServletContainer()
	 * 
	 * {@inheritDoc}
	 */
	public Servlet getJerseyServletContainer() {
		return environment.getJerseyServletContainer();
	}

	/**
	 * @see io.dropwizard.setup.Environment#getAdminContext()
	 * 
	 * {@inheritDoc}
	 */
	public MutableServletContextHandler getAdminContext() {
		return environment.getAdminContext();
	}
	
	/**
	 * Get the {@link ServiceEnvironment}
	 * 
	 * @return The {@link ServiceEnvironment} for this application
	 */
	public ServiceEnvironment services() {
		return this.serviceEnvironment;
	}
}
