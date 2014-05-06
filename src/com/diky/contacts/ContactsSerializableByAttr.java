package com.diky.contacts;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public class ContactsSerializableByAttr extends Contacts implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1311324063948454458L;

	public ContactsSerializableByAttr(Set<String> emails, Set<String> phones) {
		super(emails, phones);
	}

	public ContactsSerializableByAttr() {
		super();
	}

	private void readObject(ObjectInputStream in) throws IOException {
		emails.clear();
		phones.clear();
		int nEmails = in.readInt();
		for (int i = 0; i < nEmails; i++) {
			emails.add(in.readUTF());
		}
		int nPhones = in.readInt();
		for (int i = 0; i < nPhones; i++) {
			phones.add(in.readUTF());
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(emails.size());
		for (String e : emails) {
			out.writeUTF(e);
		}
		out.writeInt(phones.size());
		for (String p : phones) {
			out.writeUTF(p);
		}
	}


}
