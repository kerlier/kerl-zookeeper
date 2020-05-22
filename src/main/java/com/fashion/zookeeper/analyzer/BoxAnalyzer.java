package com.fashion.zookeeper.analyzer;

import java.io.IOException;

import com.fashion.zookeeper.component.ZkComponent;

public class BoxAnalyzer extends ZkComponent {

	private String boxHost;

	private String boxPort;

	public BoxAnalyzer() throws IOException {
		super();
	}

	public String getBoxHost() {
		return boxHost;
	}

	public void setBoxHost(String boxHost) {
		this.boxHost = boxHost;
	}

	public String getBoxPort() {
		return boxPort;
	}

	public void setBoxPort(String boxPort) {
		this.boxPort = boxPort;
	}

	@Override
	public String toString() {
		return "boxHost: " + boxHost + ", boxPort:" + boxPort;
	}

}
