package net.fognode.shadowrepository.api;

import net.fognode.shadow.api.Shadow;

public interface ShadowRepository {
	/**
	 * Returns a Shadow (@see net.fognode.shadow.api.Shadow) for the provided
	 * outgoing URL and injects a Client whose "protocol" service property
	 * matches the requested Shadow's protocol attribute (e.g. "HTTP").
	 * Each resource of the user-defined API should be proxied by a Shadow.
	 * This method shall either return an already-existing Shadow, or create a
	 * new one and keep it for further requests.
	 * @param url The outgoing URL used as a primary key for Shadows.
	 * @return a Shadow representing the resource identified by the outgoing URL
	 * @throws ClassNotFoundException if no matching Client for the requested
	 * Shadow's protocol (e.g. HTTP) is currently installed and active. 
	 */
	public Shadow getShadow(String url) throws ClassNotFoundException;
}
