package com.example.node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.node.controller.NodeController;

public class ConnectionExist {
	
	private NodeController nodeController;

    @BeforeEach
    public void setUp() {
        nodeController = new NodeController();
    }
    
	   @Test
	    public void testIsConnected_ConnectedNodes() {
	        nodeController.joinNodes(1, 2); // Join the nodes
	        ResponseEntity<Boolean> response = nodeController.nodeConnected(1, 2);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertTrue(response.getBody());
	    }
	   
	    @Test
	    public void testIsConnected_NotConnectedNodes() {
	        ResponseEntity<Boolean> response = nodeController.nodeConnected(1, 2);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertFalse(response.getBody());
	    }
}
