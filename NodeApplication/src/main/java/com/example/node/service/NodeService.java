package com.example.node.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.node.constant.NodeConstant;
import com.example.node.controller.NodeController;

@Service
public class NodeService {

	private static final Logger logger = LoggerFactory.getLogger(NodeController.class);
	private final Map<Integer, Set<Integer>> connections = new HashMap<>();

	public ResponseEntity<String> joinTwoNodes(int node1, int node2) {
		final String method = "joinTwoNodes";
		logger.info(NodeConstant.ENTERING_LOGGERS + method);
		if (node1 == node2) {
			return ResponseEntity.ok(NodeConstant.SAME_NODES);
		}

		if (!connections.containsKey(node1)) {
			connections.put(node1, new HashSet<>());
		}
		if (!connections.containsKey(node2)) {
			connections.put(node2, new HashSet<>());
		}

		if (connections.get(node1).contains(node2)) {
			return ResponseEntity.ok(NodeConstant.ALREDAY_EXIST);
		}

		connections.get(node1).add(node2);
		connections.get(node2).add(node1);
		logger.info(NodeConstant.ENTERING_LOGGERS + method);
		return ResponseEntity.ok(NodeConstant.SUCCESSFULL);
	}

	public boolean checkNodeConnection(int current, int target, Set<Integer> traversed) {
		final String method = "checkNodeConnection";
		logger.info(NodeConstant.ENTERING_LOGGERS + method);
		if (current == target) {
			logger.info(NodeConstant.MOVING_OUT_LOGGERS + method);
			return true;
		}
		traversed.add(current);
		for (int previous : connections.getOrDefault(current, Collections.emptySet())) {
			if (!traversed.contains(previous) && checkNodeConnection(previous, target, traversed)) {
				logger.info(NodeConstant.MOVING_OUT_LOGGERS + method);
				return true;
			}
		}
		logger.info(NodeConstant.MOVING_OUT_LOGGERS + method);
		return false;
	}

}
