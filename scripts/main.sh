#!/bin/sh

gunzip -c ../data/wac_0394_20121015_0041.log.gz | grep "http://scripts.adrcdn.com/000394/scripts/screenad_launch_9.4.0_scrambled.js" | ./compress_line.sh
