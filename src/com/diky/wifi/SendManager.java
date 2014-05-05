package com.diky.wifi;

import java.io.Externalizable;

public interface SendManager {

	public void send(String deviceName, Externalizable externalizable);
}
