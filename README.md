## Contributors

Kristijan Korunoski (22966841)

## Description

There are 4 function implementations to provide analysis over a network of devices.<br/>

1. allDevicesConnected: Determine if all of the devices in the network are connected to the network. Devices are considered to be connected to the network if they can transmit (including via other devices) to every other device in the network. If all devices in the network are connected, then return true , and return false otherwise.

2. numPaths: Determine the number of different paths a packet can take in the network to get from a transmitting device to a receiving device. A device will only transmit a packet to a device that is closer to the destination, where the distance to the destination is the minimum number of hops between a device and the destination.

3. closestInSubnet: Compute the minimum number of hops required to reach a device in each subnet query. Each device has an associated IP address. An IP address is here represented as an array of exactly four integers between 0 and 255 inclusive (for example, {192, 168, 1, 1} ). Each query specifies a subnet address. A subnet address is specified as an array of up to four integers between 0 and 255. An IP address is considered to be in a subnet if the subnet address is a prefix of the IP address (for example, {192, 168, 1, 1} is in subnet {192, 168} but not in {192, 168, 2} ). For each query, compute the minimum number of hops required to reach some device in the specified subnet. If no device in that subnet is reachable, return Integer.MAX_VALUE .

4. maxDownloadSpeed: 
Compute the maximum possible download speed from a transmitting device to a receiving device. The download may travel through more than one path simultaneously, and you can assume that there is no other traffic in the network. If the transmitting and receiving devices are the same, then you should return -1 .<br/>
Each link in the network has a related speed at the same index in the speeds array (e.g. the link described at adjlist[0][1] has its related speed at speeds[0][1] ). Speeds may be asymmetric (that is - the speed in one direction of a link may be different to the speed in the other direction of a link).

#### UWA CITS2200 Project
