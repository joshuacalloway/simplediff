# simplediff

A simple program to diff two files.
I chose to use clojure because I didn't know clojure before, and thought this was a good opportunity to try it out.

## Usage


]0;joshua@josh-asus: ~/simplediffjoshua@josh-asus:~/simplediff$ script
Script started, file is typescript
]0;joshua@josh-asus: ~/simplediffjoshua@josh-asus:~/simplediff$ lein test

lein test simplediff.core-test

Ran 5 tests containing 5 assertions.
0 failures, 0 errors.
]0;joshua@josh-asus: ~/simplediffjoshua@josh-asus:~/simplediff$ lein run
usage: simplediff <file1> <file2>
]0;joshua@josh-asus: ~/simplediffjoshua@josh-asus:~/simplediff$ lein run "file1.txt" "file2.txt"
(1 c 1 < This is first line > This is new first line)
(4 a 5 > several)
(4 a 6 > and even more several)
(7 d 8 < )
(8 a 10 > No actually this one is.)
(8 a 11 > )


## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
