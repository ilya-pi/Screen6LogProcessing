#!/bin/sh
gawk '
{	
if (match($15, "Mobi")){
	deviceType = "Mobile"
}else if (match($15, "Tablet")){
	deviceType = "Tablet"
}else if (match($15, "TV")){
	deviceType = "TV"
}else{
	deviceType = "PC"
}

print $1, "\t", $3, "\t", deviceType, "\t", $15
}' FPAT='([^ ]+)|("[^"]+")'

