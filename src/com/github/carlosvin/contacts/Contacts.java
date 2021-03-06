package com.github.carlosvin.contacts;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Contacts implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3002402809420605254L;
	
	protected Set<String> emails;
	protected Set<String> phones;

	public Contacts(Set<String> emails, Set<String> phones) {
		this.emails = emails;
		this.phones = phones;
	}

	public Contacts() {
		this.emails = new HashSet<String>();
		this.phones = new HashSet<String>();
	}

	public void setEmails(Set<String> emails) {
		this.emails.clear();
		this.emails.addAll(emails);
	}

	public void setPhones(Set<String> phones) {
		this.phones.clear();
		this.phones.addAll(phones);
	}

	public Set<String> getEmails() {
		return Collections.unmodifiableSet(emails);
	}

	public Set<String> getPhones() {
		return Collections.unmodifiableSet(phones);
	}

	@Override
	public String toString() {
		int nEmails = emails == null ? 0 : emails.size();
		int nPhones = phones == null ? 0 : phones.size();

		return getClass().getSimpleName() + "{" + "emails=" + nEmails + ", phones=" + nPhones + '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emails == null) ? 0 : emails.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Contacts)) {
			return false;
		}
		Contacts other = (Contacts) obj;
		if (emails == null) {
			if (other.emails != null) {
				return false;
			}
		} else if (!emails.equals(other.emails)) {
			return false;
		}
		if (phones == null) {
			if (other.phones != null) {
				return false;
			}
		} else if (!phones.equals(other.phones)) {
			return false;
		}
		return true;
	}

}
