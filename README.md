# ProcessCSVFileData

## Input
two CSV (Comma Separated Values) files.
A file named “bandwidth.csv”, containing the Bandwidth information for a set of Network Interfaces. A Network Interface is identified by the couple [server, network interface name]
A file named “netbitrate.csv”, containing the Network Bit Rate registered for the same set of Network Interfaces over a period. A Network Interface is identified by the couple [server, network interface name]


## Expected Output
You are asked to develop a small procedure that, given the two CSV files, calculates the Network Bandwidth Utilization for every line in the “netbitrate.csv” file. The Network Bandwidth Utilization of an interface is calculated as the Network Bit Rate divided by its Bandwidth. The procedure needs to print the results on the standard output according to the following format:

Timestamp | Server | Network Interface | Network bit rate / Bandwidth
