package net.fognode.shadowrepository.api;

import net.fognode.shadow.api.Shadow;

public interface ShadowRepository {
	public Shadow getShadow(String url) throws IllegalArgumentException;
}
