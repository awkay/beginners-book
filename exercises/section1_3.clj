(ns section1_3)

;; INSTRUCTIONS:
;; . IF you've been told there were updates to the book, do the following:
;;    * Issue a "Fetch" Git command (e.g. via IntelliJ actions)
;;    * Issue a "Merge" Git command, and pick the `origin/main` branch. This will obtain the changes.
;;    * NOTE: IF there are conflicts it means you edited the same line that changed on main. You should use the
;;      "Resolve Conflicts" action to fix them (largely self-explanatory, but ask if you hit this problem and need help.)
;; . Start a REPL and work in this actual file.
;; . Write your text-based answers to questions as new comments (starting with ;;) underneath the exercise or question
;; . Write your code-based answers as real, runnable Clojure code underneath the exercise in question
;; . When

;; Integers
;; Typing in a simple sequence of digits gives an integer (64-bit). The most common manipulations are simple math.
;; Evaluate the following in a REPL:

42
(+ 1 2 3 4 5)
(* 2 2 2)
(/ 4 2)
(/ 13 2)
;; You can ask for the "type" of *anything* in Clojure using the
;; `type` function. Try this:
(type 5)

;; The type java.lang.Long is the underlying data type name in
;; the Java system (on top of which Clojure runs) for a 64
;; bit integer.

;; EXERCISE: Write the Clojure to get the equivalent result
;; for the arithmetic:  ((11 + 9) * 6) / 2

;; EXERCISE: What is the data type (e.g. use the type function) of
;; the ANSWER to the prior question?

;; EXERCISE: Do the prior question, but divide by 17 instead of 2. Is
;; it different? Why do you think?

;; A useful operation in integer division turns out to be the modulo operation. This returns the "remainder" from
;; the division, and is useful for converting a source number into a fixed range. Try the following sequence
;; of things in a REPL:
(mod 4 2)
(mod 5 2)
(mod 6 2)
(mod 7 2)
(mod 8 2)

;; EXERCISE: Show the same kind of sequence you would use to get
;; a number between 0 and 4.

;; Decimals
;; Clojure uses 64 bit floats (double) by default

;; EXERCISE: Ask Clojure to add together 0.1 3 times. What is the
;; answer? Why do you suppose it is that?

;; Sometimes you have a decimal, but you only want the integer portion. The `int` function coerces a value
;; to an integer if it can.

;; EXERCISE: Guess what this will return before running it:
(int (/ 4.6 2.0))

;; Big Decimals.

;; Earlier you saw an example of where floating point math is slightly
;; inaccurate.
;; Clojure actually supports "arbitrary precision math" on decimal
;; numbers. (known as BigDecimals). You can simply put an M suffix on a number
;; to use them. For example, try this:
(+ 0.1M 0.1M 0.1M)

;; Try these:
(* 6.3 9.238475)
(* 6.3M 9.238475M)

;; EXERCISE: There are two drawbacks to the more accurate math. Can you guess what they are?

;; EXERCISE: Run the following two things. They are both loops
;; that do a multiplication 10 million times, and tell you how long it took.
;; What do you notice?

(time
  (dotimes [n 10000000]
    (* 6 9.238475)))

(time
  (dotimes [n 10000000]
    (* 6M 9.238475M)))

;; The `format` function can be used to show a double rounded at a particular decimal position, though
;; the notation is a bit odd. Basically you give it a "formatting instruction", and then the value(s) to format.
;; Formatting a decimal number to two decimal points looks like this (notice we're using BigDecimals here):
(format "%.02f" (* 6.3M 9.238475M))

;; EXERCISE: If we use double instead, What do you expect to be the output of the following? Why?
(format "%.02f" (* 6.3 9.238475))

;; QUESTION: Floating point numbers are slightly inaccurate, but the book talks about how accurate they are. The
;; practice of careful rounding can be used to take care of the slight differences in numbers.
;; Assume you are writing a calculation that deals with money. Try to make an argument
;; for using double, and another argument for using BigDecimals.

;; Characters

;; Sometimes it is useful to be able to talk about single characters
;; in Clojure if you precede a glyph with backslash, you get a character:

\A

;; You can use the type function to see that it is indeed its own kind of thing:
(type \A)

;; The `int` function in Clojure asks to "coerce" a value to an
;; integer (if possible).

;; EXERCISE: GUESS what the answer to the following is BEFORE running
;; it:
(int \A)

;; EXERCISE: Guess what this will return, and then run it:
(type \A)

;; Strings

;; EXERCISE: Type in the clojure string that will represent: "Hello", said Tony

;; EXERCISE: Type in the clojure string that will represent the multiline
;; value:
;; Hi
;; There

;; BONUS: Repeat the prior exercise, but do it in a different way (see book).

;; In Clojure, The `str` function joins together multiple things into a single string:
(str "Hello" " " "There")

;; EXERCISE: What will this output:
(str \H \e \l \l \o)

;; EXERCISE: What will this output:
(str \H \e \l \l \o 123)

;; EXERCISE: Use ONLY characters (like the prior exercise) to generate the string "Hello 1 2 3". Mind the spaces!

;; EXERCISE: Review https://clojure.org/guides/weird_characters and see if you can do the prior exercise in a more
;; readably way.

;; Arrays

;; Clojure has "constructor functions" for building new arrays of a given type:'
;; The `def` built-in allows us to assign a symbolic name to a value, so we can
;; make an array of 10 integers called `arr` like so:
(def arr (int-array 10))

;; NOTE: Make sure you ran the line above, or the following won't work!

;; EXERCISE: Put the number 42 at offset 5 in arr. See book.

;; EXERCISE: Get the number at offset 5 from arr. See book.

;; EXERCISE: Try putting the number 10 at offset 10. What happens? Why?

;; EXERCISE: Try putting the number 10 at offset 0.
