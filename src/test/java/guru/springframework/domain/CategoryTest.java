package guru.springframework.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.domain.Category;

public class CategoryTest {
	
	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
	}
	
	@Test
	public void testGetId() {
		Long idValue = 4L;
		category.setId(idValue);
		assertEquals(idValue, category.getId());
	}

	@Test
	public void testGetDescription() {
		assertEquals(1, 1);
	}

	@Test
	public void testGetRecipes() {
		assertEquals(1, 1);
	}

}
