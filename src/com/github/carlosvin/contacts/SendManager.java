package com.github.carlosvin.contacts;

import java.io.Externalizable;

public interface SendManager {

	public void send(String deviceName, Externalizable externalizable);
}
