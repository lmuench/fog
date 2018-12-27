package net.fognode.shadow.test.stateless;

import net.fognode.shadow.stateless.StatelessShadow;
import net.fognode.shadow.test.api.ShadowTest;

public class StatelessShadowTest extends ShadowTest {

	@Override
	protected void instantiateCUT(String protocol) {
		super.cut = new StatelessShadow(protocol);
	}
}
