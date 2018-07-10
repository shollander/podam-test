import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

class PodamTest {
    
	@Test
	void testDepth() {
	    // setup
		PodamFactory podamFactory = new PodamFactoryImpl();
		podamFactory.setStrategy(new CustomDataProviderStrategy());
		// test
		MyObject randomData = podamFactory.manufacturePojo(MyObject.class);
		// verify
		assertNotNull(randomData);
		assertNotNull(randomData.getName());
		assertDepthIsOne(randomData.getRelated());
		randomData.getChildren().stream().forEach(c -> assertDepthIsOne(c));
	}
	
	private void assertDepthIsOne(MyObject myObject) {
	    assertNotNull(myObject);
	    assertNotNull(myObject.getName());
	    assertNull(myObject.getRelated());
	    assertEmptyList(myObject.getChildren());
	}
	
	private void assertEmptyList(List<?> list) {
	    if (list.isEmpty()) {
	        return;
	    } else {
	        assertTrue(list.stream().allMatch(v -> v == null), "Found non-null item in list:" + list);
	    }
	}
	
	static class CustomDataProviderStrategy extends AbstractRandomDataProviderStrategy {
	    CustomDataProviderStrategy() {
	        super.setMemoization(false);
	    }
	    
	    @Override
	    public int getMaxDepth(Class<?> type) {
	       if (MyObject.class.isAssignableFrom(type)) {
	           return 2;
	       } else {
	           return 1;
	       }
	    }
	}
}
