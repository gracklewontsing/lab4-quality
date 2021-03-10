package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mx.tec.lab.entity.Dragon;
import mx.tec.lab.repository.DragonRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DragonRepositoryTest {
	@Resource
	private DragonRepository dragonRepository;
	
	@Test
	public void givenDragon_whenSave_thenRetrieveSame() {
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		assertEquals("Meraxes", retrievedDragon.getName());
		
	}
  
	@Test
	public void givenDragon_whenSave_thenModify() {
		// Create dragon
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		// Retrieve dragon
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		
		// Modify retrieved dragon
		retrievedDragon.setName("Abraxas");
		dragonRepository.save(retrievedDragon);
		retrievedDragon = dragonRepository.findById(1L).orElse(null);
		assertEquals("Abraxas", retrievedDragon.getName());
				
	}
	
	@Test
	public void givenDragon_whenSave_thenDelete() {
		// Create dragon
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		// Delete dragon
		dragonRepository.deleteById(1L);
		
		// Attempt retrieval, then check if null
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		assertNull(retrievedDragon);
				
  }
  
}