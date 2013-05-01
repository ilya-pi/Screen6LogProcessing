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
1. `pigz`

While first three (`bash`, `gzip` and `md5`) are default to Mac Os, you will require to install `gawk` (GNU Awk) and `pigz` 
(Parallel gzip implementation for unix/linux) in order to run it. Sadly `awk` won't do as there is no conventional way to 
ignore term separators inside double quotes in true Awk (the magic `FPATH` thingy).

Whether you use `macports` or `brew` installing should be fairly simple. For Homebrew (http://mxcl.github.io/homebrew/):
    
    brew install gawk
    brew install pigz

### Running

To run sample script that will take gziped logs and process them, producing output to your `STDOUT`:

    cd ./scripts
    ./main.sh ../data/

### Kawabanga!

Benchmarks
----------

### Configuration

 1. **Processor**: 2 Ghz Intel Core i7
 1. **Memmory**: 8 Gb 1333 Mgz DDR3
 1. **Disk**: SSD

### Measurement Method

All numbers are obtained with Linux/Unix `time` command. All given times are `real` (in a sense 
that `time` provides you with `real`, `user` and `sys` times).

In a normal experiment, I would've run each test approx 100 times and then show the `80%`, `90%` 
and `95%` percentile, but I didn't go that way here for simplicity's sake.

### Numbers

| File                               | `gawk` with `md5` | `gawk`, no `md5` | `Java`      | `Java` with `pigz` |
| :--------------------------------- |:-----------------:|:----------------:|:-----------:|:-------------------|
| wac_0394_20121015_0041.log.gz      | `161m58.591s`     | `5m34.306s`      | `0m45.784s` | `0m42.313s`        |
| wac_0394_20121015_0042.log.gz      | `97m15.428s`      | `3m44.732s`      | `0m55.721s` | `0m49.539s`        |
| wac_0394_20121015_0043.log.gz      | `187m40.101s`     | `6m7.504s`       | `0m33.741s` | `0m31.045s`        |
| wac_0394_20121015_0044.log.gz      | `65m31.095s`      | `2m53.047s`      | `0m56.740s` | `0m54.075s`        |
| wac_0394_20121015_0045.log.gz      | -                 | `7m43.135s`      | `0m51.726s` | `0m50.112s`        |
| wac_0394_20121015_0046.log.gz      | -                 | `3m41.005s`      | `0m43.871s` | `0m41.839s`        |
| wac_0394_20121015_0047.log.gz      | -                 | `3m42.607s`      | `0m24.724s` | `0m23.877s`        |

#### Final numbers

| File                               | `Java` with `pigz`, no  preliminary `grep` | Pure decompression > compression _*_ |
| :--------------------------------- |:------------------------------------------:|:----------------------------------:|
| wac_0394_20121015_0041.log.gz      | **`0m22.280s`**                            | `0m7.358s`                         |
| wac_0394_20121015_0042.log.gz      | **`0m16.901s`**                            | `0m7.594s`                         |
| wac_0394_20121015_0043.log.gz      | **`0m23.117s`**                            | `0m5.716s`                         |
| wac_0394_20121015_0044.log.gz      | **`0m12.503s`**                            | `0m8.258s`                         |
| wac_0394_20121015_0045.log.gz      | **`0m28.822s`**                            | `0m8.648s`                         |
| wac_0394_20121015_0046.log.gz      | **`0m15.496s`**                            | `0m7.984s`                         |
| wac_0394_20121015_0047.log.gz      | **`0m15.256s`**                            | `0m4.175s`                         |

_*_ Time to do `pigz -dc | pigz -c`, no actions inbetween

Notes on the code and choices
-----------------------------

Final solution consists of a bash script, that handles the framework around log processing and a small Java class to 
handle a single line of log.

Each time a log file is processed, that fact is stored in the `processed_files` file, that keeps current application state. Whenever 
you interrupt file proessing and then start over, for each file, it will check whether it was processed already, and whether 
it wasn't — pick it up.

When launched you will see application running with an output like:

    $ ./main.sh ../data/
    Processing ../data//wac_0394_20121015_0041.log.gz
    real	0m23.465s
    user	0m30.717s
    sys	0m15.337s

    Processing ../data//wac_0394_20121015_0042.log.gz
    real	0m16.675s
    user	0m23.879s
    sys	0m10.157s

    Processing ../data//wac_0394_20121015_0043.log.gz

Producing output files `wac_0394_20121015_004N.processed.gz`, with content being written in CSV format:

    gunzip -c wac_0394_20121015_0041.processed.gz
    ...
    1350300016,92.154.113.143,PC,450984629
    1350300016,95.254.226.216,PC,44881124
    1350300016,95.240.228.122,PC,277624491
    1350300016,78.123.63.173,PC,1146220856
    1350300016,95.225.112.111,PC,277624491
    1350300016,79.42.68.197,PC,450984629
    1350300016,87.2.114.186,MOBILE,213370475
    1350300016,88.39.201.20,PC,1536570949
    1350300016,195.110.157.186,PC,1831450184
    1350300016,194.79.184.70,PC,277624491
    1350300016,95.227.101.14,PC,414569225
    1350300016,213.26.146.175,PC,1861731761
    1350300016,109.217.98.41,TABLET,2046881875
    1350300016,87.24.32.4,PC,101196517
    1350300016,79.32.74.38,PC,-1067360440

Since we are reading from the disk, and writing back to it, and normally that is very expensive, I decided to read data from 
gzipped files and compress them on the fly right back to gzip, after processing was done.

You can see that user agent string is compressed via String.hashCode(), which implementation was specified ever since Java 1.3.1 
( [spec](http://docs.oracle.com/javase/6/docs/api/java/lang/String.html#hashCode()) ), so it is consistent among different 
implementations and version, meaning that the same user agent met for the second time, will result in exact same hashCode.

I used very simple algorithm to detect device type (based on "user agent string contains this/contains that"), but it can be 
adjusted might it won't be accurate enough.

`Gawk` implementation happend to be insanely slow (it took approx an hour plus something for each file), mostly for the reason 
of system call to `md5` to compute user agent string hashcode. But even without it, execution time was around `5minutes / a file`, which
is hardly a nice result for such a task.

### Parallel vs Sequential log processing

I was not able to fully utilize my CPU and memmory while performing experiments in a sequential manner, most of the time my cpu graph 
looked like —

![sequential execution](https://github.com/ilya-pi/Screen6LogProcessing/blob/main/Documentation/sequential.png?raw=true)

While altering `main.sh` a little: 

1. Adding `&` to a command for processing each log file
1. `wait` at the end of the script (to wait for all background processes completion)
1. And launching it all with `nice -n -19`, to give this process advantage compared to other running on the CPU

I got this picture (there aint a single nice shell utility to acquire historical CPU graph) —

![sequential execution](https://github.com/ilya-pi/Screen6LogProcessing/blob/main/Documentation/parallel.png?raw=true)

And overall time to process all seven log files was only **`0m58.741s`** vs `2m23.885s` for sequential log processing. But then `main.sh` would require 
some alterations in order to recover nicely after failovers/interruptions.

### Future

Depending on what the circumstances are, you might customize `nginx`'s `HttpLogModule` (http://wiki.nginx.org/HttpLogModule), to produce output very 
close to desired, or even write you own module to output precisely what you want, measuring statisticks in realtime, since `nginx` is opensource. But that only if those are your servers and you are using `nginx`.

Feedback
--------

Please send me an [email](ilya.pimenov@gmail.com) with any feedback you have.

Links
-----

 * [Problem as stated](https://github.com/ilya-pi/Screen6LogProcessing/blob/main/Problem.pdf)
 * [Screen6](http://screen6.io/)

