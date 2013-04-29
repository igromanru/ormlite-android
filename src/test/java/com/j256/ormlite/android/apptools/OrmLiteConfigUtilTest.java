package com.j256.ormlite.android.apptools;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Collection;

import org.junit.Test;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class OrmLiteConfigUtilTest {

	@Test
	public void testBasic() throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		OrmLiteConfigUtil.writeConfigFile(output, new Class[] { Foo.class });
		String result = output.toString();
		assertTrue(result, result.contains("\r\nfieldName=id\r\nid=true\r\n"));
	}

	@Test
	public void testCurrentDir() throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		OrmLiteConfigUtil.writeConfigFile(output, new File("src/test/java/com/j256/ormlite/android/apptools/"));
		String result = output.toString();
		assertTrue(result, result.contains("\r\nfieldName=id\r\nid=true\r\n"));
	}

	@Test
	public void testForeignCollection() throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		OrmLiteConfigUtil.writeConfigFile(output, new Class[] { ForeignCollectionTest.class });
		String result = output.toString();
		assertTrue(result, result.contains("\r\nfieldName=collection\r\nforeignCollection=true\r\n"));
	}

	@Test
	public void testForeign() throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		OrmLiteConfigUtil.writeConfigFile(output, new Class[] { Foo.class });
		String result = output.toString();
		assertTrue(result, result.contains("\r\nfieldName=foreign\r\nforeign=true\r\n"));
	}

	protected static class Foo {
		@DatabaseField(id = true)
		int id;
		@DatabaseField(foreign = true)
		Foreign foreign;
	}

	protected static class Foreign {
		@DatabaseField(id = true)
		int id;
	}

	protected static class ForeignCollectionTest {
		@ForeignCollectionField
		Collection<Foo> collection;
	}
}
