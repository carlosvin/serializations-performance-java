package com.diky.contacts;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public class ContactsSerializableByObject extends Contacts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145160399659807105L;

	public ContactsSerializableByObject(Set<String> emails, Set<String> phones) {
		super(emails, phones);
	}

	public ContactsSerializableByObject() {
		super();
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeObject(emails);
		out.writeObject(phones);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		emails.addAll((Collection<? extends String>) in.readObject());
		phones.addAll((Collection<? extends String>) in.readObject());
	}

}
