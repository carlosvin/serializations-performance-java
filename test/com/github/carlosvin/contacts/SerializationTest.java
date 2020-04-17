package com.github.carlosvin.contacts;

import static org.junit.Assert.assertEquals;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SerializationTest {

	private static final int MAX_ELEMENTS = 100000;

	private static final Set<String> emails = new HashSet<>();
	private static final Set<String> phones = new HashSet<>();
	
	private static ContactsExternalizableByObject externalizableContactsByObjects;
	private static ContactsExternalizableByAttribute externalizableContactsByAttr;
	private static Contacts serializableContactsByObject;

	@BeforeClass
	public static void pre() {
		System.out.println("Testing with " + MAX_ELEMENTS + " elements\n");

		// fill the emails and phones set
		for (int i = 1; i < MAX_ELEMENTS; i++) {
			if (i % 2 == 0) {
				phones.add(String.valueOf(600000000 + i));
			} else {
				phones.add("+" + (100 / i) + String.valueOf(600000000 + i));
			}
		}

		for (int i = 0; i < 150; i++) {
			emails.add("email." + i + "@ext.com");
		}

		externalizableContactsByAttr = new ContactsExternalizableByAttribute(
				emails, phones);
		externalizableContactsByObjects = new ContactsExternalizableByObject(
				emails, phones);
		serializableContactsByObject = new Contacts(emails, phones);
	}

	@Before
	public void setUp() throws Exception { }

	@After
	public void tearDown() throws Exception {
		System.out.println("--------------");
	}

	@Test
	public void testRWByObj() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		testContacts("testRWByObj.txt", externalizableContactsByObjects,
				new ContactsExternalizableByObject());

	}

	@Test
	public void testRWByAttr() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		testContacts("testRWByAttr.txt", externalizableContactsByAttr,
				new ContactsExternalizableByAttribute());

	}

	@Test
	public void testRWSerializableByObject() throws FileNotFoundException,
			IOException, ClassNotFoundException {
		testContacts("testRWSerializableByObject.txt",
				serializableContactsByObject, null);

	}

	private void testContacts(String filepath, Contacts contactsIn,
			Contacts contactsOut) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		long startSerialization = System.currentTimeMillis();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				filepath));
		if (contactsIn instanceof Externalizable) {
			((Externalizable) contactsIn).writeExternal(out);

		} else {
			out.writeObject(contactsIn);
		}
		out.close();
		long endSerializationTime = System.currentTimeMillis();

		long startDeserialization = System.currentTimeMillis();
		ObjectInputStream inputStream = new ObjectInputStream(
				new FileInputStream(filepath));
		if (contactsIn instanceof Externalizable) {
			((Externalizable) contactsOut).readExternal(inputStream);
		} else {
			contactsOut = (Contacts) inputStream.readObject();
		}
		assertEquals(contactsIn, contactsOut);
		inputStream.close();
		long endDeserializationTime = System.currentTimeMillis();

		System.out.println("Serialization\t"
				+ contactsIn.getClass().getSimpleName() + ":\t"
				+ (endSerializationTime - startSerialization) + "ms");
		System.out.println("Deserialization\t"
				+ contactsIn.getClass().getSimpleName() + ":\t"
				+ (endDeserializationTime - startDeserialization) + "ms");

	}

}
