/**
 * 
 */
package rs.eventbroker.rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.data.impl.OsgiModelServiceImpl;
import rs.eventbroker.db.EBDaoFactory;
import rs.eventbroker.security.EbSecurityContext;

/**
 * Abstract Service implementation.
 * @author ralph
 *
 */
public abstract class AbstractService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Context
	EbSecurityContext securityContext;
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	@Context
	ContainerRequestContext requestContext;
	@Context 
	HttpHeaders headers;
	@Context
	Application application;
	
	/**
	 * Constructor.
	 */
	public AbstractService() {
	}

	/**
	 * Returns the logger object.
	 * @return the logger
	 */
	protected Logger getLog() {
		if (log == null) {
			log = LoggerFactory.getLogger(getClass());
		}
		return log;
	}

	/**
	 * Logs the error.
	 * @param message message to log
	 * @param t exception to log
	 */
	public void logError(String message, Throwable t) {
		t.printStackTrace();
		log.error(message, t);
	}

	/**
	 * Logs the error.
	 * @param message message to log
	 */
	public void logError(String message) {
		log.error(message);
	}

	/**
	 * Logs the info message.
	 * @param message message to log
	 */
	public void logInfo(String message) {
		log.info(message);
	}

	/**
	 * Returns the service factory.
	 * @return service factory.
	 */
	public EBDaoFactory getServiceFactory() {
		return OsgiModelServiceImpl.getModelService().getFactory(EBDaoFactory.class);
	}

	/**
	 * Begins a transaction.
	 * @see EBDaoFactory#begin()
	 */
	public void begin() {
		getServiceFactory().begin();
	}

	/**
	 * Begins a transaction.
	 * @param timeout - timeout of transaction
	 * @see EBDaoFactory#begin(long)
	 */
	public void begin(long timeout) {
		getServiceFactory().begin(timeout);
	}

	/**
	 * Commits a transaction.
	 * @see EBDaoFactory#commit()
	 */
	public void commit() {
		getServiceFactory().commit();
	}

	/**
	 * Rolls a transaction back.
	 * @see EBDaoFactory#rollback()
	 */
	public void rollback() {
		getServiceFactory().rollback();
	}

	/**
	 * Returns the {@link #securityContext}.
	 * @return the securityContext
	 */
	public EbSecurityContext getSecurityContext() {
		return (EbSecurityContext)securityContext;
	}

	/**
	 * Returns the {@link #uriInfo}.
	 * @return the uriInfo
	 */
	public UriInfo getUriInfo() {
		return uriInfo;
	}

	/**
	 * Returns the {@link #request}.
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * Returns the {@link #requestContext}.
	 * @return the requestContext
	 */
	public ContainerRequestContext getRequestContext() {
		return requestContext;
	}

	/**
	 * Returns the {@link #headers}.
	 * @return the headers
	 */
	public HttpHeaders getHeaders() {
		return headers;
	}

	/**
	 * Returns the {@link #application}.
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}
	
}
