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
true `awk` (the magic `FPATH` thingy).

Whether you use `macports` or `brew` installing should be fairly simple. For Homebrew (http://mxcl.github.io/homebrew/):
    
    brew install gawk

### Running

To run sample script that will take gziped logs and process them, producing output to your `STDOUT`:

    cd ./scripts
    ./main.sh

### Kawabanga!

Feedback
--------

Please send me an [email](ilya.pimenov@gmail.com) with any feedback you have.

Links
-----

 * [Problem as stated](https://github.com/ilya-pi/Screen6LogProcessing/blob/main/Problem.pdf)
 * [Screen6](http://screen6.io/)

