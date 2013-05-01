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

| File                               | `Java` with `pigz`, no  preliminary `grep` | Pure decompression > compression * |
| :--------------------------------- |:------------------------------------------:|:----------------------------------:|
| wac_0394_20121015_0041.log.gz      | `0m22.280s`                                | `0m7.358s`                         |
| wac_0394_20121015_0042.log.gz      | `0m16.901s`                                | `0m7.594s`                         |
| wac_0394_20121015_0043.log.gz      | `0m25.571s`                                | `0m5.716s`                         |
| wac_0394_20121015_0044.log.gz      | `0m14.210s`                                | `0m8.258s`                         |
| wac_0394_20121015_0045.log.gz      | `0m31.299s`                                | `0m8.648s`                         |
| wac_0394_20121015_0046.log.gz      | `0m16.603s`                                | `0m7.984s`                         |
| wac_0394_20121015_0047.log.gz      | `0m15.256s`                                | `0m4.175s`                         |

* _Time to do `gunzip | gzip`, not actions inbetween_

Feedback
--------

Please send me an [email](ilya.pimenov@gmail.com) with any feedback you have.

Links
-----

 * [Problem as stated](https://github.com/ilya-pi/Screen6LogProcessing/blob/main/Problem.pdf)
 * [Screen6](http://screen6.io/)

