package com.db.org.trading.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.org.trading.SignalHandler;
import com.db.org.trading.services.Application;

@RestController
@RequestMapping("/trading-app")
public class TradingSignalController {

	SignalHandler signalHandler = new Application();

	@GetMapping("/testing")
	public void test() {
		System.out.println("HIIII");
	}

	@PostMapping("/receive-signal")
	public ResponseEntity<String> receiveSignal(@RequestBody int signal) {
		try {
			signalHandler.handleSignal(signal);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ResponseEntity.ok("Signal received and handled successfully ::: " + signal);
	}
}
