package com.example.node.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.node.constant.NodeConstant;
import com.example.node.service.NodeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("node")

public class NodeController {

	private static final Logger logger = LoggerFactory.getLogger(NodeController.class);

	@Autowired
	NodeService nodeService;

	@PostMapping("/join")
	public ResponseEntity<String> joinNodes(@RequestParam int node1, @RequestParam int node2) {
		logger.info(NodeConstant.ENTERING_LOGGERS + "joinNodes");
		ResponseEntity<String> response = nodeService.joinTwoNodes(node1, node2);
		logger.info(NodeConstant.MOVING_OUT_LOGGERS + "joinNodes");
		return response;
	}

	@GetMapping("/isConnected")
	public ResponseEntity<Boolean> nodeConnected(@RequestParam int node1, @RequestParam int node2) {
		final String method = "nodeConnected";
		logger.info(NodeConstant.ENTERING_LOGGERS + method);
		boolean response = nodeService.checkNodeConnection(node1, node2, new HashSet<>());
		logger.info(NodeConstant.MOVING_OUT_LOGGERS + method);
		return ResponseEntity.ok(response);
	}

}
