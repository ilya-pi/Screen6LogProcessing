#!/bin/sh

filter_pattern="http://scripts.adrcdn.com/000394/scripts/screenad_launch_9.4.0_scrambled.js"
processed=processed_files
usage="$(basename "$0") <directory> -- Screen6 log converting application

where:
    <directory> to scan for compressed access log files"

usage(){
	echo "$usage"
}

if ( [[ $# -eq 0 ]] && usage )
	then
	exit 1
fi

if [ ! -e "$processed" ] ; 
	then
	touch "$processed"
fi

for i in $1/* 
do
	if test -f "$i" 
		then
		if grep -Fxq "$i" $processed 
			then
			echo "Already processed $i"
		else
			echo "Processing $i"
			filename=$(basename "$i")
			processed_filename="${filename%.*.*}".processed.gz
			time pigz -dc $i | java Screen6LogProcessor | pigz -c > $processed_filename
			echo $i >> $processed
		fi
	fi
done
