(ns section1_3)

;; Integers
;; Typing in a simple sequence of digits gives an integer (64-bit). The most common manipulations are simple math.
;; Evaluate the following in a REPL:

42
(+ 1 2 3 4 5)
(* 2 2 2)
(/ 4 2)

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

;; Big Decimals.
;; Clojure actually supports "arbitrary precision math" on decimal
;; numbers. (known as BigDecimals). You put an M suffix on a number
;; to use them.
(+ 0.1M 0.1M 0.1M)

;; EXERCISE: Run the following two things. They are both loops
;; that do math a bunch of times, and tell you how long it took.
;; What do you notice?

(time
  (dotimes [n 10000000]
    (* 1 9.238475)))

(time
  (dotimes [n 10000000]
    (* 1M 9.238475M)))

;; Characters

;; Strings

;; Arrays
