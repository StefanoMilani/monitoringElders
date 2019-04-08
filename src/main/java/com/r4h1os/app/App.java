package com.r4h1os.app;

import java.io.File;
import java.util.concurrent.TimeUnit;

// import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.edgent.connectors.iot.IotDevice;
import org.apache.edgent.connectors.iot.QoS;
import org.apache.edgent.connectors.iotp.IotpDevice;
import org.apache.edgent.providers.direct.DirectProvider;
import org.apache.edgent.providers.direct.DirectTopology;
import org.apache.edgent.topology.TStream;

import com.google.gson.JsonObject;

public class App {

	public static void main(String[] args) {
		DirectProvider dp = new DirectProvider();
		DirectTopology topology = dp.newTopology();
		
		String DeviceCfg = "/Users/milanimac/Developer/eclipse_workspace/Mini_Project_IoT/device.cfg";
		IotDevice device = new IotpDevice(topology, new File(DeviceCfg));
		// Set up sensor
		SmartWatchSensor sensor = new SmartWatchSensor();
		// Read each second from sensor
		TStream<Triple<String, Integer , Integer>> readings = device.topology().poll(sensor, 1, TimeUnit.SECONDS);
		// Create 2 stream (steps and heart rate) with significant value
		TStream<Triple<String, Integer , Integer>> StepsTriple = readings.filter(reading -> reading.getMiddle() > 0 );
		TStream<Triple<String, Integer , Integer>> HeartTriple = readings.filter(reading -> 
																					reading.getRight() != 0 &&
																					reading.getRight() != 255 &&
																					reading.getRight() != -1 );
		/***********************
		// Create better stream
		TStream<Pair<String , Integer>> HeartRateReadings = HeartTriple.map();
		TStream<Pair<String , Integer>> HeartRateReadings;
		************************/
		
		// Parse steps data in JSON
		TStream<JsonObject> stepsJSON = StepsTriple.map(reading ->{
			JsonObject j = new JsonObject();
			j.addProperty("name", "SmartWatchSensor");
			j.addProperty("Date", reading.getLeft());
			j.addProperty("Steps", reading.getMiddle());
			return j;
		});
		// Parse heart rate data in JSON
		TStream<JsonObject> heartRateJSON = HeartTriple.map(reading ->{
			JsonObject j = new JsonObject();
			j.addProperty("name", "SmartWatchSensor");
			j.addProperty("Date", reading.getLeft());
			j.addProperty("Heart rate", reading.getRight());
			return j;
		});
		
		stepsJSON.print();
		heartRateJSON.print();
		// Send the device streams as IoTF device events with event identifier "sensors".
		device.events(stepsJSON, "sensors", QoS.FIRE_AND_FORGET);
		device.events(heartRateJSON, "sensors", QoS.FIRE_AND_FORGET);
		dp.submit(topology);
		//sensor.closeConnection();
	}

}
