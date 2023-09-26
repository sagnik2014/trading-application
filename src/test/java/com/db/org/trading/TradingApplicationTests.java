package com.db.org.trading;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.db.org.trading.services.Application;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest
//class TradingApplicationTests {
//
//	//@Test
//	void contextLoads() {
//	}
//
//}



import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TradingApplicationTests {

	 
	 @Mock
	 SignalHandler signalHandler;

	    @InjectMocks
	    private Application application;

    
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }
	    @Test
	    public void testHandleSignal() throws Exception {
	        int sampleSignal = 2;
	        // Mocking the behavior of objectMapper
	        doNothing().when(signalHandler).handleSignal(sampleSignal);
	        
	     // Call the method to be tested
	        signalHandler.handleSignal(sampleSignal);

	        // Verify that the handleSignal method of the signalHandler was called once with the sampleSignal
	        verify(signalHandler, times(1)).handleSignal(sampleSignal);

	    }
	    

}
