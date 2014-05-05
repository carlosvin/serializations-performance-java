package com.diky.contacts;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

/**
 * Created by carlos on 22/09/13.
 */
public class ContactsToSerializeByObject extends Contacts {

	public ContactsToSerializeByObject(Set<String> emails, Set<String> phones) {
		super(emails,phones);
	}

	public ContactsToSerializeByObject() {
		super();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setEmails((Set<String>) in.readObject());
		setPhones((Set<String>) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(emails);
		out.writeObject(phones);
	}

	

}
