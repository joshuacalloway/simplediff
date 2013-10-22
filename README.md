# simplediff

A simple program to diff two files.
I chose to use clojure because I didn't know clojure before, and thought this was a good opportunity to try it out.

## build, run, test

Need lein installed.
I developed this on ubuntu linux., but it should work on windows or mac OS.

* https://github.com/technomancy/leiningen
* Java 6 or 7 installed
* see USAGE for more details<br>
  ** lein run  
  ** lein test


## Usage

Script started, file is typescript<br>
joshua@josh-asus:~/simplediff$ lein test<br>
<br>

lein test simplediff.core-test<br>
<br>
Ran 5 tests containing 5 assertions.<br>
0 failures, 0 errors.<br>
joshua@josh-asus:~/simplediff$ lein run<br>
usage: simplediff <file1> <file2><br>
joshua@josh-asus:~/simplediff$ lein run "file1.txt" "file2.txt"<br>
(1 c 1 < This is first line > This is new first line)<br>
(4 a 5 > several)<br>
(4 a 6 > and even more several)<br>
(7 d 8 < )<br>
(8 a 10 > No actually this one is.)<br>
(8 a 11 > )<br>


## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
