package com.diky.contacts;

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
import org.junit.Test;

public class SerializationTest {

	Set<String> emails, phones;
	private ContactsExternalizableByObject byObj;
	private ContactsExternalizableByAttribute byAttr;
	private ContactsSerializable serializableContacts;
	private ContactsSerializableByAttr serializableContactsByAttr;

	@Before
	public void setUp() throws Exception {
		emails = new HashSet<>();
		phones = new HashSet<>();

		// fill the emails and phones set
		for (int i = 1; i < 10000; i++) {
			if (i % 2 == 0) {
				phones.add(String.valueOf(600000000 + i));
			} else {
				phones.add("+" + (100 / i) + String.valueOf(600000000 + i));
			}
		}

		for (int i = 0; i < 150; i++) {
			emails.add("email." + i + "@ext.com");
		}

		byAttr = new ContactsExternalizableByAttribute(emails, phones);
		byObj = new ContactsExternalizableByObject(emails, phones);
		serializableContacts = new ContactsSerializable(emails, phones);
		serializableContactsByAttr = new ContactsSerializableByAttr(emails, phones);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void testRWByObj() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		testContacts("testRWByObj.txt", byObj, new ContactsExternalizableByObject());

	}

	@Test
	public void testRWByAttr() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		testContacts("testRWByAttr.txt", byAttr, new ContactsExternalizableByAttribute());

	}
	
	@Test
	public void testRWSerializable() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		testContacts("testRWSerializable.txt", serializableContacts, new ContactsSerializable());

	}
	@Test
	public void testRWSerializableByAttr() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		testContacts("testRWSerializableByAttr.txt", serializableContactsByAttr, new ContactsSerializableByAttr());

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

		System.out.println("Serialization "
				+ contactsIn.getClass().getSimpleName() + ": "
				+ (endSerializationTime - startSerialization) + "ms");
		System.out.println("Deserialization "
				+ contactsIn.getClass().getSimpleName() + ": "
				+ (endDeserializationTime - startDeserialization) + "ms");

	}

}
