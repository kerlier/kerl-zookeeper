package com.fashion.zookeeper.analyzer;

import java.io.IOException;

import com.fashion.zookeeper.annotation.Path;
import com.fashion.zookeeper.component.ZkComponent;

public class LocationAnalyzer extends ZkComponent {

	
	@Path(value = "/locationHost")
	private String locationHost;
	
	@Path(value = "/locationPort")
	private String locationPort;

	public LocationAnalyzer() throws IOException {
		super();
	}
	
	public LocationAnalyzer(String zkHosts) throws IOException {
		super(zkHosts);
	}

	@Override
	public String toString() {
		return "locationHost: " + locationHost + ", locationPort:" + locationPort;
	}

	public String getLocationHost() {
		return locationHost;
	}

	public void setLocationHost(String locationHost) {
		this.locationHost = locationHost;
	}

	public String getLocationPort() {
		return locationPort;
	}

	public void setLocationPort(String locationPort) {
		this.locationPort = locationPort;
	}

}
