// Kristijan Korunoski (22966841)

import java.util.Queue;
import java.util.LinkedList;

public class MyProject implements Project {
	
	public MyProject() { 
		
	}
	
	/**
	 * Determine if all of the devices in the network are connected to the network.
	 * @param adjlist The adjacency list describing the links between devices
     * @return Whether all the devices are connected or not
	 */
    public boolean allDevicesConnected(int[][] adjlist) {
        // TODO
    	Queue<Integer> q = new LinkedList<Integer>();
    	int size = adjlist.length;
    	boolean[] visited = new boolean[size];
    	int visitedCount = 1;
    	boolean allVisited = false;
    	
    	q.add(0);
    	visited[0] = true;
    	while (!q.isEmpty()) {
    		int d = q.remove();
    		for (int j = 0; j < adjlist[d].length; j++) {
    			if (!visited[adjlist[d][j]]) {
    				q.add(adjlist[d][j]);
    				visitedCount++;
    				visited[adjlist[d][j]] = true;
   				}
   			}
    	}
    	
    	if (visitedCount == size) {
    		allVisited = true;
    	}
		
		return allVisited;
    }

    /**
     * Determine the number of different paths a packet can take in the network to get 
     * from a transmitting device to a receiving device.
     * @param adjlist The adjacency list describing the links between devices
     * @param src The source (transmitting) device
     * @param dst The destination (receiving) device
     * @return The total number of possible paths between the two devices
     */
    public int numPaths(int[][] adjlist, int src, int dst) {
        // TODO
    	if (src == dst) {
    		return 1;
    	} else {
	    	Queue<Integer> q = new LinkedList<Integer>();
	    	int paths = 0;
	    	int size = adjlist.length;
	    	boolean[] visited = new boolean[size];
	    	
		    q.add(src);
		    visited[src] = true;
		    while (!q.isEmpty()) {
		    	int d = q.remove();
		    	for (int j = 0; j < adjlist[d].length; j++) {
		    		if (adjlist[d][j] == dst) {
		   				paths ++;
		   			} else if (!visited[adjlist[d][j]]) {
		   				q.add(adjlist[d][j]);
		   				visited[adjlist[d][j]] = true;
		   			}
	    		}
		    }
		    
	        return paths;
    	}
    }

    /**
     * Compute the minimum number of hops required to reach a device in each subnet query.
     * @param adjlist The adjacency list describing the links between devices
     * @param addrs The list of IP addresses for each device
     * @param src The source device
     * @param queries The queries to respond to for each subnet
     * @return An array with the distance to the closest device for each query, or Integer.MAX_VALUE if none are reachable
     */
    public int[] closestInSubnet(int[][] adjlist, short[][] addrs, int src, short[][] queries) {
        // TODO
    	int numQ = queries.length;
    	int numD = adjlist.length;
    	Queue<Integer> Q = new LinkedList<Integer>();
		int[] hops = new int[numQ];
		int[] numHops = new int[numD];
		boolean[] visited = new boolean[numD];
		for (int j = 0; j < numQ; j++) {
			hops[j] = Integer.MAX_VALUE;
		}
		
		for (int q = 0; q < numQ; q++) {
			while (!Q.isEmpty()) {
				Q.remove();
			}
			
			for (int i = 0; i < numD; i++) {
				visited[i] = false;
				numHops[i] = 0;
			}
			
			Q.add(src);
			visited[src] = true;
			while (!Q.isEmpty()) {
				int v = Q.remove();
				if (isSubNet(addrs[v], queries[q])) {
					hops[q] = numHops[v];
					break;
				} else {
					for (int i = 0; i < adjlist[v].length; i++) {
						if (!visited[adjlist[v][i]]){
								numHops[adjlist[v][i]] = numHops[v]+1;
		                        Q.add(adjlist[v][i]);
		                        visited[adjlist[v][i]] = true;			
						}
					}
					
				}
			}
		}
		
		return hops;
    }
    
    /**
     * Return true if the IP is in the subnet query
     * @param ip The IP address of a device
     * @param query The query that is to be checked against the IP
     * @return True if the IP is in the subnet query, false otherwise.
     */
    private boolean isSubNet(short[] ip, short[] query) {
    	boolean isSub = true;
    	for (int i = 0; i < query.length; i++) {
    		if (ip[i] != query[i]) {
    			isSub = false;
    			break;
    		}
    	}
		return isSub;
    }
    
    /**
     * Compute the maximum possible download speed from a transmitting device to a receiving device.
     * @param adjlist The adjacency list describing the connections between devices
     * @param speeds The list of query row segments
     * @param src The source (transmitting) device
     * @param dst The destination (receiving) device
     * @return The maximum download speed possible from the source to the destination, or -1 if they are the same
     */
    public int maxDownloadSpeed(int[][] adjlist, int[][] speeds, int src, int dst) {
        // TODO
    	if (src == dst) {
    		return -1;
    	} else {
    		//int[][] speeds2 = speeds;
    		int maxSpeed = 0;
    		int numD = adjlist.length;
    		Queue<Integer> Q = new LinkedList<Integer>();
    		int[] key = new int[numD];
    		boolean[] visited = new boolean[numD];
    		
    		Q.add(src);
    		visited[src] = true;
    		while (!Q.isEmpty()) {
    			int v = Q.remove();
    			if (noOutput(speeds[v])) {
    				visited[v] = true;
    			}
    			if (key[v] == - 1) {
    				
    			} else {
	    			for (int i = 0; i < adjlist[v].length; i++) {
	    				if (!visited[adjlist[v][i]] && speeds[v][i] != 0){
    						if (speeds[v][i] < key[v]) {
	    						if (adjlist[v][i] == dst) {
	    							maxSpeed = maxSpeed + speeds[v][i];
	    						} else {
		    						key[adjlist[v][i]] = key[adjlist[v][i]] + speeds[v][i];
		                            Q.add(adjlist[v][i]);
	    						}
	    						key[v] = key[v] - speeds[v][i];
	    						if (key[v] == 0) {key[v] = -1;}
	    						speeds[v][i] = 0;
	    					} else if (key[v] == 0){
	    						if (adjlist[v][i] == dst) {
	    							maxSpeed = maxSpeed + speeds[v][i];
	    						} else {
		    						key[adjlist[v][i]] = speeds[v][i];
		                            Q.add(adjlist[v][i]);
	    						}
	    						speeds[v][i] = 0;
	    					} else {
	    						if (adjlist[v][i] == dst) {
	    							maxSpeed = maxSpeed + key[v];
	    						} else {
		    						key[adjlist[v][i]] = key[v];
		                            Q.add(adjlist[v][i]);
	    						}
	    						speeds[v][i] = speeds[v][i] - key[v];
	    						key[v] = -1;
	    					}
    					}
    				}
    			}
    		}
		return maxSpeed;
    	}
    }
    
    /**
     * Check if there is no possible output from a device 
     * i.e. transmit speed to all other devices = 0
     * @param speeds An array of speeds at which a download can be made between devices
     * @return True only if there is no possible output (A transmit speed < 0) and false otherwise
     */
    private boolean noOutput(int[] speeds) {
    	boolean hasOutput = false;
    	for (int i = 0; i < speeds.length; i++) {
    		if (speeds[i] != 0) {
    			hasOutput = true;
    			break;
    		}
    	}
    	return hasOutput;
    }
    
}
