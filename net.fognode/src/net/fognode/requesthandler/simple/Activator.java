/*******************************************************************************
 * Copyright (C) 2018 Ludwig Muench
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package net.fognode.requesthandler.simple;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.mappingrepository.api.MappingRepository;
import net.fognode.middleware.api.Middleware;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.shadowrepository.api.ShadowRepository;

/**
 * Registers SimpleRequestHandler as a RequestHandler
 * (@see net.fognode.requesthandler.api.RequestHandler) OSGi service with
 * dependencies to a MappingRepository service, a ShadowFactory service, as
 * well as all active Middleware services. To keep track of all active
 * middleware, the SimpleRequestHandler observes the service registry
 * withhelp of its "added()" and "removed()" methods registered as callbacks
 * below.
 * 
 * @author Ludwig Muench
 */
public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(RequestHandler.class.getName(), null)
			.setImplementation(SimpleRequestHandler.class)
			.add(
				createServiceDependency()
				.setService(MappingRepository.class)
				.setRequired(true)
			).add(
				createServiceDependency()
				.setService(Middleware.class)
				.setRequired(false)
				.setCallbacks("added", "removed")
			).add(
				createServiceDependency()
				.setService(ShadowRepository.class)
				.setRequired(true)
			)
		);
	}
}
