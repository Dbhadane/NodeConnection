package com.example.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.node.controller.NodeController;

public class ConnectionAlredayExist {
	
	 private NodeController nodeController;

	    @BeforeEach
	    public void setUp() {
	        nodeController = new NodeController();
	    }

    @Test
    public void testJoinNodes_AlreadyConnected() {
        nodeController.joinNodes(1, 2); // Join the nodes
        ResponseEntity<String> response = nodeController.joinNodes(1, 2); // Attempt to join again
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Connection already exists", response.getBody());
    }
}
