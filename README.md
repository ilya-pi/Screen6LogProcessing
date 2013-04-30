Screen6LogProcessing
====================

Access logs compressing utility

Disclaimer
----------

Following github's `robots.txt` (https://github.com/robots.txt) this repo is not indexed by google, 
as the `master` branch was deleted manually, everything here is under `main` branch.

Tech Soup
---------

### Prerequisites

In order to run this script you require:

1. `bash`
1. `gzip`
1. `md5`
1. `gawk`

While first three (`bash`, `gzip` and `md5`) are default to Mac Os, you will require to install `gawk` (GNU Awk)
in order to run it. Sadly `awk` won't do as there is no conventional way to ignore term separators inside double quotes 
in true Awk (the magic `FPATH` thingy).

Whether you use `macports` or `brew` installing should be fairly simple. For Homebrew (http://mxcl.github.io/homebrew/):
    
    brew install gawk

### Running

To run sample script that will take gziped logs and process them, producing output to your `STDOUT`:

    cd ./scripts
    ./main.sh

### Kawabanga!

Benchmarks
----------

### Configuration

 *Processor*: 2 Ghz Intel Core i7
 *Memmory*: 8 Gb 1333 Mgz DDR3

### Measurement Method

All numbers are obtained with Linux/Unix `time` command

### Numbers

Processing ../data/wac_0394_20121015_0041.log.gz
1. `real	161m58.591s`
1. `user	91m10.060s`
1. `sys	67m59.805s`

Processing ../data/wac_0394_20121015_0042.log.gz
1. `real	97m15.428s`
1. `user	54m57.806s`
1. `sys	40m49.837s`

Processing ../data/wac_0394_20121015_0043.log.gz
1. `real	187m40.101s`
1. `user	105m20.379s`
1. `sys	79m2.680s`

Processing ../data/wac_0394_20121015_0044.log.gz
1. `real	65m31.095s`
1. `user	37m13.850s`
1. `sys	27m31.892s`

Feedback
--------

Please send me an [email](ilya.pimenov@gmail.com) with any feedback you have.

Links
-----

 * [Problem as stated](https://github.com/ilya-pi/Screen6LogProcessing/blob/main/Problem.pdf)
 * [Screen6](http://screen6.io/)

