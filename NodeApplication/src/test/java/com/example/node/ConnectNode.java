package com.example.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.node.controller.NodeController;

public class ConnectNode {
	
    private NodeController nodeController;

    @BeforeEach
    public void setUp() {
        nodeController = new NodeController();
    }  
    
	@Test
    public void ConnectNodes() {
		ResponseEntity<String> response = nodeController.joinNodes(1, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nodes joined successfully", response.getBody());
     
    }
}
