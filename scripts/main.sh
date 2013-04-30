#!/bin/sh

filter_pattern="http://scripts.adrcdn.com/000394/scripts/screenad_launch_9.4.0_scrambled.js"
processed=processed_files

if [ ! -e "$processed" ] ; then
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
           time gunzip -c $i | grep $filter_pattern | java Screen6LogProcessor | gzip -c > $processed_filename
           echo $i >> $processed
       fi
    fi
done

