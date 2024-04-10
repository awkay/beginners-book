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
(/ (* (+ 11 9) 6) 2)
60

;; EXERCISE: What is the data type (e.g. use the type function) of
;; the ANSWER to the prior question?
(type 60)
;; java.lang.Long

;; EXERCISE: Do the prior question, but divide by 17 instead of 2. Is
;; it different? Why do you think?
(/ (* (+ 11 9) 6) 17)
120/17
;;Result is not an integer = clojure displays non integers as fractions

;; A useful operation in integer division turns out to be the modulo operation. This returns the "remainder" from
;; the division, and is useful for converting a source number into a fixed range. Try the following sequence
;; of things in a REPL:
(mod 4 2)
;; 0 as no remainder
(mod 5 2)
;; 1 as remainder of 1
(mod 6 2)
;; 0 as no remainder
(mod 7 2)
;; 1 as remainder of 1
(mod 8 2)
;; 0 as no remainder


(mod 10 6)
;; 4
(mod 10 -1)
;; 0
(mod -2 5)
;; 3

;; When the second argument of the mod function is negative, clojure interprets it as 0.
;; So the remainder is equal to the first argument

;; EXERCISE: Show the same kind of sequence you would use to get
;; a number between 0 and 4.


(mod 4 3)
;;0
(mod 5 3)
;;1
(mod 6 3)
;;2
(mod 7 3)
;;0
(mod 8 3)
;;2

;; there are multiple answers to this that achieve the same result

;; Decimals
;; Clojure uses 64 bit floats (double) by default

;; EXERCISE: Ask Clojure to add together 0.1 3 times. What is the
;; answer? Why do you suppose it is that?

( + 0.1 0.1 0.1)
;; 0.30000000000000004
;; 0.1 cannot be represented precisely as floating point format has limitations which
;; result in rounding errors

;; Sometimes you have a decimal, but you only want the integer portion. The `int` function coerces a value
;; to an integer if it can.

     ;; EXERCISE: Guess what this will return before running it:
     (int (/ 4.6 2.0))
;;2

;; Big Decimals.

     ;; Earlier you saw an example of where floating point math is slightly
     ;; inaccurate.
     ;; Clojure actually supports "arbitrary precision math" on decimal
     ;; numbers. (known as BigDecimals). You can simply put an M suffix on a number
     ;; to use them. For example, try this:
     (+ 0.1M 0.1M 0.1M)
;;0.3M
     ;; Try these:
     (* 6.3 9.238475)
;;58.202392499999995
     (* 6.3M 9.238475M)
;;58.2023925M
     ;; EXERCISE: There are two drawbacks to the more accurate math. Can you guess what they are?
;; rounding errors = loss of accuracy
;; Using Big Decimals over double or float = memory usage/decreased performance

;; EXERCISE: Run the following two things. They are both loops
;; that do a multiplication 10 million times, and tell you how long it took.
;; What do you notice?

(time
  (dotimes [n 10000000]
    (* 6 9.238475)))

(time
  (dotimes [n 10000000]
    (* 6M 9.238475M)))

;;the first loop using double precision floating point numbers is considerably faster than
;; the loop using Big Decimal


     ;; The `format` function can be used to show a double rounded at a particular decimal position, though
     ;; the notation is a bit odd. Basically you give it a "formatting instruction", and then the value(s) to format.
     ;; Formatting a decimal number to two decimal points looks like this (notice we're using BigDecimals here):
     (format "%.02f" (* 6.3M 9.238475M))

     ;; EXERCISE: If we use double instead, What do you expect to be the output of the following? Why?
     (format "%.02f" (* 6.3 9.238475))
;;I expect the output to be the same. Formatting to two decimal points from a Big Decimal and a double floating point
;; should give the same value. The level of precision both are going into is not necessary


;; QUESTION: Floating point numbers are slightly inaccurate, but the book talks about how accurate they are. The
;; practice of careful rounding can be used to take care of the slight differences in numbers.
;; Assume you are writing a calculation that deals with money. Try to make an argument
;; for using double, and another argument for using BigDecimals.

;;Using double:
;;Lower memory usage and faster performance
;;Using BigDecimals:
;; No rounding errors = high accuracy and reliability


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
;;? --> uses unicode standard of A=65
;; wasn't expecting that

;; EXERCISE: Guess what this will return, and then run it:
(type \A)
;;java.lang.Character

;; Strings

;; EXERCISE: Type in the clojure string that will represent: "Hello", said Tony
"\"Hello, \" said Tony"


;; EXERCISE: Type in the clojure string that will represent the multiline
;; value:
;; Hi
;; There

"Hi
There"


;; BONUS: Repeat the prior exercise, but do it in a different way (see book).

"Hi\nThere"


;; In Clojure, The `str` function joins together multiple things into a single string:
(str "Hello" " " "There")
;;Hello There

;; EXERCISE: What will this output:
(str \H \e \l \l \o)
;;Hello


;; EXERCISE: What will this output:
(str \H \e \l \l \o 123)
;; Hello 123 = WRONG
;; Hello123 - RIGHT
;; need to explicitly add a space. Strings will concatenate them in the order they are provided
;; they will not insert spaces or characters in between
(str \H \e \l \l \o " " 123)
;;this gives 'Hello 123'
(str \H\e\l\l\o" "123)
;; and so does this
;; but this is less readable and lacks clarity.
;; even though valid in Clojure, better practice to use the previous


;; EXERCISE: Use ONLY characters (like the prior exercise) to generate the string "Hello 1 2 3". Mind the spaces!

;;1 = U+0031 = unicode code point 49
;;2 = U+0032 = unicode code point 50
;;3 = U+0033 = unicode code point 51
;;(str Hello " " (+ U+0031 U+0032 U+0033) = NO

;;Not sure which is best answer here:
(str "Hello "(char 49)(char 50)(char 51))
;; WRONG
;; ;; This is what you were looking for in unicode land to make '123':
(str \u0031 \u0032 \u0033)

(str \H \e \l \l \o " " \1 " " \2 " " \3)
;; ^ is answer
(str \H \e \l \l \o`\ `\1 `\ `\2 `\ `\3)
;; this also applies

;; EXERCISE: Review https://clojure.org/guides/weird_characters and see if you can do the prior exercise in a more
;; readably way.

(str \H \e \l \l \o \space \1 \space \2 \space \3)

;; Arrays

;; Clojure has "constructor functions" for building new arrays of a given type:'
;; The `def` built-in allows us to assign a symbolic name to a value, so we can
;; make an array of 10 integers called `arr` like so:
(def arr (int-array 10))

;; NOTE: Make sure you ran the line above, or the following won't work!

;; EXERCISE: Put the number 42 at offset 5 in arr. See book.
(aset arr 5 42)
;; 5

;; EXERCISE: Get the number at offset 5 from arr. See book.
(aget arr 5)
;; 5

;; EXERCISE: Try putting the number 10 at offset 10. What happens? Why?
(aset arr 10 10)
;; error - Index 10 out of bounds for length 10
;; array has a length of 0-9. 10 is not on there

;; EXERCISE: Try putting the number 10 at offset 0.
(aset arr 0 10)
;; 10