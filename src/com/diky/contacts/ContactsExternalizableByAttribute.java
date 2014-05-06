package com.diky.contacts;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

/**
 * Created by carlos on 22/09/13.
 */
public class ContactsExternalizableByAttribute extends Contacts implements Externalizable{


	public ContactsExternalizableByAttribute(Set<String> emails, Set<String> phones) {
		super(emails,phones);
	}
	
	public ContactsExternalizableByAttribute() {
		super();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
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
