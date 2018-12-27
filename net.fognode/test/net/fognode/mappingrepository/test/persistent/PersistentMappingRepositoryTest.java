package net.fognode.mappingrepository.test.persistent;

import net.fognode.mappingrepository.persistent.PersistentMappingRepository;
import net.fognode.mappingrepository.test.api.MappingRepositoryTest;

public class PersistentMappingRepositoryTest extends MappingRepositoryTest {

	@Override
	protected void instantiateCUT() {
		super.cut = new PersistentMappingRepository();
	}
}
