package com.db.org.trading.services;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.db.org.trading.Algo;
import com.db.org.trading.SignalHandler;
import com.db.org.trading.exception.ClassNodeNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Application implements SignalHandler {
	ObjectMapper objectMapper = new ObjectMapper();

	public Application() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleSignal(int signal) {
		System.out.println("Received Signal is ::: "+ signal);
		try {
			File jsonFile = new File(
					"/Users/sagnik/DB_Interview/trading-application/src/main/resources/tradings-config.json");
			JsonNode rootNode = objectMapper.readTree(jsonFile);

			// Get the class name and methods based on some key (e.g., "JIRA_signal")
			JsonNode classNode = rootNode.get("JIRA" + "_" + signal);
			// check if implementation is already present or not
			if (classNode == null) {
				throw new ClassNodeNotFoundException(signal);
			}

			String className = classNode.get("className").asText();
			JsonNode methodsNode = classNode.get("methods");

			// Dynamically load and instantiate the class
			Class<?> clazz = Class.forName(className);
			Object instance = clazz.getDeclaredConstructor().newInstance();

			// Iterate over the specified methods and invoke them
			for (JsonNode methodNode : methodsNode) {
				String methodName = methodNode.get("methodName").asText();
				JsonNode methodParametersNode = methodNode.get("methodParameters");
				Class<?>[] parameterTypes = new Class<?>[methodParametersNode.size()];
				Object[] methodArgs = new Object[methodParametersNode.size()];

				for (int i = 0; i < methodParametersNode.size(); i++) {
					parameterTypes[i] = int.class;
					methodArgs[i] = methodParametersNode.get(i).asInt();
				}
				Method method = clazz.getMethod(methodName, parameterTypes);
				method.invoke(instance, methodArgs);
				Algo algo = new Algo();
				algo.doAlgo();
			}

		} catch (ClassNodeNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (IOException | ReflectiveOperationException e) {
			e.printStackTrace();
		}

	}

}
