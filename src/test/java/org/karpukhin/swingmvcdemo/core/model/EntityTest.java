package org.karpukhin.swingmvcdemo.core.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Pavel Karpukhin
 */
public class EntityTest {

	private static final int SOME_ID = 43212;
	private static final int SOME_OTHER_ID = 4323;

	@Test
	public void testReflexivity() {
		SomeEntity first = new SomeEntity(SOME_ID);

		assertTrue(first.equals(first));
	}

	@Test
	public void testSymmetry() {
		SomeEntity first = new SomeEntity(SOME_ID);
		SomeEntity second = new SomeEntity(SOME_ID);

		assertTrue(first.equals(second));
		assertTrue(second.equals(first));
	}

	@Test
	public void testTransitivity() {
		SomeEntity first = new SomeEntity(SOME_ID);
		SomeEntity second = new SomeEntity(SOME_ID);
		SomeEntity third = new SomeEntity(SOME_ID);

		assertTrue(first.equals(second));
		assertTrue(second.equals(third));
		assertTrue(first.equals(third));
	}

	@Test
	public void testEqualsWhenOtherIsNull() {
		SomeEntity first = new SomeEntity(SOME_ID);

		assertFalse(first.equals(null));
	}

	@Test
	public void testEqualsWhenDifferentId() {
		SomeEntity first = new SomeEntity(SOME_ID);
		SomeEntity second = new SomeEntity(SOME_OTHER_ID);

		assertFalse(first.equals(second));
		assertFalse(second.equals(first));
	}

	@Test
	public void testEqualsWhenDifferentClasses() {
		SomeEntity first = new SomeEntity(SOME_ID);
		Entity second = new Entity() {};
		second.setId(SOME_OTHER_ID);

		assertFalse(first.equals(second));
		assertFalse(second.equals(first));
	}

	@Test
	public void testHashCode() {
		SomeEntity first = new SomeEntity(SOME_ID);
		SomeEntity second = new SomeEntity(SOME_ID);

		assertEquals(first.hashCode(), second.hashCode());
	}

	private static class SomeEntity extends Entity {

		public SomeEntity(int id) {
			setId(id);
		}
	}
}
